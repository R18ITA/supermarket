package net.togogo;

import net.togogo.entity.*;
import net.togogo.serviceimpl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ReturnManagement")
public class ReturnController {

    @Resource
    ReturnLineServiceImpl returnLineService;

    @Resource
    ReturnDetailsServiceImpl returnDetailsService;

    @Resource
    ProductsServiceImpl productsService;

    @Resource
    UserroleServiceImpl userroleService;

    @Resource
    RolesServiceImpl rolesService;

    //检验商品是否足够
    @RequestMapping("/checkStoreofReturn")
    @ResponseBody
    public Map<String,Object> checkStoreReturn(Integer id)
    {
        Map<String,Object> map = new HashMap<String,Object>();

        //拿到要新加入退货单的商品
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Products> products = productsService.selectByExample(productsExample);

        Products addproduct = products.get(0);
        if(addproduct.getStore()>0)
        {
            System.out.println("足够");
            map.put("checkresult","商品足够");
            return map;
        }
        map.put("checkresult","商品不足");
        return map;

    }

//加入商品到临时退货单
    @RequestMapping("/addtotemporaryreturn")
    public String addToTemporaryReturn(Model model, HttpSession session,Integer id){

        //拿到临时退货单单的商品列表
        List<Products> productslist = (List<Products>) session.getAttribute("temporaryreturn");
        if(productslist==null)
            productslist = new ArrayList<Products>();

        //拿到要新加入临时退货单单的商品
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Products> products = productsService.selectByExample(productsExample);

        //将新加入的商品加入临时退货单单
        for(int i=0;i<products.size();i++) {
            products.get(i).setStore(products.get(i).getStore()-1);
            productsService.updateProduct(products.get(i));
            productslist.add(products.get(i));
        }

        //计算临时订单总金额
        Double sumamount= Double.valueOf(0);
        for (int i=0;i<productslist.size();i++)
        {
            if (productslist.get(i).getDiscount()==0)
                sumamount+=productslist.get(i).getSaleprice();
            else
                sumamount+=(productslist.get(i).getSaleprice()*productslist.get(i).getDiscount()*0.1);
        }

        model.addAttribute("temporaryreturn",productslist);
        model.addAttribute("sumamount",sumamount);
        session.setAttribute("temporaryreturn",productslist);
        session.setAttribute("sumamount",sumamount);

        return "temporaryreturn";
    }

//生成退货单
    @RequestMapping("/addReturn")
    @ResponseBody
    public Map<String,Object> addReturn(HttpSession session) throws ParseException {

        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("addresult","你无权限操作！");
            return map;
        }
        //获取临时退货单的商品列表和总金额
        List<Products> productslist = (List<Products>) session.getAttribute("temporaryreturn");
        Double sumamount = (Double) session.getAttribute("sumamount");

        //临时订单无商品时
        if(productslist==null||sumamount==null)
        {
            map.put("addresult","未加入任何商品进入退货单，无法退货！");
            return map;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date time = new Date();// new Date()为获取当前系统时间
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(time);
        calendar.add(Calendar.DATE,1);
        String savetime = df2.format(calendar.getTime());

        //加入退货单列表
        //1.查询今日已有退货单数量，按日进行退货单编号
        ReturnlineExample returnlineExample = new ReturnlineExample();
        ReturnlineExample.Criteria cri = returnlineExample.createCriteria();
        cri.andRlIdLike(df.format(time)+"%");
        List<Returnline> returnlines = returnLineService.selectReturnLineByExamole(returnlineExample);

//        2.生成退货单编号
        String rlid = df.format(time)+(returnlines.size()+1);

//        3.退货单记录存入数据库
        Returnline returnline = new Returnline();
        returnline.setRlId(rlid);
        returnline.setAmount(sumamount);
        returnline.setCreatetime(df2.parse(savetime));
        int addresultrl = returnLineService.addReturnLine(returnline);

//        订单详情表存入数据库
        Returndetail rd = new Returndetail();
        int addresultrd = 0;
        for(int i=0;i<productslist.size();i++)
        {
            rd.setRdId(rlid);
            rd.setpId(productslist.get(i).getpId());
            rd.setRdNum((double) 1);
            for(int j=i+1;j<productslist.size();j++)
            {
                if(productslist.get(i).getpId().equals(productslist.get(j).getpId()))
                {
                    rd.setRdNum(rd.getRdNum()+1);
                    productslist.remove(productslist.get(j));
                }
            }
            rd.setsId(productslist.get(i).getsId());
            rd.setCreatetime(df2.parse(savetime));

            addresultrd+=returnDetailsService.addReturnDetails(rd);
        }

        session.removeAttribute("sumamount");
        session.removeAttribute("temporaryreturn");

        if(addresultrl>=1&&addresultrd>=1)
        {
            map.put("addresult","退货单生成成功!");
            return map;
        }

        map.put("addresult","退货单生成失败!");
        return map;
    }


    //删除退货单
    @RequestMapping("/deleteReturn")
    @ResponseBody
    public Map<String,Object> deleteReturn(HttpSession session,Integer id)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("deleteresult","你无权限操作！");
            return map;
        }

        ReturnlineExample returnlineExample = new ReturnlineExample();
        ReturnlineExample.Criteria cri = returnlineExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Returnline> deletereturnlines = returnLineService.selectReturnLineByExamole(returnlineExample);

        Returnline deletereturnline = deletereturnlines.get(0);

        //获取要删除的退货单单编号
        String rdid = deletereturnline.getRlId();


        //删除退货单列表
        int deleteresultrl = returnLineService.deleteReturnLineByPK(id);

        //删除对应退货单单详情
        ReturndetailExample returndetailExample = new ReturndetailExample();
        ReturndetailExample.Criteria cri1 = returndetailExample.createCriteria();
        cri1.andRdIdEqualTo(rdid);
        int deleteresultrd = returnDetailsService.deleteReturnDetailsByExample(returndetailExample);

        if(deleteresultrd>=1||deleteresultrl>=1)
        {
            map.put("deleteresult","删除成功！");
            return map;
        }
        map.put("deleteresult","删除失败！");
        return map;
    }




    public boolean checkrole(HttpSession session)
    {
        //检验用户权限
        Users users = (Users) session.getAttribute("user");
        Userrole userrole = userroleService.getRid(users.getuId()).get(0);
        RolesExample rolesExample = new RolesExample();
        RolesExample.Criteria cri = rolesExample.createCriteria();
        cri.andRIdEqualTo(userrole.getrId());
        Roles role = rolesService.selectByExample(rolesExample).get(0);
        if(role.getrName().equals("库存管理员")||role.getrName().equals("超市经理"))
        {
            return true;
        }
        return false;
    }

}

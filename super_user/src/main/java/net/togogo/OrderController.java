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
@RequestMapping("/OrderManagement")
public class OrderController {

    @Resource
    ProductsServiceImpl productsService;

    @Resource
    OrderLineServiceImpl orderLineService;

    @Resource
    OrderDetailsServiceImpl orderDetailsService;

    @Resource
    UserroleServiceImpl userroleService;

    @Resource
    RolesServiceImpl rolesService;


//检验商品是否足够
    @RequestMapping("/checkStore")
    @ResponseBody
    public Map<String,Object> checkStore(Integer id)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        //拿到要新加入临时订单的商品
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Products> products = productsService.selectByExample(productsExample);

        Products addproduct = products.get(0);
        if(addproduct.getStore()>0)
        {
            map.put("checkresult","商品足够");
            return map;
        }
        map.put("checkresult","商品不足");
        return map;

    }


//加入商品到临时订单
    @RequestMapping("/addtotemporary")
    public String addtoTemporary(Model model,HttpSession session, Integer id){

        //拿到临时订单的商品列表
        List<Products> productslist = (List<Products>) session.getAttribute("temporaryorder");
        if(productslist==null)
            productslist = new ArrayList<Products>();

        //拿到要新加入临时订单的商品
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Products> products = productsService.selectByExample(productsExample);

        //将新加入的商品加入临时订单
        for(int i=0;i<products.size();i++) {
            productslist.add(products.get(i));
            products.get(i).setStore(products.get(i).getStore()-1);
            productsService.updateProduct(products.get(i));
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

        model.addAttribute("temporaryorder",productslist);
        model.addAttribute("sumamount",sumamount);
        session.setAttribute("temporaryorder",productslist);
        session.setAttribute("sumamount",sumamount);

        return "temporary";
    }

//结算、生成订单
    @RequestMapping("/addtoOrder")
    @ResponseBody
    public Map<String,Object> addtoOrder(HttpSession session) throws ParseException {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("addresult","你无权限操作！");
            return map;
        }

        //获取临时订单的产品列表和总金额
        List<Products> productslist = (List<Products>) session.getAttribute("temporaryorder");
        Double sumamount = (Double) session.getAttribute("sumamount");

        //临时订单无商品时
        if(productslist==null||sumamount==null)
        {
            map.put("addresult","未加入任何商品进入订单，无法结算！");
            return map;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date time = new Date();// new Date()为获取当前系统时间
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(time);
        calendar.add(Calendar.DATE,1);
        String savetime = df2.format(calendar.getTime());
        System.out.println(df.format(time));
        System.out.println(savetime);

        //加入订单列表
        //1.查询今日已有订单数量，按日进行订单编号
        OrderlineExample orderlineExample = new OrderlineExample();
        OrderlineExample.Criteria cri = orderlineExample.createCriteria();
        cri.andOlIdLike(df.format(time)+"%");
        List<Orderline> orderlines = orderLineService.selectOrderLineByExample(orderlineExample);

        //2.生成订单编号
        String olid = df.format(time)+(orderlines.size()+1);
        //3.生成订单记录存入数据库
        Orderline neworderline = new Orderline();
        neworderline.setOlId(olid);
        neworderline.setAmount(sumamount);
        neworderline.setCreatetime(df2.parse(savetime));
        int addresulttool = orderLineService.addOrderLine(neworderline);

        //将订单商品加入订单详情表
        Orderdetails od = new Orderdetails();
        int addresulttood = 0;
        for(int i=0;i<productslist.size();i++)
        {
            od.setOdId(olid);
            od.setpId(productslist.get(i).getpId());
            od.setDiscount(productslist.get(i).getDiscount());
            od.setNum((double) 1);
            for(int j=i+1;j<productslist.size();j++)
            {
                if(productslist.get(i).getpId().equals(productslist.get(j).getpId()))
                {
                    od.setNum(od.getNum()+1);
                    productslist.remove(productslist.get(j));
                }
            }
            addresulttood+=orderDetailsService.addOrderdetails(od);
        }

        session.removeAttribute("sumamount");
        session.removeAttribute("temporaryorder");
        if(addresulttool>=1&&addresulttood>=1)
        {
            map.put("addresult","结算成功!");
            return map;
        }

        map.put("addresult","结算失败！");
        return map;
    }


//删除订单
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public Map<String,Object> deleteOrder(HttpSession session,Integer id)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        //检验用户权限
        if(!checkrole(session))
        {
            map.put("deleteresult","你无权限操作！");
            return map;
        }

        OrderlineExample orderlineExample = new OrderlineExample();
        OrderlineExample.Criteria cri = orderlineExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Orderline> deleteorderlines = orderLineService.selectOrderLineByExample(orderlineExample);

        Orderline deleteorderline = deleteorderlines.get(0);

        //获取要删除的订单编号
        String odid = deleteorderline.getOlId();

        //删除订单列表
        int deleteresultol = orderLineService.deleteOrderLineByPK(id);

        //删除对应订单详情
        OrderdetailsExample orderdetailsExample = new OrderdetailsExample();
        OrderdetailsExample.Criteria cri1 = orderdetailsExample.createCriteria();
        cri1.andOdIdEqualTo(odid);
        int deleteresultod = orderDetailsService.deleteOrderdetailsByExample(orderdetailsExample);

        if(deleteresultol>=1||deleteresultod>=1)
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
        if(role.getrName().equals("销售员")||role.getrName().equals("超市经理"))
        {
            return true;
        }
        return false;
    }

}

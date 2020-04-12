package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.service.ProductsService;
import net.togogo.serviceimpl.PurchasingDetailsServiceImpl;
import net.togogo.serviceimpl.PurchasingLineServiceImpl;
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

public class PurchasingController {

    @Resource
    ProductsService productsService;

    @Resource
    PurchasingLineServiceImpl purchasingLineService;

    @Resource
    PurchasingDetailsServiceImpl purchasingDetailsService;

//加入商品到临时进货单
    @RequestMapping("/addtotempurchasing")
    public String addtoTemPurchasing(Model model, HttpSession session, Integer id)
    {
        //拿到临时进货单的商品列表
        List<Products> productslist = (List<Products>) session.getAttribute("tempurchasing");
        if(productslist==null)
            productslist = new ArrayList<Products>();

        //拿到要新加入临时进货单的商品
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(id);
        List<Products> products = productsService.selectByExample(productsExample);

        //将新加入的商品加入临时进货单
        for(int i=0;i<products.size();i++) {
            productslist.add(products.get(i));
        }

        //计算临时进货单总金额
        Double sumamount= Double.valueOf(0);
        for (int i=0;i<productslist.size();i++)
        {
            sumamount+=productslist.get(i).getPurchasingprice();
        }

        model.addAttribute("tempurchasing",productslist);
        model.addAttribute("sumamount",sumamount);
        session.setAttribute("tempurchasing",productslist);
        session.setAttribute("sumamount",sumamount);

        return "temporarypurchasing";
    }


//生成进货单
    @RequestMapping("/addtoPurchasing")
    @ResponseBody
    public Map<String,Object> addtoPurchasing(HttpSession session) throws ParseException {
        Map<String,Object> map = new HashMap<String, Object>();


        //获取临时进货单的产品列表和总金额
        List<Products> productslist = (List<Products>) session.getAttribute("tempurchasing");
        Double sumamount = (Double) session.getAttribute("sumamount");

        //临时进货单无商品时
        if(productslist==null||sumamount==null)
        {
            map.put("addresult","未加入任何商品进入订单，无法进货！");
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

        //加入进货单列表

        //2.生成进货单编号
        String plid = df.format(time);
        //3.生成订单记录存入数据库
        Purchasingline purchasingline = new Purchasingline();
        purchasingline.setPlId(plid);
        purchasingline.setAmount(sumamount);
        purchasingline.setCreatetime(df2.parse(savetime));
        purchasingline.setStatus(0);
        int addresultpl = purchasingLineService.addPurchasingLine(purchasingline);

        //将订单商品加入订单详情表
        Purchasingdetail pd = new Purchasingdetail();
        int addresultpd = 0;
        for(int i=0;i<productslist.size();i++)
        {
            pd.setPdId(plid);
            pd.setpId(productslist.get(i).getpId());
            pd.setPdNum((double) 1);
            for(int j=i+1;j<productslist.size();j++)
            {
                if(productslist.get(i).getpId().equals(productslist.get(j).getpId()))
                {
                    pd.setPdNum(pd.getPdNum()+1);
                    productslist.remove(productslist.get(j));
                }
            }
            pd.setkId(productslist.get(i).getkId());
            pd.setsId(productslist.get(i).getsId());
            pd.setPurchasingprice(productslist.get(i).getPurchasingprice());
            pd.setpUnit(productslist.get(i).getUnit());
            pd.setAmount(pd.getPdNum()*pd.getPurchasingprice());

            addresultpd+=purchasingDetailsService.addPurchasingDetail(pd);
        }

        session.removeAttribute("sumamount");
        session.removeAttribute("tempurchasing");
        if(addresultpl>=1&&addresultpd>=1)
        {
            map.put("addresult","生成订货单成功!");
            return map;
        }

        map.put("addresult","生成订货单失败！");
        return map;
    }



    //查询所有的订单
    @RequestMapping("/getPurchasingline")
    public String getPurchasingLind(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,5);
        PurchasinglineExample example=new PurchasinglineExample();
        PurchasinglineExample.Criteria cri=example.createCriteria();
        cri.andStatusNotEqualTo(0);

        List<Purchasingline> purchasinglines=purchasingLineService.selectPurchasinglineByExample(example);
        PageInfo<Purchasingline> pageInfo=new PageInfo<>(purchasinglines);
        System.out.println(purchasinglines.size());
        model.addAttribute("purchasinglines",purchasinglines);
        model.addAttribute("page",pageInfo);
        return "approval";
    }

    //获取某一次的进货单详情
    @RequestMapping("/getPurchasingdetail")
    public String getPurchasingdetail(Model model,String plId,Integer pageNum){
        if (pageNum==null){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,5);
        PurchasingdetailExample example=new PurchasingdetailExample();
        PurchasingdetailExample.Criteria cri=example.createCriteria();
        cri.andPdIdEqualTo(plId);

        List<Purchasingdetail> purchasingdetails=purchasingDetailsService.selectPurchasingDetailsByExample(example);
        PageInfo<Purchasingdetail> pageInfo=new PageInfo<>(purchasingdetails);
        model.addAttribute("purchasingdetails",purchasingdetails);
        model.addAttribute("page",pageInfo);
        model.addAttribute("plId",plId);
        return "purchasingdetail";
    }
}

package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.service.KindService;
import net.togogo.service.ProductsService;
import net.togogo.service.ReturnDetailsService;
import net.togogo.service.SuppliersService;
import net.togogo.serviceimpl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/Guider")
public class GuideController {

    @Resource
    KindService kindService;

    @Resource
    ProductsService productsService;

    @Resource
    SuppliersService suppliersService;

    @Resource
    OrderLineServiceImpl orderLineService;

    @Resource
    OrderDetailsServiceImpl orderDetailsService;

    @Resource
    ReturnLineServiceImpl returnLineService;

    @Resource
    ReturnDetailsService returnDetailsService;

    @Resource
    PurchasingLineServiceImpl purchasingLineService;

    @Resource
    PurchasingDetailsServiceImpl purchasingDetailsService;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

//  主页面
    @RequestMapping("/tocommon")
    public String tocommon(){
        return "common";
    }


//    商品相关页面
    @RequestMapping("/toproductsPart")
    public String toProductsPart(){
        return "productsPart";
    }



//    商品相关->商品管理
    @RequestMapping("/toproducts")
    public String toProducts(Model model,Integer pageNum,String showkind)
    {
        if (pageNum==null)
            pageNum = 1;

        PageHelper.startPage(pageNum,5);
        if (showkind==null||showkind.equals("全部"))
        {
            showkind="全部";
            //获取商品列表
            List<Products> products = productsService.selectByExample(new ProductsExample());
            PageInfo<Products> pageInfo = new PageInfo<Products>(products);
            model.addAttribute("products",products);
            model.addAttribute("page",pageInfo);
        }
        else
        {
            ProductsExample productsExample = new ProductsExample();
            ProductsExample.Criteria cri = productsExample.createCriteria();
            cri.andKIdEqualTo(showkind);
            List<Products> products = productsService.selectByExample(productsExample);
            PageInfo<Products> pageInfo = new PageInfo<Products>(products);
            model.addAttribute("products",products);
            model.addAttribute("page",pageInfo);
        }

    //获取商品种类列表
        List<Kind> kinds = kindService.selectKindByExample(new KindExample());
    //获取供应商列表
        List<Suppliers> suppliers = suppliersService.selectSupplierByExample(new SuppliersExample());


        model.addAttribute("kinds",kinds);
        model.addAttribute("suppliers",suppliers);
        model.addAttribute("showkind",showkind);
        return "products";
    }



//    商品相关->种类管理
    @RequestMapping("/toproductkind")
    public String toProductKind(Model model,Integer pageNum){
        if (pageNum==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        List<Kind> kinds = kindService.selectKindByExample(new KindExample());
        PageInfo<Kind> pageInfo = new PageInfo<Kind>(kinds);
        model.addAttribute("kinds",kinds);
        model.addAttribute("page",pageInfo);
        return "productkind";
    }


//    商品相关->供应商
    @RequestMapping("/tosuppliers")
    public String toSuppliers(Model model,Integer pageNum)
    {
        if(pageNum==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        List<Suppliers> suppliers = suppliersService.selectSupplierByExample(new SuppliersExample());
        PageInfo<Suppliers> pageInfo = new PageInfo<Suppliers>(suppliers);
        model.addAttribute("suppliers",suppliers);
        model.addAttribute("page",pageInfo);
        return "suppliers";
    }

//----------------------------------------------------------------------------------------------------------------------
//    ********************************************************************************************************************

//  销售管理页面
    @RequestMapping("/tosalePart")
    public String tosalePart()
    {
        return "salePart";
    }

//下单页面
    @RequestMapping("/tomakeOrder")
    public String tomakeOrder(Model model,Integer pageNum,String searchcondition)
    {
        if(pageNum==null)
            pageNum=1;
        List<Products> products = null;
        List<Kind> kinds = kindService.selectKindByExample(new KindExample());

        PageHelper.startPage(pageNum,5);
        //无查询条件-》查全部商品
        if(searchcondition==null||searchcondition.trim().length()==0)
            products = productsService.selectByExample(new ProductsExample());

        //有查询条件-》结合查询条件查
        else
        {
            ProductsExample productsExample = new ProductsExample();
            productsExample.or().andPIdLike("%"+searchcondition+"%");
            productsExample.or().andPNameLike("%"+searchcondition+"%");


            for(int i=0;i<kinds.size();i++)
            {
                if(kinds.get(i).getkName().indexOf(searchcondition)!=-1)
                    productsExample.or().andKIdEqualTo(kinds.get(i).getkId());
            }
            products = productsService.selectByExample(productsExample);
        }
        //分页信息
        PageInfo<Products> pageInfo = new PageInfo<Products>(products);

        model.addAttribute("products",products);
        model.addAttribute("page",pageInfo);
        model.addAttribute("kinds",kinds);
        model.addAttribute("searchcondition",searchcondition);
        return "makeorder";
    }

    @RequestMapping("/totemporary")
    public String totemporary(){return "temporary";}


//订单列表
    @RequestMapping("/toorderline")
    public String toOrderLine(Model model,Integer pageNum,String searchcondition){
        if(pageNum==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        if(searchcondition==null||searchcondition.trim().length()==0)
        {
            List<Orderline> orderlines = orderLineService.selectOrderLineByExample(new OrderlineExample());
            PageInfo<Orderline> pageInfo = new PageInfo<Orderline>(orderlines);
            model.addAttribute("orderlines",orderlines);
            model.addAttribute("page",pageInfo);
        }
        else{
            OrderlineExample orderlineExample = new OrderlineExample();
            orderlineExample.or().andIdEqualTo(Integer.parseInt(searchcondition));
            orderlineExample.or().andOlIdLike("%"+searchcondition+"%");
            List<Orderline> orderlines = orderLineService.selectOrderLineByExample(orderlineExample);
            PageInfo<Orderline> pageInfo = new PageInfo<Orderline>(orderlines);
            model.addAttribute("orderlines",orderlines);
            model.addAttribute("page",pageInfo);

        }
        model.addAttribute("searchcondition",searchcondition);
        return "orderline";
    }


//订单详情
    @RequestMapping("/toorderdetails")
    public String toOrderDetails(Model model,String id)
    {
        OrderdetailsExample orderdetailsExample = new OrderdetailsExample();
        OrderdetailsExample.Criteria cri = orderdetailsExample.createCriteria();
        cri.andOdIdEqualTo(id);
        List<Orderdetails> orderdetails = orderDetailsService.selectOrderdetailsByExample(orderdetailsExample);

        List<Products> products = productsService.selectByExample(new ProductsExample());

        model.addAttribute("orderdetails",orderdetails);
        model.addAttribute("products",products);
        return "orderdetails";
    }

//  --------------------------------------------------------------------------------------------------------------------
//    ********************************************************************************************************************


//库存管理
    @RequestMapping("/tostorepart")
    public String toStorePart(){return "storePart";}


//退单页面
    @RequestMapping("/tomakereturn")
    public String toMakeReturn(Model model,Integer pageNum,String searchcondition)
    {
        if(pageNum==null)
            pageNum=1;
        List<Products> products = null;
        List<Kind> kinds = kindService.selectKindByExample(new KindExample());
        List<Orderdetails> orderdetails = orderDetailsService.selectOrderdetailsByExample(new OrderdetailsExample());

        PageHelper.startPage(pageNum,5);
        //无查询条件-》查全部商品
        if(searchcondition==null||searchcondition.trim().length()==0)
            products = productsService.selectByExample(new ProductsExample());

            //有查询条件-》结合查询条件查
        else
        {
            ProductsExample productsExample = new ProductsExample();
            productsExample.or().andPIdLike("%"+searchcondition+"%");
            productsExample.or().andPNameLike("%"+searchcondition+"%");


            for(int i=0;i<kinds.size();i++)
            {
                if(kinds.get(i).getkName().indexOf(searchcondition)!=-1)
                    productsExample.or().andKIdEqualTo(kinds.get(i).getkId());
            }
            products = productsService.selectByExample(productsExample);
        }

        //分页信息
        PageInfo<Products> pageInfo = new PageInfo<Products>(products);

        model.addAttribute("products",products);
        model.addAttribute("page",pageInfo);
        model.addAttribute("kinds",kinds);
        model.addAttribute("orderdetails",orderdetails);
        model.addAttribute("searchcondition",searchcondition);

        return "makereturn";
    }

    //临时退货单
    @RequestMapping("/totemporaryreturn")
    public String totemporaryreturn(){return "temporaryreturn";}


//退货单列表
    @RequestMapping("/toreturnline")
    public String toReturnLine(Model model,Integer pageNum,String searchcondition){
        if(pageNum==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        if(searchcondition==null||searchcondition.trim().length()==0)
        {
            List<Returnline> returnlines = returnLineService.selectReturnLineByExamole(new ReturnlineExample());
            PageInfo<Returnline> pageInfo = new PageInfo<Returnline>(returnlines);
            model.addAttribute("returnlines",returnlines);
            model.addAttribute("page",pageInfo);
        }
        else{
            ReturnlineExample returnlineExample = new ReturnlineExample();
            returnlineExample.or().andIdEqualTo(Integer.parseInt(searchcondition));
            returnlineExample.or().andRlIdLike("%"+searchcondition+"%");
            List<Returnline> returnlines = returnLineService.selectReturnLineByExamole(returnlineExample);
            PageInfo<Returnline> pageInfo = new PageInfo<Returnline>(returnlines);
            model.addAttribute("returnlines",returnlines);
            model.addAttribute("page",pageInfo);

        }
        model.addAttribute("searchcondition",searchcondition);
        return "returnline";
    }


//退货单详情
    @RequestMapping("/toreturndetails")
    public String toReturnDetails(Model model,String id)
    {

        ReturndetailExample returndetailExample = new ReturndetailExample();
        ReturndetailExample.Criteria cri = returndetailExample.createCriteria();
        cri.andRdIdEqualTo(id);
        List<Returndetail> returndetails = returnDetailsService.selectReturnDetailsByExample(returndetailExample);

        List<Products> products = productsService.selectByExample(new ProductsExample());
        List<Suppliers> suppliers = suppliersService.selectSupplierByExample(new SuppliersExample());

        model.addAttribute("returndetails",returndetails);
        model.addAttribute("products",products);
        model.addAttribute("suppliers",suppliers);
        return "returndetails";
    }

//管理员页面
    @RequestMapping("/toSystemAdmin")
    public String toSystemAdmin(){
        return "systemAdmin";
    }


    //  --------------------------------------------------------------------------------------------------------------------
//    ********************************************************************************************************************

//进货管理
    @RequestMapping("/toPurchasingPart")
    public String toPurchasingPart(){
        return "purchasingPart";
    }


//进货单页面
    @RequestMapping("/tomakepurchasing")
    public String toMakePurchasing(Model model,Integer pageNum,String searchcondition)
    {
        if(pageNum==null)
            pageNum=1;
        List<Products> products = null;
        List<Kind> kinds = kindService.selectKindByExample(new KindExample());

        PageHelper.startPage(pageNum,5);
        //无查询条件-》查全部商品
        if(searchcondition==null||searchcondition.trim().length()==0)
            products = productsService.selectByExample(new ProductsExample());

            //有查询条件-》结合查询条件查
        else
        {
            ProductsExample productsExample = new ProductsExample();
            productsExample.or().andPIdLike("%"+searchcondition+"%");
            productsExample.or().andPNameLike("%"+searchcondition+"%");


            for(int i=0;i<kinds.size();i++)
            {
                if(kinds.get(i).getkName().indexOf(searchcondition)!=-1)
                    productsExample.or().andKIdEqualTo(kinds.get(i).getkId());
            }
            products = productsService.selectByExample(productsExample);
        }

        //分页信息
        PageInfo<Products> pageInfo = new PageInfo<Products>(products);

        model.addAttribute("products",products);
        model.addAttribute("page",pageInfo);
        model.addAttribute("kinds",kinds);
        model.addAttribute("searchcondition",searchcondition);

        return "makepurchasing";
    }

//临时进货单
    @RequestMapping("/totemporarypurchasing")
    public String totemporarypurchasing(){return "temporarypurchasing";}


//进货单列表
    @RequestMapping("/topurchasingline")
    public String toPurchasingLine(Model model,Integer pageNum,String searchcondition){
        if(pageNum==null)
            pageNum=1;
        PageHelper.startPage(pageNum,5);
        if(searchcondition==null||searchcondition.trim().length()==0)
        {
            List<Returnline> returnlines = returnLineService.selectReturnLineByExamole(new ReturnlineExample());
            List<Purchasingline> purchasinglines = purchasingLineService.selectPurchasinglineByExample(new PurchasinglineExample());
            PageInfo<Purchasingline> pageInfo = new PageInfo<Purchasingline>(purchasinglines);
            model.addAttribute("purchasinglines",purchasinglines);
            model.addAttribute("page",pageInfo);
        }
        else{
            PurchasinglineExample purchasinglineExample = new PurchasinglineExample();
            purchasinglineExample.or().andIdEqualTo(Integer.valueOf(searchcondition));
            purchasinglineExample.or().andPlIdLike("%"+searchcondition+"%");
            List<Purchasingline> purchasinglines = purchasingLineService.selectPurchasinglineByExample(purchasinglineExample);
            PageInfo<Purchasingline> pageInfo = new PageInfo<Purchasingline>(purchasinglines);
            model.addAttribute("purchasinglines",purchasinglines);
            model.addAttribute("page",pageInfo);

        }
        model.addAttribute("searchcondition",searchcondition);
        return "purchasingline";
    }

}

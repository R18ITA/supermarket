package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.serviceimpl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productsManagement")
public class ProductsController {

    @Resource
    ProductsServiceImpl productsService;

    @Resource
    SuppliersServiceImpl suppliersService;

    @Resource
    KindServiceImpl kindService;

    @Resource
    UserroleServiceImpl userroleService;

    @Resource
    RolesServiceImpl rolesService;

    //增加商品
    @RequestMapping("/addProducts")
    @ResponseBody
    public Map<String,Object> addProducts(HttpSession session, @RequestBody Products products){

        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("addresult","你无权限操作！");
            return map;
        }


        //检查输入是否合法
        if(products.getpId().trim().length()==0||products.getpName().trim().length()==0
                ||products.getPurchasingprice()==0||products.getSaleprice()==0)
        {
            map.put("addresult","商品编号、商品名、进货价、销售价不能为空");
            return map;
        }

        //商品编号和商品名是否重复
        List<Products> nowproducts = productsService.selectByExample(new ProductsExample());
        for (int i=0;i<nowproducts.size();i++)
        {
            if(nowproducts.get(i).getpId().equals(products.getpId())||nowproducts.get(i).getpName().equals(products.getpName()))
            {
                map.put("addresult","商品编号或商品名已存在，不能重复");
                return map;
            }
        }

        //增加商品
        int addresult = productsService.addProduct(products);
        if(addresult>=1) {
            map.put("addresult","增加成功");
            return map;
        }
        map.put("addresult","增加失败");
        return map;
    }



    //删除商品
    @RequestMapping("/deleteProduct")
    @ResponseBody
    public Map<String, Object> deleteProduct(HttpSession session,Integer id)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("deleteresult","你无权限操作！");
            return map;
        }

        int deleteresult = productsService.deleteProductByPK(id);
        if(deleteresult>=1)
        {
            map.put("deleteresult","删除成功");
            return map;
        }
        map.put("deleteresult","未删除，删除失败");
        return map;
    }



    //修改商品
    @RequestMapping("/updateProducts")
    @ResponseBody
    public Map<String,Object> updateProducts(HttpSession session,@RequestBody Products products)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("updateresult","你无权限操作！");
            return map;
        }

    //检查输入是否合法
        if(products.getpId().trim().length()==0||products.getpName().trim().length()==0
        ||products.getPurchasingprice()==0||products.getSaleprice()==0)
        {
            map.put("updateresult","商品编号、商品名、进货价、销售价不能为空");
            return map;
        }

    //商品编号和商品名是否重复
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdNotEqualTo(products.getId());
        List<Products> nowproducts = productsService.selectByExample(productsExample);

        for (int i=0;i<nowproducts.size();i++)
        {
            if(nowproducts.get(i).getpId().equals(products.getpId())||nowproducts.get(i).getpName().equals(products.getpName()))
            {
                map.put("updateresult","商品编号或商品名已存在，不能重复");
                return map;
            }
        }

    //修改商品
        int updateresult = productsService.updateProduct(products);
        if(updateresult>=1)
        {
            map.put("updateresult","修改成功");
            return map;
        }
        map.put("updateresult","修改失败");
        return map;
    }


    //条件查询商品
    @RequestMapping("/searchProducts")
    public String searchProducts(Model model,String searchcondition,Integer pageNum)
    {
        if(pageNum==null)
            pageNum=1;

        //构造查询条件
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        productsExample.or().andPIdLike("%"+searchcondition+"%");
        productsExample.or().andPNameLike("%"+searchcondition+"%");
        List<Suppliers> suppliers = suppliersService.selectSupplierByExample(new SuppliersExample());
        for(int i=0;i<suppliers.size();i++)
        {
            if (suppliers.get(i).getsName().indexOf(searchcondition)!=-1)
                productsExample.or().andSIdEqualTo(suppliers.get(i).getsId());
        }

        List<Kind> kinds = kindService.selectKindByExample(new KindExample());
        for(int i=0;i<kinds.size();i++)
        {
            if(kinds.get(i).getkName().indexOf(searchcondition)!=-1)
                productsExample.or().andKIdEqualTo(kinds.get(i).getkId());
        }

        //分页查询
        PageHelper.startPage(pageNum,5);
        List<Products> products = productsService.selectByExample(productsExample);
        PageInfo<Products> pageInfo = new PageInfo<Products>(products);

        model.addAttribute("products",products);
        model.addAttribute("page",pageInfo);
        model.addAttribute("searchcondition",searchcondition);
        model.addAttribute("kinds",kinds);
        model.addAttribute("suppliers",suppliers);
        model.addAttribute("showkind","全部");

        return "products";
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
        if(role.getrName().equals("仓库管理员")||role.getrName().equals("超市经理"))
        {
           return true;
        }
        return false;
    }
}

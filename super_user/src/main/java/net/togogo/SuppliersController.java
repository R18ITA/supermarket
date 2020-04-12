package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.serviceimpl.RolesServiceImpl;
import net.togogo.serviceimpl.SuppliersServiceImpl;
import net.togogo.serviceimpl.UserroleServiceImpl;
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
@RequestMapping("/suppliersManagement")
public class SuppliersController {

    @Resource
    SuppliersServiceImpl suppliersService;

    @Resource
    UserroleServiceImpl userroleService;

    @Resource
    RolesServiceImpl rolesService;

//增加供应商
    @RequestMapping("/addSupplier")
    @ResponseBody
    public Map<String,Object> addSupplier(HttpSession session,@RequestBody Suppliers supplier){

        Map<String,Object> map = new HashMap<String, Object>();
        //检验用户权限
        if(!checkrole(session))
        {
            map.put("addresult","你无权限操作！");
            return map;
        }

        //检出输入是否正确
        if(supplier.getsId().trim().length()==0||supplier.getsName().trim().length()==0||supplier.getsPhone().trim().length()==0
        ||supplier.getsEmail().trim().length()==0||supplier.getsAddress().trim().length()==0||supplier.getLeader().trim().length()==0
        ||supplier.getLeaderphone().trim().length()==0)
        {
            map.put("addresult","所填内容不能为空！");
            return map;
        }

        //检查供应商编号和供应商名称是否重复
        List<Suppliers> nowsuppliers = suppliersService.selectSupplierByExample(new SuppliersExample());
        for (int i=0;i<nowsuppliers.size();i++)
        {
            if(nowsuppliers.get(i).getsId().equals(supplier.getsId())||nowsuppliers.get(i).getsName().equals(supplier.getsName()))
            {
                map.put("addresult","供应商编号或者供应商名称已存在，请重新输入!");
                return map;
            }
        }

        //添加供应商
        int addresult = suppliersService.addSupplier(supplier);
        if (addresult>=1)
        {
            map.put("addresult","增加成功");
            return map;
        }
        map.put("addresult","增加失败");
        return map;
    }


//删除供应商
    @RequestMapping("/deleteSupplier")
    @ResponseBody
    public Map<String,Object> deleteSupplier(HttpSession session,Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        //检验用户权限
        if(!checkrole(session))
        {
            map.put("deleteresult","你无权限操作！");
            return map;
        }
        int deleteresult = suppliersService.deleteSupplierByPK(id);
        if(deleteresult>=1)
        {
            map.put("deleteresult","删除成功");
            return map;
        }
        map.put("deleteresult","删除失败");
        return map;
    }


//根据条件查询供应商
    @RequestMapping("/searchSupplier")
    public String searchSupplier(Model model,String searchcondition,Integer pageNum)
    {
        if(pageNum==null)
            pageNum=1;

        //若查询框内无内容，跳转到显示所有供应商页面
        if(searchcondition==null||searchcondition.trim().length()==0)
        {
            return "redirect:/Guider/tosuppliers?pageNum="+pageNum;
        }

        //构造查询条件
        SuppliersExample suppliersExample = new SuppliersExample();
        suppliersExample.or().andSIdLike("%"+searchcondition+"%");
        suppliersExample.or().andSNameLike("%"+searchcondition+"%");
        suppliersExample.or().andLeaderLike("%"+searchcondition+"%");

        //分页查询
        PageHelper.startPage(pageNum,5);
        List<Suppliers> suppliers = suppliersService.selectSupplierByExample(suppliersExample);
        PageInfo<Suppliers> pageInfo = new PageInfo<Suppliers>(suppliers);

        model.addAttribute("suppliers",suppliers);
        model.addAttribute("page",pageInfo);
        model.addAttribute("searchcondition",searchcondition);

        return "suppliers";
    }

//修改供应商
    @RequestMapping("/updateSupplier")
    @ResponseBody
    public Map<String,Object> updateSupplier(HttpSession session,@RequestBody Suppliers supplier){

        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("updateresult","你无权限操作！");
            return map;
        }

        //检查输入是否合法
        if(supplier.getsId().trim().length()==0||supplier.getsName().trim().length()==0||supplier.getsPhone().trim().length()==0
                ||supplier.getsEmail().trim().length()==0||supplier.getsAddress().trim().length()==0||supplier.getLeader().trim().length()==0
                ||supplier.getLeaderphone().trim().length()==0)
        {
            map.put("updateresult","所修改内容不能为空！");
            return map;
        }

        //检查供应商编号和供应商名称是否重复
        SuppliersExample suppliersExample = new SuppliersExample();
        SuppliersExample.Criteria cri = suppliersExample.createCriteria();
        cri.andIdNotEqualTo(supplier.getId());
        List<Suppliers> nowsuppliers = suppliersService.selectSupplierByExample(suppliersExample);
        for (int i=0;i<nowsuppliers.size();i++)
        {
            if(nowsuppliers.get(i).getsId().equals(supplier.getsId())||nowsuppliers.get(i).getsName().equals(supplier.getsName()))
            {
                map.put("updateresult","修改失败,供应商编号或者供应商名称已存在，请重新输入!");
                return map;
            }
        }

        //修改供应商
        int updateresult = suppliersService.updateSupplier(supplier);
        if(updateresult>=1)
        {
            map.put("updateresult","修改成功");
            return map;
        }
        map.put("updateresult","修改失败");
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
        if(role.getrName().equals("仓库管理员")||role.getrName().equals("超市经理"))
        {
            return true;
        }
        return false;
    }

}

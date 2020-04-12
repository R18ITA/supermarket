package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.service.KindService;
import net.togogo.serviceimpl.RolesServiceImpl;
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
@RequestMapping("/kindManagement")
public class KindController {

    @Resource
    KindService kindService;

    @Resource
    UserroleServiceImpl userroleService;

    @Resource
    RolesServiceImpl rolesService;

    //增加种类
    @RequestMapping("/addKind")
    @ResponseBody
    public Map<String,Object> addKind(HttpSession session,@RequestBody Kind kind)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("addresult","你无权限操作！");
            return map;
        }

//    检查输入是否合法
        if(kind.getkId().trim().length()==0||kind.getkId()==null||kind.getkName().trim().length()==0||kind.getkName()==null)
        {
            map.put("addresult","商品编号和商品名不能为空，请重新输入！");
            return map;
        }

//    检查输入商品编号是否已存在
        KindExample kindExample = new KindExample();
        KindExample.Criteria cri = kindExample.createCriteria();
        cri.andKIdEqualTo(kind.getkId());
        List<Kind> nowkinds = kindService.selectKindByExample(kindExample);
        if(nowkinds!=null&&nowkinds.size()>0)
        {
            map.put("addresult","商品编号已存在，请重新输入！");
            return map;
        }

//    添加商品
        int addresult = kindService.addKind(kind);
        if(addresult>=1)
        {
            map.put("addresult","添加成功");
            return map;
        }
        map.put("addresult","添加失败");
        return map;
    }

    //删除种类
    @RequestMapping("/deleteKind")
    @ResponseBody
    public Map<String,Object> deleteKind(HttpSession session,Integer id)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("deleteresult","你无权限操作！");
            return map;
        }

        int deleteresult = kindService.deleteKindByPK(id);
        if(deleteresult>=1)
        {
            map.put("deleteresult","删除成功");
            return map;
        }
        map.put("deleteresult","删除失败");
        return map;
    }

    //根据条件查询种类
    @RequestMapping("/searchKind")
    public String searchKind(Model model,String searchcondition,Integer pageNum)
    {
        if (pageNum==null)
            pageNum=1;

    //若查询框内无内容，跳转到显示所有商品种类页面
        if(searchcondition==null||searchcondition.trim().length()==0)
        {
            return "redirect:/Guider/toproductkind?pageNum="+pageNum;
        }


//    构造查询条件
        KindExample kindExample = new KindExample();
        KindExample.Criteria cri = kindExample.createCriteria();
        cri.andKIdLike("%"+searchcondition+"%");
        kindExample.or().andKNameLike("%"+searchcondition+"%");
        kindExample.or().andKIdLike("%"+searchcondition+"%");

//    分页查询
        PageHelper.startPage(pageNum,5);
        List<Kind> kinds = kindService.selectKindByExample(kindExample);
        PageInfo<Kind> pageInfo = new PageInfo<Kind>(kinds);


        model.addAttribute("kinds",kinds);
        model.addAttribute("page",pageInfo);
        model.addAttribute("searchcondition",searchcondition);

        return "productkind";
    }

    //修改种类
    @RequestMapping("/updateKind")
    @ResponseBody
    public Map<String,Object> updateKind(HttpSession session,@RequestBody Kind kind)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        //检验用户权限
        if(!checkrole(session))
        {
            map.put("updateresult","你无权限操作！");
            return map;
        }

//    检查输入是否合法
        if(kind.getkId().trim().length()==0||kind.getkId()==null||kind.getkName().trim().length()==0||kind.getkName()==null)
        {
            map.put("updateresult","商品编号和商品名不能为空，请重新输入！");
            return map;
        }

//      检查商品编号是否重复
        KindExample kindExample = new KindExample();
        KindExample.Criteria cri = kindExample.createCriteria();
        cri.andIdNotEqualTo(kind.getId());
        List<Kind> nowkinds = kindService.selectKindByExample(kindExample);

        for (int i=0;i<nowkinds.size();i++)
        {
            if(nowkinds.get(i).getkId().equals(kind.getkId())||nowkinds.get(i).getkName().equals(kind.getkName()))
            {
                map.put("updateresult","商品编号或商品名已存在，请重新输入！");
                return map;
            }
        }

//    修改商品
        int updateresult = kindService.updateKindByExample(kind);
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

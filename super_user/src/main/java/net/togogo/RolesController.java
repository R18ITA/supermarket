package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.togogo.entity.Rolepermission;
import net.togogo.entity.RolepermissionExample;
import net.togogo.entity.Roles;
import net.togogo.entity.RolesExample;
import net.togogo.serviceimpl.RolepermissionServiceImpl;
import net.togogo.serviceimpl.RolesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class RolesController {
    @Resource
    private RolesServiceImpl rolesService;
    @Resource
    private RolepermissionServiceImpl rpservice;

    public String message=null;

    //查询所有角色信息
    @RequestMapping("/getRoles")
    public String getRoles(Model model, Integer pageNum){
        if (pageNum==null){
            pageNum=1;
        }
        RolesExample example=new RolesExample();
        PageHelper.startPage(pageNum,5);
        List<Roles> roles=rolesService.selectByExample(example);
        PageInfo<Roles> pageInfo=new PageInfo<>(roles);
        model.addAttribute("roles",roles);
        model.addAttribute("page",pageInfo);
        return "role";
    }

    //模糊查询
    @RequestMapping("/searchrole")
    public String searchRole(String key){
        RolesExample example=new RolesExample();
        RolesExample.Criteria cri=example.createCriteria();
        cri.andRIdLike("%"+key+"%");
        example.or().andRNameLike("%"+key+"%");
        List<Roles> roles=rolesService.selectByExample(example);
        return "";
    }

    //增加角色
    @RequestMapping("/createrole")
    @ResponseBody
    public String createRole(String createRid,String createRname){
        System.out.println(createRid+"***"+createRname);
        Roles roles=new Roles();
        int f,flag,flag2 = 0;
        CommonController common=new CommonController();//创建时间
        Date time=common.createtime();
        flag=rolesService.checkeRole(createRid);
        if (flag==1){
            message="添加失败，所添加的角色编号已存在！";
        }else {
            roles.setrId(createRid);
            roles.setrName(createRname);
            roles.setCreatetime(time);
            f=rolesService.insert(roles);
            if(f==0){
                message="添加失败！";
            }else {
                flag2=addrp(createRid);//同时在rolepermission表上添加
                if (flag2==0){
                    System.out.println("添加成功");
                    message="添加失败！";
                }else
                    message="添加成功";
            }
        }
        return message;
    }
    public int addrp(String rid){
        int flag;
        Rolepermission rp=new Rolepermission();
        rp.setrId(rid);
        flag=rpservice.insertSelective(rp);
        return flag;
    }

    //按主键删除
    @RequestMapping("/deleterole")
    @ResponseBody
    public  String deleteRole(HttpSession session, String id, String rId){
        int Id,flag,flag2;
        Id=Integer.parseInt(id);
        flag=rolesService.deleteByPrimaryKey(Id);
        flag2=rpservice.delete(rId);//同时删除rolepermission表中的数据
        if(flag==1&&flag2==1){
            message="删除成功！";
        }else {
            message="删除失败，请注意删除信息！";
        }
        session.setAttribute("message",message);
        return message;
    }

    //按主键部分修改
    @RequestMapping("/updaterole")
    @ResponseBody
    public String updateRole(Integer id,String rId,String rName){
        int flag2,flag;
        Roles roles=rolesService.selectByPrimaryKey(id);
        //修改roles表
        Roles role=new Roles();
        role.setId(id);
        role.setrId(rId);
        role.setrName(rName);
        flag=rolesService.updateByPrimaryKeySelective(role);
        //同时修改rolepermission表
        flag2 = updataRPbyrid(roles.getrId(),rId);
        if (flag!=0&&flag2!=0){
            message="修改成功！";
        }else{
            message="修改失败！";
        }
        return message;
    }
    public int updataRPbyrid(String rid,String rId){//rid是原r_id，rId是修改后的
        int flag=0;
        RolepermissionExample example=new RolepermissionExample();
        RolepermissionExample.Criteria cri=example.createCriteria();
        cri.andRIdEqualTo(rid);
        Rolepermission rp=new Rolepermission();
        rp.setrId(rId);
        flag=rpservice.updateByExampleSelective(rp,example);
        return flag;
    }
}

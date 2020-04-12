package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.Userrole;
import net.togogo.entity.UserroleExample;
import net.togogo.serviceimpl.UserroleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserroleController {
    @Resource
    private UserroleServiceImpl urService;

    public String message=null;

    //获取所有用户关联角色
    @RequestMapping("/getUserrole")
    public String getUserrole(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        }
        UserroleExample example=new UserroleExample();
        PageHelper.startPage(pageNum,5);
        List<Userrole> userroles=urService.selectByExample(example);
        PageInfo<Userrole> pageInfo=new PageInfo<>(userroles);
        model.addAttribute("urs",userroles);
        model.addAttribute("page",pageInfo);
        return "userrole";
    }

    //模糊查询
    @RequestMapping("/searchUR")
    public String searchUR(String key){
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andUIdLike("%"+key+"%");
        example.or().andRIdLike("%"+key+"%");
        List<Userrole> ur=urService.selectByExample(example);
        return "";
    }

    //增加
    @RequestMapping("/createUR")
    @ResponseBody
    public String createUserrole(String createURuid,String createURrid){
        Userrole userrole=new Userrole();
        int f,flag;
        f=urService.check(createURuid,createURrid);
        if (f==1){
            message="添加失败，用户关联角色已存在！";
        }else {
            userrole.setrId(createURrid);
            userrole.setuId(createURuid);
            flag=urService.insert(userrole);
            if(flag==0){
                message="添加失败，信息有误！";
            }else
                message="添加成功！";
        }
        return message;
    }

    //按主键删除
    @RequestMapping("/deleteUR")
    @ResponseBody
    public String deleteUserrole(String id){
        int Id,flag;
        Id=Integer.parseInt(id);
        flag=urService.deleteByPrimaryKey(Id);
        if(flag==0){
            message="删除失败！";
        }else
            message="删除成功！";
        return message;
    }

    //按主键修改
    @RequestMapping("/updateUR")
    @ResponseBody
    public String updateUR(String hid,String uid,String rid){
        int Id,flag;
        Id=Integer.parseInt(hid);
        Userrole ur=new Userrole();
        ur.setId(Id);
        ur.setuId(uid);
        ur.setrId(rid);
        flag=urService.updateByPrimaryKey(ur);
        if (flag==0){
            message="修改失败！";
        }else{
            message="修改成功！";
        }
        return message;
    }
}

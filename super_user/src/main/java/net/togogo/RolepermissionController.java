package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.Rolepermission;
import net.togogo.entity.RolepermissionExample;
import net.togogo.serviceimpl.RolepermissionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RolepermissionController {
    @Resource
    private RolepermissionServiceImpl rpService;
    public String message;

    //查询所有角色关联权限
    @RequestMapping("/getRolePermission")
    public String getRolepermission(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        }
        System.out.println(pageNum);
        RolepermissionExample example=new RolepermissionExample();
        PageHelper.startPage(pageNum,5);
        List<Rolepermission> rps=rpService.selectByExample(example);
        PageInfo<Rolepermission> pageInfo=new PageInfo<>(rps);
        model.addAttribute("rps",rps);
        model.addAttribute("page",pageInfo);
        return "rolepermission";
    }

    //模糊查询
    @RequestMapping("/searchRP")
    public String searchRP(String key){
        RolepermissionExample example=new RolepermissionExample();
        RolepermissionExample.Criteria cri=example.createCriteria();
        cri.andRIdLike("%"+key+"%");
        example.or().andPIdLike("%"+key+"%");
        List<Rolepermission> rp=rpService.selectByExample(example);
        return "";
    }

    //按主键删除
    @RequestMapping("/deleteRP")
    @ResponseBody
    public String deleteRolepermission(String id){
        int Id,flag;
        Id=Integer.parseInt(id);
        flag=rpService.deleteByPrimaryKey(Id);
        if(flag==0){
            message="删除失败，请注意删除信息！";
        }else {
            message="删除成功！";
        }
        return message;
    }

    //增加
    @RequestMapping("/createRP")
    @ResponseBody
    public String createRolepermission(String createRPrid,String createRPpid){
        int f,flag;
        Rolepermission rp=new Rolepermission();
        f=rpService.check(createRPrid,createRPpid);
        if (f==0){
            message="添加失败，信息已存在！";
        }else {
            rp.setrId(createRPrid);
            rp.setpId(createRPpid);
            flag=rpService.insert(rp);
            if(flag==0){
                message="添加失败！";
            }else {
                message="添加成功";
            }
        }
        return message;
    }

    //按主键部分修改
    @RequestMapping("/updateRP")
    @ResponseBody
    public String updateRP(String hid,String rid,String pid){
        int Id,flag;
        Id=Integer.parseInt(hid);
        Rolepermission rp=new Rolepermission();
        rp.setId(Id);
        rp.setrId(rid);
        rp.setpId(pid);
        flag=rpService.update(rp);
        if (flag==0){
            message="修改失败！";
        }else{
            message="修改成功！";
        }
       return message;
    }
}

package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.Permission;
import net.togogo.entity.PermissionExample;
import net.togogo.serviceimpl.PermissionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class PermissionController {
    @Resource
    private PermissionServiceImpl permissionService;
    public String message=null;

    //查询所有权限信息
    @RequestMapping("/getPermission")
    public String getPermission(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        }
        PermissionExample example=new PermissionExample();
        PageHelper.startPage(pageNum,5);
        List<Permission> permissions=permissionService.selectByExample(example);
        PageInfo pageInfo=new PageInfo<>(permissions);
        model.addAttribute("permissions",permissions);
        model.addAttribute("page",pageInfo);
        return "permission";
    }

    //模糊查询
    @RequestMapping("/searchpermission")
    public String searchPermission(String key){
        PermissionExample example=new PermissionExample();
        PermissionExample.Criteria cri=example.createCriteria();
        cri.andPIdLike("%"+key+"%");
        example.or().andPNameLike("%"+key+"%");
        example.or().andPDetailLike("%"+key+"%");
        List<Permission> permissions=permissionService.selectByExample(example);
        return "";
    }

    //添加权限
    @RequestMapping("/createpermission")
    @ResponseBody
    public String createPermission(String createPid,String createPname,String createPdetail){
       Permission permission=new Permission();
        int f,flag;
        //创建时间
        CommonController common=new CommonController();
        Date time=common.createtime();
        flag=permissionService.check(createPid);
        if ((flag == 1)) {
            message="添加失败！权限已存在！";
        }else {
            permission.setpId(createPid);
            permission.setpName(createPname);
            permission.setpDetail(createPdetail);
            permission.setCreatetime(time);
            f=permissionService.insert(permission);
            if(f==0){
                message="添加失败！";
            }else {
                message="添加成功";
            }
        }
        return message;
    }

    //按主键删除权限
    @RequestMapping("/deletepermission")
    @ResponseBody
    public String deletePermission(String id){
        int Id,flag;
        Id=Integer.parseInt(id);
        flag=permissionService.deleteByPrimaryKey(Id);
        if(flag==0){
            message="删除失败！";
        }else
            message="删除成功";
       return message;
    }

    //按主键部分更新
    @RequestMapping("/updatepermission")
    @ResponseBody
    public String updatePermission(String hid,String pId,String pName,String pDetail){
        int Id,flag;
        Id=Integer.parseInt(hid);
        Permission permission=new Permission();
        permission.setId(Id);
        permission.setpId(pId);
        permission.setpName(pName);
        permission.setpDetail(pDetail);
        flag=permissionService.updateByPrimaryKeySelective(permission);
        if (flag==0){
            message="修改失败！";
        }else{
            message="修改成功！";
        }
        return message;
    }


}

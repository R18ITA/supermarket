package net.togogo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.entity.*;
import net.togogo.serviceimpl.RolesServiceImpl;
import net.togogo.serviceimpl.UserroleServiceImpl;
import net.togogo.serviceimpl.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UsersController {
    @Resource
    private UsersServiceImpl usersService;
    @Resource
    private RolesServiceImpl rolesService;
    @Resource
    private UserroleServiceImpl urService;

    public String message=null;
    private String adminRID="001";

    //查询管理员用户
    @RequestMapping("/getAdmin")
    public String getAdmin(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        };
        //获取角色中管理员id
        String admin="系统管理员";
        Roles roles=  rolesService.getKey(admin);
        String adminId=roles.getrId();

        //通过刚得到的r_id去获得userrole表中管理员的u_id
        List<Userrole> UR= urService.getKey(adminId);

        UsersExample usersExample = new UsersExample();
        for(int i=0;i<UR.size();i++)
        {
            usersExample.or().andUIdEqualTo(UR.get(i).getuId());
        }
        //分页
        PageHelper.startPage(pageNum,5);
        List<Users> admins = usersService.selectByExample(usersExample);
        PageInfo<Users> pageInfo=new PageInfo<Users>(admins);
        model.addAttribute("admins",admins);
        model.addAttribute("page",pageInfo);
        return "admin";
    }

    //查询普通用户
    @RequestMapping("/getUser")
    public String getUser(Model model, Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        };
        //获取角色中管理员id
        String admin="系统管理员";
        Roles roles=  rolesService.getKey(admin);
        String adminId=roles.getrId();

        List<Userrole> UR=urService.getUser(adminId);
        UsersExample example=new UsersExample();
        for (int i=0;i<UR.size();i++){
            example.or().andUIdNotEqualTo(UR.get(i).getuId());
        }
        PageHelper.startPage(pageNum,5);
//        List<Users> users=usersService.selectByExample(example);
        List<Users> users = usersService.selectByExample(example);
        PageInfo<Users> pageInfo=new PageInfo<>(users);
        model.addAttribute("users",users);
        model.addAttribute("page",pageInfo);
        return "user";
    }

    //模糊查询
    @RequestMapping("/searchuser")
    public String searchUser(String key){
        UsersExample example=new UsersExample();
        UsersExample.Criteria cri=example.createCriteria();
        cri.andUIdLike("%"+key+"%");
        example.or().andUNameLike("%"+key+"%");
        example.or().andUPasswordLike("%"+key+"%");
        example.or().andUPhoneLike("%"+key+"%");
        List<Users> users=usersService.selectByExample(example);
        return "";
    }

    //添加用户
    @RequestMapping("/createuser")
    @ResponseBody
    public String  createUser(String createuId,String createuName, String createuPassword,String createuPhone,int usertype){
        Users users=new Users();
        int f,flag;
        int c=0;
        //创建时间
        CommonController common=new CommonController();
        Date time=common.createtime();
        flag=usersService.checkuser(createuId);
        if (flag==1){
            message="添加失败，所添加的用户账号已存在！";
        }else {
            users.setuId(createuId);
            users.setuName(createuName);
            users.setuPassword(createuPassword);
            users.setuPhone(createuPhone);
            users.setCreatetime(time);
            f=usersService.insert(users);
            if (usertype==0){
                Roles roles=  rolesService.getKey("系统管理员");
                String rId=roles.getrId();
                c=addUser(createuId,rId);

            }else {
                c=addUser(createuId,"000");//普通用户默认角色编号为000
            }
            if(f==1&&c==1){
                message="添加成功！";
            }else {
                message="添加失败";
            }
        }
        return message;
    }
    //添加用户的同时添加该用户的角色
    public int addUser(String uid,String rid){
        Userrole userrole=new Userrole();
        int flag;
        userrole.setuId(uid);
        userrole.setrId(rid);
        flag=urService.insert(userrole);
        if(flag==0){
            return 0;
        }else
            return 1;
    }

    //删除用户（按主键删除用户）
    @RequestMapping("/deleteuser")
    @ResponseBody
    public String deleteUser(String id,String uId){
        int Id,flag1,flag2;
        Id=Integer.parseInt(id);
        flag1=usersService.deleteByPrimaryKey(Id);
        flag2=urService.delete(uId);//同时删除userrole表中的记录
        if(flag1!=0&&flag2!=0){
            message="删除成功！";
        }else {
            message="删除失败，请注意删除信息！";
        }
        return message;
    }

    //修改
    @RequestMapping("/updateuser")
    @ResponseBody
    public String updateUser(Integer id,String uId,String uName,String uPassword,String uPhone){
        int flag,flag1;
        Users users=usersService.selectByPrimaryKey(id);//查询修改前的用户信息，主要是u_id
        System.out.println(users.getuId());
        //修改user表
        Users user=new Users();
        user.setId(id);
        user.setuName(uName);
        user.setuPassword(uPassword);
        user.setuPhone(uPhone);
        flag=usersService.updateByPrimaryKeySelective(user);
        //同时修改userrole表
        flag1=updateURbyuid(users.getuId(),uId);
        System.out.println(flag);
        if (flag==1&&flag1==1){
            message="修改成功！";
        }else{
            message="修改失败！";
        }
        System.out.println("修改修改修改");
        return message;
    }
    //同时修改userrole表
    public int updateURbyuid(String uid,String uId){//uid是原u_id,uId是修改后的
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uid);
        Userrole userrole=new Userrole();
        userrole.setuId(uId);
        int flag;
        flag=urService.updateByExampleSelective(userrole,example);
        return flag;
    }


    //检验用户是否存在
    @RequestMapping("/checkout")
    @ResponseBody
    public int checkout(HttpSession session, String uid, String password){
        int flag=0;
        List<Users> users=usersService.logincheck(uid,password);
        flag=users.size();
        System.out.println("flag: checkout: "+flag);
        if (flag!=0){//不为0，则存在
            session.setAttribute("user",users.get(0));
        }
        return flag;
    }

    //检验用户是否为管理员
//通过uid去角色表中查询是否为管理员
    @RequestMapping("/checkadmin")
    @ResponseBody
    public int checkadmin(String uid){
        List<Userrole> ur=urService.getRid(uid);
        if((ur.get(0).getrId()).equals(adminRID)){
            return 0;
        }else
        {
            return 1;

        }
    }
}

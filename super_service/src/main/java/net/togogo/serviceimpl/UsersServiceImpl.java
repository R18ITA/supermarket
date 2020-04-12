package net.togogo.serviceimpl;

import net.togogo.entity.Users;
import net.togogo.entity.UsersExample;
import net.togogo.mapper.UsersMapper;
import net.togogo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements IUserService {
    @Resource
    private UsersMapper usersMapper;
//    private RolesMapper rolesMapper;

    //查询所有用户（普通用户和管理员用户）
    public List<Users> selectByExample(UsersExample example){
        return usersMapper.selectByExample(example);
    }

    //通过主键查询
    public Users selectByPrimaryKey(Integer id){
        return usersMapper.selectByPrimaryKey(id);
    }

    //由u_id查询用户user
    //查询管理员用户
    public Users getKey(String uid){
        UsersExample example=new UsersExample();
        UsersExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uid);
        List<Users> result=usersMapper.selectByExample(example);
        return result.get(0);
    }

    //按用户账号查询用户是否已经存在
    public int checkuser(String uId){
        UsersExample example=new UsersExample();
        UsersExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uId);
        List<Users> users=usersMapper.selectByExample(example);
        if(users!=null&& users.size()>0){
            return 1;
        }else
            return 0;
    }

    //增加用户
    @Override
    public int insert(Users record){
        return usersMapper.insert(record);
    }

    //按主键删除用户
    public int deleteByPrimaryKey(Integer id){
        return usersMapper.deleteByPrimaryKey(id);
    }

    //修改按主键全部修改
    public int updateByPrimaryKey(Users record){
        return usersMapper.updateByPrimaryKey(record);
    }

    //按主键部分更新
    public int updateByPrimaryKeySelective(Users record){
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    //验证该用户是否存在
    public List<Users> logincheck(String uid, String password){
        UsersExample example=new UsersExample();
        UsersExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uid);
        cri.andUPasswordEqualTo(password);
        List<Users> users=usersMapper.selectByExample(example);
        return users;//因为UID有唯一性，所以查出来的List，若用户存在的话，那List只有一个user
    }
}

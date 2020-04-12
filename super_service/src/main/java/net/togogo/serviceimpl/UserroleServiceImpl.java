package net.togogo.serviceimpl;


import net.togogo.entity.Userrole;
import net.togogo.entity.UserroleExample;
import net.togogo.mapper.UserroleMapper;
import net.togogo.service.IUserroleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserroleServiceImpl implements IUserroleService {
    @Resource
    private UserroleMapper urMapper;

    //查询所有的用户关联角色
    @Override
    public List<Userrole> selectByExample(UserroleExample example){
        return urMapper.selectByExample(example);
    }

    public List<Userrole> getRid(String uid){
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uid);
        List<Userrole> ur=urMapper.selectByExample(example);
        return ur;
    }

    //由R_id获取对象
    //由r_id查userrole得u_id
    @Override
    public List<Userrole> getKey(String rid){
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andRIdEqualTo(rid);
        List<Userrole> result=urMapper.selectByExample(example);
        return result;
    }

    //查询不等于管理员用户的角色id的用户（即普通用户）
    public List<Userrole> getUser(String rid){
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andRIdNotEqualTo(rid);
        List<Userrole> result=urMapper.selectByExample(example);
        return result;
    }

    //检查用户关联角色是否已经存在
    public int check(String uid,String rid){
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andRIdEqualTo(rid);
        cri.andUIdEqualTo(uid);
        List<Userrole> userroles=urMapper.selectByExample(example);
        if (userroles!=null&&userroles.size()>0){
            return 1;
        }else
            return 0;
    }

    //增加
    public  int insert(Userrole record){
        return urMapper.insert(record);
    }

    //按主键删除
    public int deleteByPrimaryKey(Integer id){
        System.out.println(id);
        return  urMapper.deleteByPrimaryKey(id);
    }

    //按条件删除
    public int delete(String uId){
        int flag=0;
        UserroleExample example=new UserroleExample();
        UserroleExample.Criteria cri=example.createCriteria();
        cri.andUIdEqualTo(uId);
        flag=urMapper.deleteByExample(example);
        return flag;
    }

    //按主键修改
    public int updateByPrimaryKey(Userrole record){
        return urMapper.updateByPrimaryKey(record);
    }

    public int updateByExampleSelective(@Param("record") Userrole record, @Param("example") UserroleExample example){
        return urMapper.updateByExampleSelective(record,example);
    }
}

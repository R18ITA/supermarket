package net.togogo.serviceimpl;

import net.togogo.entity.Roles;
import net.togogo.entity.RolesExample;
import net.togogo.mapper.RolesMapper;
import net.togogo.service.IRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService {
    @Resource
    private RolesMapper rolesMapper;

    //查询所有角色信息
    public  List<Roles> selectByExample(RolesExample example){
      return  rolesMapper.selectByExample(example);
  }

    //按主键查询角色信息
    public Roles selectByPrimaryKey(Integer id){
        return rolesMapper.selectByPrimaryKey(id);
    }

    //由角色名获取管理员id
    @Override
    public Roles getKey(String rname){
        RolesExample example=new RolesExample();
        RolesExample.Criteria cri=example.createCriteria();
        cri.andRNameEqualTo(rname);
        List<Roles> result=rolesMapper.selectByExample(example);
        return result.get(0);
    }

    //增加角色
    @Override
    public int insert(Roles record){
      return rolesMapper.insert(record);
    }

    //删除角色
    public int deleteByPrimaryKey(Integer id){
      return rolesMapper.deleteByPrimaryKey(id);
    }

    //按照角色id检查角色是否存在
    public int checkeRole(String rid){
        RolesExample example=new RolesExample();
        RolesExample.Criteria cri=example.createCriteria();
        cri.andRNameEqualTo(rid);
        List<Roles> roles=rolesMapper.selectByExample(example);
        if(roles!=null&&roles.size()>0){
            return 1;
        }else
            return 0;
  }

  //按照主键部分更新
    public  int updateByPrimaryKeySelective(Roles record){
      return rolesMapper.updateByPrimaryKeySelective(record);
    }


}

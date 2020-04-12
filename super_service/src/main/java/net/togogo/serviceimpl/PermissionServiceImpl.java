package net.togogo.serviceimpl;


import net.togogo.entity.Permission;
import net.togogo.entity.PermissionExample;
import net.togogo.mapper.PermissionMapper;
import net.togogo.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    //查询所有权限信息
    @Override
    public List<Permission> selectByExample(PermissionExample example){
        return permissionMapper.selectByExample(example);
    }

    //增加权限
    public int insert(Permission record){
        return permissionMapper.insert(record);
    }

    //删除权限
    public int deleteByPrimaryKey(Integer id){
        return permissionMapper.deleteByPrimaryKey(id);
    }

    //按主键部分修改
    public int updateByPrimaryKeySelective(Permission record){
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    //检查权限是否已经存在
    public int check(String pid){
        int flag;
        PermissionExample example=new PermissionExample();
        PermissionExample.Criteria cri=example.createCriteria();
        cri.andPIdEqualTo(pid);
        List<Permission> permissions=permissionMapper.selectByExample(example);
        if(permissions!=null&&permissions.size()>0){
            return 1;
        }else
            return 0;
    }
}

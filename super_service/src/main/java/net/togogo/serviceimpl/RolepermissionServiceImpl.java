package net.togogo.serviceimpl;


import net.togogo.entity.Rolepermission;
import net.togogo.entity.RolepermissionExample;
import net.togogo.mapper.RolepermissionMapper;
import net.togogo.service.IRolepermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolepermissionServiceImpl implements IRolepermissionService {
    @Resource
    private RolepermissionMapper rpMapper;

    //查询所有角色关联权限信息
    public List<Rolepermission> selectByExample(RolepermissionExample example){
        return  rpMapper.selectByExample(example);
    }

    //删除
    public int deleteByPrimaryKey(Integer id){
        return rpMapper.deleteByPrimaryKey(id);
    }

    public int delete(String rid){
        int flag=0;
        RolepermissionExample example=new RolepermissionExample();
        RolepermissionExample.Criteria cri=example.createCriteria();
        cri.andRIdEqualTo(rid);
        flag=rpMapper.deleteByExample(example);
        return flag;
    }

    //增加
    public int insert(Rolepermission record){
        return rpMapper.insert(record);
    }

    //选择性增加
     public int insertSelective(Rolepermission record){
        return rpMapper.insertSelective(record);
    }

    //按主键部分更新
    public int update(Rolepermission record){
        return rpMapper.updateByPrimaryKeySelective(record);
    }

    //条件性部分更新
    public int updateByExampleSelective(@Param("record") Rolepermission record, @Param("example") RolepermissionExample example){
        return rpMapper.updateByExampleSelective(record,example);
    }


    //检查是否已经存在关系
    public int check(String rid,String pid){
        RolepermissionExample example=new RolepermissionExample();
        RolepermissionExample.Criteria cri=example.createCriteria();
        cri.andRIdEqualTo(rid);
        cri.andPIdEqualTo(pid);
        List<Rolepermission> rolepermissions=rpMapper.selectByExample(example);
        if(rolepermissions!=null&&rolepermissions.size()>0){
            return 1;
        }else
            return 0;
    }

}

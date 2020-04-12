package net.togogo.service;

import net.togogo.entity.Userrole;
import net.togogo.entity.UserroleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserroleService {

    List<Userrole> selectByExample(UserroleExample example);

    List<Userrole> getKey(String rid);

    List<Userrole> getRid(String uid);

    int insert(Userrole record);

    int deleteByPrimaryKey(Integer id);

    int delete(String uId);

    int updateByPrimaryKey(Userrole record);

    int updateByExampleSelective(@Param("record") Userrole record, @Param("example") UserroleExample example);
}

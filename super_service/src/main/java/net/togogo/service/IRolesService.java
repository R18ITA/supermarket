package net.togogo.service;

import net.togogo.entity.Roles;
import net.togogo.entity.RolesExample;

import java.util.List;

public interface IRolesService {
    List<Roles> selectByExample(RolesExample example);

    Roles getKey(String rname);

    int insert(Roles record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

}

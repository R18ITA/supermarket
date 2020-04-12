package net.togogo.service;

import net.togogo.entity.Permission;
import net.togogo.entity.PermissionExample;

import java.util.List;

public interface IPermissionService {

    List<Permission> selectByExample(PermissionExample example);

    int insert(Permission record);

    int updateByPrimaryKeySelective(Permission record);

    int deleteByPrimaryKey(Integer id);
}

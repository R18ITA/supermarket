package net.togogo.service;

import net.togogo.entity.Rolepermission;
import net.togogo.entity.RolepermissionExample;

import java.util.List;

public interface IRolepermissionService {
    List<Rolepermission> selectByExample(RolepermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int delete(String rid);

    int insert(Rolepermission record);

    int check(String rid, String pid);

    int update(Rolepermission record);

    int insertSelective(Rolepermission record);
}

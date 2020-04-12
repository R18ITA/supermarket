package net.togogo.service;

import net.togogo.entity.Users;
import net.togogo.entity.UsersExample;

import java.util.List;

public interface IUserService {
    List<Users> selectByExample(UsersExample example);

    Users getKey(String uid);

    int insert(Users record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Users record);

    int updateByPrimaryKeySelective(Users record);
}

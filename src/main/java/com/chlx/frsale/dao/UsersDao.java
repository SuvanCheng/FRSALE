package com.chlx.frsale.dao;

import com.xiaowang.mesqle.pojo.User;

import java.util.List;

public interface UsersDao {
    void insertUsers(User users);
    User login(User user);
    void updateUserType(User user);
    List<User> selectAllUser();
    User selectUserById(int id);
}

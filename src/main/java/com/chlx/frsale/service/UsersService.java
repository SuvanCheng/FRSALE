package com.chlx.frsale.service;

import com.chlx.frsale.pojo.User;

import java.util.List;

public interface UsersService {
    void addUsers(User users);
    User login(User user);
    void updateUserType(User user);
    List<User> findAllUser();
    User findUserById(int id);
}

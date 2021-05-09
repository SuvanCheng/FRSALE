package com.xiaowang.mesqle.service.impl;

import com.xiaowang.mesqle.dao.UsersDao;
import com.xiaowang.mesqle.pojo.User;
import com.xiaowang.mesqle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理业务层
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void addUsers(User users) {
        this.usersDao.insertUsers(users);
    }

    @Override
    public User login(User user) {
        return this.usersDao.login(user);
    }
    public void updateUserType(User user){
        this.usersDao.updateUserType(user);
    }

    @Override
    public List<User> findAllUser() {
        return this.usersDao.selectAllUser();
    }

    @Override
    public User findUserById(int id) {
        return this.usersDao.selectUserById(id);
    }
}

package com.xmx.service.impl;

import com.xmx.dao.UserDao;
import com.xmx.dao.impl.UserDaoImpl;
import com.xmx.pojo.User;
import com.xmx.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> find() {
        return userDao.find();
    }

    @Override
    public User findByUserId(int id) {
        return userDao.findByUserId(id);
    }

    @Override
    public User login(String username, String pwd) {
        return userDao.login(username, pwd);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int del(int id) {
        return userDao.del(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}

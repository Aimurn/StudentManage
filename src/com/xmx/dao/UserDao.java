package com.xmx.dao;

import com.xmx.pojo.User;

import java.util.List;

public interface UserDao {
    public List<User> find();

    public User findByUserId(int id);

    public User login(String username, String pwd);

    public int add(User user);

    public int add1(User user);

    public int del(int id);

    public int update(User user);

    public List<User> find1( String username,int type);
}

package com.xmx.service;

import com.xmx.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> find();

    public User findByUserId(int id);

    public User login(String username, String pwd);

    public int add(User user);

    public int del(int id);

    public int update(User user);
}

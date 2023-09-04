package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.UserDao;
import com.xmx.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> find() {
        List<User> list = new ArrayList<>();
        String sql = "select * from user where state=1";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPwd(resultSet.getString(3));
                user.setImage(resultSet.getString(4));
                user.setState(resultSet.getInt(5));
                user.setType(resultSet.getInt(6));
                user.setSFtype(resultSet.getInt(7));
                list.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public User findByUserId(int id) {
        String sql = "select * from user where id=? and state =1";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPwd(resultSet.getString("pwd"));
                user.setImage(resultSet.getString("image"));
                user.setState(resultSet.getInt("state"));
                //管理员类型
                user.setType(resultSet.getInt("type"));
                //管理员身份
                user.setSFtype(resultSet.getInt("SFtype"));
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    //管理员登录判断
    @Override
    public User login(String username, String pwd) {
        String sql = "select * from user where username = ? and pwd = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, username);
            statement.setObject(2, pwd);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPwd(resultSet.getString("pwd"));
                user.setImage(resultSet.getString("image"));
                user.setState(resultSet.getInt("state"));
                //管理员类型
                user.setType(resultSet.getInt("type"));
                //管理员身份
                user.setSFtype(resultSet.getInt("SFtype"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public int add(User user) {
        String sql = "insert into user values(null,?,?,null,1,?,2)";
        return BaseDao.executeUpdate(sql, user.getUsername(), user.getPwd(), user.getType());
    }

    @Override
    public int add1(User user) {
        String sql = "insert into user values(null,?,?,null,1,?,2)";
        return BaseDao.executeUpdate(sql, user.getUsername(), user.getPwd(), user.getType());
    }
    @Override
    public int del(int id) {
        String sql = "update  user set state=0 where id=?";
        return BaseDao.executeUpdate(sql, id);
    }

    @Override
    public int update(User user) {
        String sql = "update user set username=?,pwd=? where id=?";
        return BaseDao.executeUpdate(sql, user.getUsername(), user.getPwd(), user.getId());
    }

    //存图片
    public int saveImg(int no, String saveFile) {
        String sql = "update user set image=? where id=?";
        return BaseDao.executeUpdate(sql, saveFile, no);
    }

    //带搜素条件
    @Override
    public List<User> find1( String username,int type) {
        List<User> list = new ArrayList<>();
        String sql = "select * from user where state=1";
        if(username !=""){
            sql +=" and username like '%"+username+"%'";
        }
        if(type !=2){
            sql +=" and type="+type;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPwd(resultSet.getString(3));
                user.setImage(resultSet.getString(4));
                user.setState(resultSet.getInt(5));
                user.setType(resultSet.getInt(6));
                user.setSFtype(resultSet.getInt(7));
                list.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }
}

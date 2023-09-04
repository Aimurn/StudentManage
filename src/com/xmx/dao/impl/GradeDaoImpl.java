package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.GradeDao;
import com.xmx.pojo.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl implements GradeDao {

    @Override
    public List<Grade> getGrades() {
        List<Grade> list = new ArrayList<>();
        String sql = "select * from Grade where state =1 ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setgId(resultSet.getInt(1));
                grade.setgName(resultSet.getString(2));
                grade.setState(resultSet.getInt(3));
                list.add(grade);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Grade findByGradeId(int gId) {
        String sql = "select * from Grade where gId=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, gId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Grade grade = new Grade();
                grade.setgId(resultSet.getInt(1));
                grade.setgName(resultSet.getString(2));
                grade.setState(resultSet.getInt(3));
                return grade;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public int add(Grade grade) {
        String sql = "insert into grade values(null,?,1)";
        return BaseDao.executeUpdate(sql, grade.getgName());
    }

    @Override
    public int update(Grade grade) {
        String sql = "update grade set gName=? where gId=?";
        return BaseDao.executeUpdate(sql, grade.getgName(), grade.getgId());
    }

    @Override
    public int del(int gId) {
        String sql = "update  grade set state=0 where gId=?";
        return BaseDao.executeUpdate(sql, gId);
    }
}

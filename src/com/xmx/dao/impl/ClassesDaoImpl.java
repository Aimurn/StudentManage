package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.ClassesDao;
import com.xmx.pojo.Classes;
import com.xmx.pojo.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesDaoImpl implements ClassesDao {
    @Override
    //班级信息，带搜索年级编号条件
    public List<Classes> pages(int index, int size, int gId) {
        List<Classes> list = new ArrayList<>();
        String sql = "select * from classes where 1=1 and state=1";
        if (gId != 0) {
            sql += " and gId=" + gId;
        }

        //分页参数
        sql += " limit ?,?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            //赋值占位符
            statement.setObject(1, (index - 1) * size);
            statement.setObject(2, size);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Classes classes1 = new Classes();
                classes1.setClassId(resultSet.getInt(1));
                classes1.setClassName(resultSet.getString(2));

                int gId1 = resultSet.getInt(3);
                Grade grade = new GradeDaoImpl().findByGradeId(gId1);
                classes1.setGrade(grade);
                //classes1.setState(resultSet.getInt(4));
                list.add(classes1);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Classes> getClasses() {
        List<Classes> list = new ArrayList<>();
        String sql = "select * from classes where state=1 ";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Classes classes1 = new Classes();
                classes1.setClassId(resultSet.getInt(1));
                classes1.setClassName(resultSet.getString(2));

                int gId1 = resultSet.getInt(3);
                Grade grade = new GradeDaoImpl().findByGradeId(gId1);
                classes1.setGrade(grade);
                // classes1.setState(resultSet.getInt(4));
                list.add(classes1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    //通过班级id找班级信息
    public Classes findByClassId(int classId) {
        Classes classes2 = new Classes();
        String sql = "select * from classes where classId=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            // 占位符赋值
            statement.setObject(1, classId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                classes2.setClassId(resultSet.getInt(1));
                classes2.setClassName(resultSet.getString(2));
                // 找年级名称
                int gId1 = resultSet.getInt(3);
                Grade grade = new GradeDaoImpl().findByGradeId(gId1);
                classes2.setGrade(grade);

                classes2.setState(resultSet.getInt(4));
                return classes2;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Classes> findClassBygId(int gId) {
        List<Classes> list = new ArrayList<>();
        String sql = "select * from classes where state=1 ";
        if (gId != 0) {
            sql += " and gId =? ";
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            if (gId != 0) {
                statement.setObject(1, gId);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Classes classes1 = new Classes();
                classes1.setClassId(resultSet.getInt(1));
                classes1.setClassName(resultSet.getString(2));
                // classes1.setState(resultSet.getInt(4));
                list.add(classes1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    //页数，带年级id条件
    public int count(int gId) {
        String sql = "select count(1) from classes where 1=1 and state=1 ";
        if (gId != 0) {
            sql += "and gId=" + gId;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    //增肌班级
    public int add(Classes classes) {
        String sql = "insert into classes values(null,?,?,1)";
        return BaseDao.executeUpdate(sql, classes.getClassName(), classes.getGrade().getgId());
    }

    @Override
    //编辑班级
    public int update(Classes classes) {
        String sql = "update classes set className=?,gId=? where classId=?";
        return BaseDao.executeUpdate(sql, classes.getClassName(), classes.getGrade().getgId(), classes.getClassId());
    }

    //删除班级信息
    @Override
    public int del(int classId) {
        String sql = "update classes set state=0 where classId=?";
        return BaseDao.executeUpdate(sql, classId);
    }
}

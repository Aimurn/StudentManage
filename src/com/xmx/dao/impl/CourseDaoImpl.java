package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.CourseDao;
import com.xmx.pojo.Course;
import com.xmx.pojo.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> getCourse() {
        List<Course> list = new ArrayList<>();
        String sql = "select * from course where state=1 ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setcId(resultSet.getInt(1));
                course.setcName(resultSet.getString(2));
                course.setcImage(resultSet.getString(3));
                course.setHour(resultSet.getInt(4));
                //找年级名称
                int gid1 = resultSet.getInt(5);
                Grade grade = new GradeDaoImpl().findByGradeId(gid1);
                course.setGrade(grade);
                list.add(course);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }

        return list;
    }

    @Override
    //科目信息，带年级编号跟科目名搜索条件
    public List<Course> pages(int index, int size, String cName, int gId) {
        List<Course> list = new ArrayList<>();
        String sql = "select * from course where 1=1 and state=1";
        if (gId != 0) {
            sql += " and gId=" + gId;
        }
        if (cName != "") {
            sql += " and cName like '%" + cName + "%'";
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
                Course course = new Course();
                course.setcId(resultSet.getInt(1));
                course.setcName(resultSet.getString(2));
                course.setcImage(resultSet.getString(3));
                course.setHour(resultSet.getInt(4));
                //找年级名称
                int gid1 = resultSet.getInt(5);
                Grade grade = new GradeDaoImpl().findByGradeId(gid1);
                course.setGrade(grade);
                list.add(course);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }

        return list;
    }


    @Override
    //通过科目id找科目信息
    public Course findByCourseId(int cId) {
        Course course = new Course();
        String sql = "select * from course where cId =?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            //占位符赋值
            statement.setObject(1, cId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                course.setcId(resultSet.getInt(1));
                course.setcName(resultSet.getString(2));
                course.setcImage(resultSet.getString(3));
                course.setHour(resultSet.getInt(4));
                //找年级名称
                int gId1 = resultSet.getInt(5);
                Grade grade = new GradeDaoImpl().findByGradeId(gId1);
                course.setGrade(grade);

                return course;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    //带gId年级id条件页数
    public int count(String cName, int gId) {
        String sql = "select count(1) from course where 1=1 and state=1 ";
        if (gId != 0) {
            sql += " and gId=" + gId;
        }
        if (cName != "") {
            sql += " and cName like '%" + cName + "%'";
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
    //增加科目
    public int add(Course course) {
        String sql = "insert into course values(null,?,?,?,?,null) ";
        return BaseDao.executeUpdate(sql, course.getcName(), course.getcImage()
                , course.getHour(), course.getGrade().getgId());
    }

    //编辑科目
    @Override
    public int update(Course course) {
        String sql = "update course set cName=?,hour=?,gId=? where cId=?";
        return BaseDao.executeUpdate(sql, course.getcName(), course.getHour(),
                course.getGrade().getgId(),
                course.getcId());
    }

    //删除科目
    @Override
    public int del(int cId) {
        String sql = "update course set state=0 where cId=?";
        return BaseDao.executeUpdate(sql, cId);

    }

    //存图片
    public int updateByImg(Course course) {
        String sql = "update course set cName=?, hour=?, gId=? where cName='a1a' and gId=1 and state=1";
        return BaseDao.executeUpdate(sql, course.getcName(), course.getHour(), course.getGrade().getgId());
    }

    //编辑修改时
    public int updateImg(String saveFile, Course course) {
        String sql = "update course set cImage=? where cId=? and state=1";
        return BaseDao.executeUpdate(sql, saveFile, course.getcId());
    }

    public int saveImg(String saveFile) {
        String sql = "insert into course(cName,cImage,gId,state) values('a1a',?,1,1)";
        return BaseDao.executeUpdate(sql, saveFile);
    }
}

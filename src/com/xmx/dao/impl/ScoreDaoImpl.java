package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.ScoreDao;
import com.xmx.pojo.Course;
import com.xmx.pojo.Grade;
import com.xmx.pojo.Score;
import com.xmx.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl implements ScoreDao {
    @Override
    public List<Score> pages(int index, int size, int stuNo, int cId) {
        List<Score> list = new ArrayList<>();
        String sql = "select * from score where 1=1 and state=1 ";
        if (stuNo != 0) {
            sql += " and no=" + stuNo;
        }
        if (cId != 0) {
            sql += " and cid=" + cId;
        }
        // 分页参数
        sql += " limit ?,?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            // 赋值占位符
            statement.setObject(1, (index - 1) * size);
            statement.setObject(2, size);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Score score = new Score();

                int stuno = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(stuno);
                score.setStudent(student);

                int courseid = resultSet.getInt(2);
                Course course = new CourseDaoImpl().findByCourseId(courseid);
                score.setCourse(course);

                score.setScore(resultSet.getInt(3));
                list.add(score);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Score> getScore() {
        List<Score> list = new ArrayList<>();
        String sql = "select * from score where and state=1 ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Score score = new Score();

                int stuno = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(stuno);
                score.setStudent(student);

                int courseid = resultSet.getInt(2);
                Course course = new CourseDaoImpl().findByCourseId(courseid);
                score.setCourse(course);

                score.setScore(resultSet.getInt(3));
                list.add(score);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int count(int stuNo, int cId) {
        String sql = "select count(1) from score where 1=1 and state=1 ";
        if (stuNo != 0) {
            sql += " and no=" + stuNo;
        }
        if (cId != 0) {
            sql += " and cid=" + cId;
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
    public int add(Score score) {
        String sql = "insert into score values(?,?,?,1)";
        return BaseDao.executeUpdate(sql, score.getStudent().getStuNo(), score.getCourse().getcId(), score.getScore());
    }

    @Override
    public int update(Score score) {
        String sql = "update score set score=? where no=? and cid=? and state = 1";
        return BaseDao.executeUpdate(sql, score.getScore(), score.getStudent().getStuNo(), score.getCourse().getcId());
    }

    @Override
    public int del(int no, int cId) {
        String sql = "update score set state=0 where no=? and cid=?";
        return BaseDao.executeUpdate(sql, no, cId);
    }
}

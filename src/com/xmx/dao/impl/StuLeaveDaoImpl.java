package com.xmx.dao.impl;

import java.sql.PreparedStatement;

import com.xmx.dao.BaseDao;
import com.xmx.dao.StuLeaveDao;
import com.xmx.pojo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StuLeaveDaoImpl implements StuLeaveDao {
    @Override
    public List<StuLeave> pages(int index, int size, int approval, int no) {
        List<StuLeave> list = new ArrayList<>();
        //拼接搜索条件
        String sql = "select * from stuLeave where 1=1 and state =1 and id = " + no + "";
        if (approval != -1) {
            sql += " and approval=" + approval;
        }
        //拼接分页参数
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
                StuLeave stuLeave = new StuLeave();

                int sid = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(sid);
                stuLeave.setStudent(student);

                stuLeave.setName(resultSet.getString(2));

                int cId = resultSet.getInt(3);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                stuLeave.setClasses(class1);

                stuLeave.setReason(resultSet.getString(4));
                stuLeave.setStartTime(resultSet.getString(5));
                stuLeave.setEndTime(resultSet.getString(6));
                stuLeave.setApproval(resultSet.getInt(7));
                stuLeave.setState(resultSet.getInt(8));

                list.add(stuLeave);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<StuLeave> adpages(int index, int size, int approval, String name, int classId) {
        List<StuLeave> list = new ArrayList<>();
        //拼接搜索条件
        String sql = "select * from stuLeave where 1=1 and state =1 ";
        if (approval != -1) {
            sql += " and approval=" + approval;
        }
        if (name !=""){
            sql +=" and name like '%"+name+"%'";
        }
        if(classId !=0){
            sql +=" and classId="+classId;
        }
        //拼接分页参数
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
                StuLeave stuLeave = new StuLeave();

                int sid = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(sid);
                stuLeave.setStudent(student);

                stuLeave.setName(resultSet.getString(2));

                int cId = resultSet.getInt(3);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                stuLeave.setClasses(class1);

                stuLeave.setReason(resultSet.getString(4));
                stuLeave.setStartTime(resultSet.getString(5));
                stuLeave.setEndTime(resultSet.getString(6));
                stuLeave.setApproval(resultSet.getInt(7));
                stuLeave.setState(resultSet.getInt(8));

                list.add(stuLeave);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<StuLeave> getStuLeave() {
        List<StuLeave> list = new ArrayList<>();
        String sql = "select * from stuleave where state =1 ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StuLeave stuLeave = new StuLeave();

                int sid = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(sid);
                stuLeave.setStudent(student);

                stuLeave.setName(resultSet.getString(2));

                int cId = resultSet.getInt(3);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                stuLeave.setClasses(class1);

                stuLeave.setReason(resultSet.getString(4));
                stuLeave.setStartTime(resultSet.getString(5));
                stuLeave.setEndTime(resultSet.getString(6));
                stuLeave.setApproval(resultSet.getInt(7));
                stuLeave.setState(resultSet.getInt(8));

                list.add(stuLeave);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int count(int approval, int no) {
        String sql = "select count(1) from stuleave where 1=1 and state=1 and id = " + no + "";
        //拼接搜索条件
        if (approval != -1) {
            sql += " and approval=" + approval;
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
    public int adcount(int approval, String name,int classId) {
        String sql = "select count(1) from stuleave where 1=1 and state=1   ";
        //拼接搜索条件
        if (approval != -1) {
            sql += " and approval=" + approval;
        }
        if (name !=""){
            sql +=" and name like '%"+name+"%'";
        }
        if(classId !=0){
            sql +=" and classId="+classId;
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
    public StuLeave findId(int id) {
        String sql = "select * from stuleave where id=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                StuLeave stuLeave = new StuLeave();

                int sid = resultSet.getInt(1);
                Student student = new StudentDaoImpl().findById(sid);
                stuLeave.setStudent(student);

                stuLeave.setName(resultSet.getString(2));

                int cId = resultSet.getInt(3);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                stuLeave.setClasses(class1);

                stuLeave.setReason(resultSet.getString(4));
                stuLeave.setStartTime(resultSet.getString(5));
                stuLeave.setEndTime(resultSet.getString(6));
                stuLeave.setApproval(resultSet.getInt(7));
                stuLeave.setState(resultSet.getInt(8));
                return stuLeave;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public int add(StuLeave stuLeave) {
        String sql = "insert into stuleave values(?,?,?,?,?,?,0,1)";
        return BaseDao.executeUpdate(sql,stuLeave.getStudent().getStuNo(), stuLeave.getName(),
                stuLeave.getClasses().getClassId(), stuLeave.getReason(), stuLeave.getStartTime(),
                stuLeave.getEndTime());
    }

    @Override
    public int del(int id) {
        String sql = "update stuleave set state=0 where id=?";
        return BaseDao.executeUpdate(sql, id);
    }

    @Override
    public int update(StuLeave stuLeave) {
        String sql = "update stuleave set approval=? where id=? and startTime=? and endTime=?";
        return BaseDao.executeUpdate(sql, stuLeave.getApproval(), stuLeave.getStudent().getStuNo(),
                stuLeave.getStartTime(), stuLeave.getEndTime());
    }
}

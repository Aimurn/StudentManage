package com.xmx.dao.impl;

import com.xmx.dao.BaseDao;
import com.xmx.dao.StudentDao;
import com.xmx.pojo.Classes;
import com.xmx.pojo.Grade;
import com.xmx.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class StudentDaoImpl implements StudentDao {
    @Override
    //搜索条件带年级跟学生姓名
    public List<Student> pages(int index, int size, int gid, String name, int stuNo, int classId) {
        List<Student> list = new ArrayList<>();
        //拼接搜索条件
        String sql = "select * from student where 1=1 and state =1";
        if (gid != 0) {
            sql += " and gId=" + gid;
        }
        if (name != "") {
            sql += " and stuName like '%" + name + "%'";
        }
        if (stuNo != 0) {
            sql += " and stuNo=" + stuNo;
        }
        if (classId != 0) {
            sql += " and classId=" + classId;
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
                Student student = new Student();
                student.setStuNo(resultSet.getInt(1));
                student.setStuName(resultSet.getString(2));
                student.setStuImg(resultSet.getString(3));
                student.setPwd(resultSet.getString(4));
                student.setSex(resultSet.getString(5));

                int gId = resultSet.getInt(6);
                Grade grade = new GradeDaoImpl().findByGradeId(gId);
                student.setGrade(grade);

                int cId = resultSet.getInt(7);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                student.setClasses(class1);
                student.setBorn(resultSet.getString(8));
                student.setPhone(resultSet.getString(9));
                student.setAddress(resultSet.getString(10));
                student.setIdCard(resultSet.getString(11));
                student.setState(resultSet.getInt(12));
                list.add(student);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        //拼接搜索条件
        String sql = "select * from student where state =1";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStuNo(resultSet.getInt(1));
                student.setStuName(resultSet.getString(2));
                student.setStuImg(resultSet.getString(3));
                student.setPwd(resultSet.getString(4));
                student.setSex(resultSet.getString(5));

                int gId = resultSet.getInt(6);
                Grade grade = new GradeDaoImpl().findByGradeId(gId);
                student.setGrade(grade);

                int cId = resultSet.getInt(7);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                student.setClasses(class1);
                student.setBorn(resultSet.getString(8));
                student.setPhone(resultSet.getString(9));
                student.setAddress(resultSet.getString(10));
                student.setIdCard(resultSet.getString(11));
                student.setState(resultSet.getInt(12));
                list.add(student);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    //通过学生ID找学生信息
    public Student findById(int stuNo) {
        String sql = "select * from student where stuNo=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, stuNo);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStuNo(resultSet.getInt(1));
                student.setStuName(resultSet.getString(2));
                student.setStuImg(resultSet.getString(3));
                System.out.println(student.getStuImg());
                student.setPwd(resultSet.getString(4));
                student.setSex(resultSet.getString(5));

                int gId = resultSet.getInt(6);
                Grade grade = new GradeDaoImpl().findByGradeId(gId);
                student.setGrade(grade);

                int cId = resultSet.getInt(7);
                Classes class1 = new ClassesDaoImpl().findByClassId(cId);
                student.setClasses(class1);
                student.setBorn(resultSet.getString(8));
                student.setPhone(resultSet.getString(9));
                student.setAddress(resultSet.getString(10));
                student.setIdCard(resultSet.getString(11));
                student.setState(resultSet.getInt(12));
                student.setSFtype(resultSet.getInt(13));
                return student;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public int count(int gid, String name, int stuNo, int classId) {
        //拼接搜索条件
        String sql = "select count(1) from student where 1=1 and state=1 ";
        if (gid != 0) {
            sql += " and gId=" + gid;
        }
        if (name != "") {
            sql += " and stuName like '%" + name + "%'";
        }
        if (stuNo != 0) {
            sql += " and stuNo=" + stuNo;
        }
        if (classId != 0) {
            sql += " and classId=" + classId;
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

    //学生登录判断
    @Override
    public Student login(int stuNo, String pwd) {
        String sql = "select * from student where stuNo = ? and pwd = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, stuNo);
            statement.setObject(2, pwd);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = findById(stuNo);  //调用学生ID找学生信息

                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    //增
    @Override
    public int add(Student student) {
        String sql = "insert into student values(null,?,null,?,?,?,?,?,?,?,?,1,1)";
        return BaseDao.executeUpdate(sql, student.getStuName(), student.getPwd(), student.getSex(),
                student.getGrade().getgId(), student.getClasses().getClassId(), student.getBorn(),
                student.getPhone(), student.getAddress(), student.getIdCard());
    }

    //删 更改该字段的值为0，显示时只显示值为 1 的数据
    @Override
    public int del(int stuNo) {
        String sql = "update student set state=0 where stuNo=?";
        return BaseDao.executeUpdate(sql, stuNo);
    }

    //改
    @Override
    public int update(Student student) {
        String sql = "update student set stuName=?,pwd=?,sex=?,gId=?,classId=?,born=?,phone=?,address=?," +
                "idCard=? where stuNo=?";
        return BaseDao.executeUpdate(sql, student.getStuName(), student.getPwd(),
                student.getSex(), student.getGrade().getgId(), student.getClasses().getClassId(), student.getBorn(),
                student.getPhone(), student.getAddress(), student.getIdCard(), student.getStuNo());
    }

    //存图片
    public int saveImg(int no, String saveFile){
        String sql="update student set stuImg=? where stuNo=?";
        return BaseDao.executeUpdate(sql,saveFile,no);
    }
}

package com.xmx.service.impl;

import com.xmx.dao.StudentDao;
import com.xmx.dao.impl.StudentDaoImpl;
import com.xmx.pojo.Student;
import com.xmx.service.StudentService;
import com.xmx.util.PageUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public PageUtil<Student> pages(int index, int size, int gid, String name, int stuNo, int classId) {
        int count = studentDao.count(gid, name, stuNo, classId);
        //取本页的数据
        List<Student> list = studentDao.pages(index, size, gid, name, stuNo, classId);
        //计算总页数
        int totail = (int) Math.ceil((double) count / size);
        //封装成分页工具类
        PageUtil<Student> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public Student login(int stuNo, String pwd) {
        return studentDao.login(stuNo, pwd);
    }

    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public Student findById(int stuNo) {
        return studentDao.findById(stuNo);
    }

    @Override
    public int del(int stuNo) {
        return studentDao.del(stuNo);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }
}

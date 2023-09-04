package com.xmx.service;

import com.xmx.pojo.Student;
import com.xmx.util.PageUtil;

public interface StudentService {
    //分页 index:当前页码 size:每页大小
    public PageUtil<Student> pages(int index, int size, int gid, String name, int stuNo, int classId);

    public Student login(int stuNo, String pwd);

    public int add(Student student);

    public Student findById(int stuNo);

    public int del(int stuNo);

    public int update(Student student);
}

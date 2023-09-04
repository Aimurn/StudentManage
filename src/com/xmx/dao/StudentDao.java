package com.xmx.dao;

import com.xmx.pojo.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> pages(int index, int size, int gid, String name, int stuNo, int classId);

    public List<Student> getStudents();

    Student findById(int stuNo);

    public int count(int gid, String name, int stuNo, int classId);

    public Student login(int stuNo, String pwd);

    public int add(Student student);

    public int del(int stuNo);

    public int update(Student student);

}

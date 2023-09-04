package com.xmx.dao;

import com.xmx.pojo.Course;

import java.util.List;

public interface CourseDao {
    public List<Course> pages(int index, int size, String cName, int gId);

    public List<Course> getCourse();

    public Course findByCourseId(int cId);

    public int count(String cName, int gId);

    public int add(Course course);

    public int update(Course course);

    public int del(int cId);
}

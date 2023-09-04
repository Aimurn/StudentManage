package com.xmx.service;

import com.xmx.pojo.Course;
import com.xmx.util.PageUtil;

import java.util.List;

public interface CourseService {
    public PageUtil<Course> pages(int index, int size, String cName, int gId);

    public List<Course> getCourse();

    public Course findByCourseId(int cId);

    public int add(Course course);

    public int update(Course course);

    public int del(int cId);
}

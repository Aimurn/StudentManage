package com.xmx.service.impl;

import com.xmx.dao.CourseDao;
import com.xmx.dao.impl.CourseDaoImpl;
import com.xmx.pojo.Course;
import com.xmx.service.CourseService;
import com.xmx.util.PageUtil;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> getCourse() {
        return courseDao.getCourse();
    }

    @Override
    public PageUtil<Course> pages(int index, int size, String cName, int gId) {

        int count = courseDao.count(cName, gId);
        //取本页数据
        List<Course> list = courseDao.pages(index, size, cName, gId);

        //计算页数
        int totail = (int) Math.ceil((double) count / size);
        //封装成分页工具类
        PageUtil<Course> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public Course findByCourseId(int cId) {
        return courseDao.findByCourseId(cId);
    }

    @Override
    public int add(Course course) {
        return courseDao.add(course);
    }

    @Override
    public int update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public int del(int cId) {
        return courseDao.del(cId);
    }
}

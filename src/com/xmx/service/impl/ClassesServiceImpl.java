package com.xmx.service.impl;

import com.xmx.dao.ClassesDao;
import com.xmx.dao.impl.ClassesDaoImpl;
import com.xmx.pojo.Classes;
import com.xmx.service.ClassesService;
import com.xmx.util.PageUtil;

import java.util.List;

public class ClassesServiceImpl implements ClassesService {
    ClassesDao classesDao = new ClassesDaoImpl();

    @Override
    public PageUtil<Classes> pages(int index, int size, int gId) {
        int count = classesDao.count(gId);
        //取本页的数据
        List<Classes> list = classesDao.pages(index, size, gId);

        //计算页数
        int totail = (int) Math.ceil((double) count / size);
        //封装成分页工具类
        PageUtil<Classes> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public List<Classes> getClasses() {
        return classesDao.getClasses();
    }

    @Override
    public Classes findByClassId(int classId) {
        return classesDao.findByClassId(classId);
    }

    @Override
    public List<Classes> findClassBygId(int gId) {
        return classesDao.findClassBygId(gId);
    }

    @Override
    public int count(int gId) {
        return classesDao.count(gId);
    }

    @Override
    public int add(Classes classes) {
        return classesDao.add(classes);
    }

    @Override
    public int update(Classes classes) {
        return classesDao.update(classes);
    }

    @Override
    public int del(int classId) {
        return classesDao.del(classId);
    }
}

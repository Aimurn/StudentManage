package com.xmx.service.impl;

import com.xmx.dao.GradeDao;
import com.xmx.dao.impl.GradeDaoImpl;
import com.xmx.pojo.Grade;
import com.xmx.service.GradeService;
import com.xmx.util.PageUtil;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    //调用持久层的方法  父类的引用指向子类的对象
    GradeDao gradeDao = new GradeDaoImpl();

    @Override
    public List<Grade> getGrades() {
        return gradeDao.getGrades();
    }

    @Override
    public Grade findByGradeId(int stuNo) {
        return gradeDao.findByGradeId(stuNo);
    }

    @Override
    public int add(Grade grade) {
        return gradeDao.add(grade);
    }

    @Override
    public int update(Grade grade) {
        return gradeDao.update(grade);
    }

    @Override
    public int del(int gId) {
        return gradeDao.del(gId);
    }
}

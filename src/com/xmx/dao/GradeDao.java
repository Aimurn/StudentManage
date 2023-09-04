package com.xmx.dao;

import com.xmx.pojo.Grade;

import java.util.List;

public interface GradeDao {

    public List<Grade> getGrades();

    Grade findByGradeId(int stuNo);

    public int add(Grade grade);

    public int update(Grade grade);

    public int del(int gId);
}

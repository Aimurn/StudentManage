package com.xmx.service;

import com.xmx.pojo.Grade;
import com.xmx.util.PageUtil;

import java.util.List;

public interface GradeService {

    public List<Grade> getGrades();

    Grade findByGradeId(int stuNo);

    public int add(Grade grade);

    public int update(Grade grade);

    public int del(int gId);
}

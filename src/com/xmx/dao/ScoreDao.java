package com.xmx.dao;

import com.xmx.pojo.Score;

import java.util.List;

public interface ScoreDao {
    public List<Score> pages(int index, int size, int stuNo, int cId );//搜索条件带学生编号跟科目编号

    public List<Score> getScore();

    public int count(int stuNo, int cId);//页数

    public int add(Score score);

    public int update(Score score);

    public int del(int no, int cId);

}

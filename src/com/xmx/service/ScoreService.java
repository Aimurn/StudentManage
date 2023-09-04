package com.xmx.service;

import com.xmx.pojo.Score;
import com.xmx.util.PageUtil;

import java.util.List;

public interface ScoreService {
    public PageUtil<Score> pages(int index, int size, int stuNo, int cId);

    public List<Score> getScore();

    public int add(Score score);

    public int update(Score score);

    public int del(int no, int cId);
}

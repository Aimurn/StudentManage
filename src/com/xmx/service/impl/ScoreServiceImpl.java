package com.xmx.service.impl;

import com.xmx.dao.ScoreDao;
import com.xmx.dao.impl.ScoreDaoImpl;
import com.xmx.pojo.Score;
import com.xmx.service.ScoreService;
import com.xmx.util.PageUtil;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {
    ScoreDao scoreDao = new ScoreDaoImpl();

    @Override
    public PageUtil<Score> pages(int index, int size, int stuNo, int cId) {
        int count = scoreDao.count(stuNo, cId);
        // 取本页的数据
        List<Score> list = scoreDao.pages(index, size, stuNo, cId);

        // 计算页数
        int totail = (int) Math.ceil((double) count / size);
        // 封装成分页工具类
        PageUtil<Score> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public List<Score> getScore() {
        return scoreDao.getScore();
    }

    @Override
    public int add(Score score) {
        return scoreDao.add(score);
    }

    @Override
    public int update(Score score) {
        return scoreDao.update(score);
    }

    @Override
    public int del(int no, int cId) {
        return scoreDao.del(no, cId);
    }
}

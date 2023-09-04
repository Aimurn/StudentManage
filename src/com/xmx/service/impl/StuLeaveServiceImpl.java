package com.xmx.service.impl;

import com.xmx.dao.StuLeaveDao;
import com.xmx.dao.impl.StuLeaveDaoImpl;
import com.xmx.pojo.StuLeave;
import com.xmx.service.StuLeaveService;
import com.xmx.util.PageUtil;

import java.util.List;

public class StuLeaveServiceImpl implements StuLeaveService {
    StuLeaveDao stuLeaveDao = new StuLeaveDaoImpl();

    @Override
    public PageUtil<StuLeave> pages(int index, int size, int approval, int no) {
        int count = stuLeaveDao.count(approval, no);
        //取本页数据
        List<StuLeave> list = stuLeaveDao.pages(index, size, approval, no);
        //计算总页数
        int totail = (int) Math.ceil((double) count / size);
        //封装成分页工具类
        PageUtil<StuLeave> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public PageUtil<StuLeave> adpages(int index, int size, int approval, String name, int classId) {
        int count = stuLeaveDao.adcount(approval, name, classId);
        //取本页数据
        List<StuLeave> list = stuLeaveDao.adpages(index, size, approval, name, classId);
        //计算总页数
        int totail = (int) Math.ceil((double) count / size);
        //封装成分页工具类
        PageUtil<StuLeave> pageUtil = new PageUtil<>();
        pageUtil.setPageIndex(index);
        pageUtil.setPageSize(size);
        pageUtil.setList(list);
        pageUtil.setPageTotial(totail);
        pageUtil.setRowNumber(count);
        return pageUtil;
    }

    @Override
    public List<StuLeave> getStuLeave() {
        return stuLeaveDao.getStuLeave();
    }

    @Override
    public StuLeave findId(int id) {
        return stuLeaveDao.findId(id);
    }

    @Override
    public int add(StuLeave stuLeave) {
        return stuLeaveDao.add(stuLeave);
    }

    @Override
    public int del(int id) {
        return stuLeaveDao.del(id);
    }

    @Override
    public int update(StuLeave stuLeave) {
        return stuLeaveDao.update(stuLeave);
    }
}

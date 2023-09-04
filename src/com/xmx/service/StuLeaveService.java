package com.xmx.service;

import com.xmx.pojo.StuLeave;
import com.xmx.util.PageUtil;

import java.util.List;

public interface StuLeaveService {
    public PageUtil<StuLeave> pages(int index, int size, int approval, int no);

    public PageUtil<StuLeave> adpages(int index, int size, int approval, String name, int classId);

    public List<StuLeave> getStuLeave();

    StuLeave findId(int id);

    public int add(StuLeave stuLeave);

    public int del(int id);

    public int update(StuLeave stuLeave);
}

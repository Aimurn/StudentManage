package com.xmx.dao;

import com.xmx.pojo.StuLeave;

import java.util.List;

public interface StuLeaveDao {
    public List<StuLeave> pages(int index, int size, int approval, int no);

    public List<StuLeave> adpages(int index, int size, int approval, String name, int classId);

    public List<StuLeave> getStuLeave();

    public int count(int approval, int no);

    public int adcount(int approval, String name, int classId);

    StuLeave findId(int id);

    public int add(StuLeave stuLeave);

    public int del(int id);

    public int update(StuLeave stuLeave);
}


package com.xmx.dao;

import com.xmx.pojo.Classes;

import java.util.List;

public interface ClassesDao {
    public List<Classes> pages(int index, int size, int gId);

    public List<Classes> getClasses();

    public Classes findByClassId(int classId);

    public List<Classes> findClassBygId(int gId);

    public int count(int gId);

    public int add(Classes classes);

    public int update(Classes classes);

    public int del(int classId);
}

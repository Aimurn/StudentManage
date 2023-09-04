package com.xmx.util;

import java.util.List;

//T泛型类
public class PageUtil<T> {
    private int pageIndex; //当前页码
    private int pageSize;  //每页大小
    private int pageTotial; //总页数
    private int rowNumber; //行数
    private List<T> list;   //本页数据

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotial() {
        return pageTotial;
    }

    public void setPageTotial(int pageTotial) {
        this.pageTotial = pageTotial;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

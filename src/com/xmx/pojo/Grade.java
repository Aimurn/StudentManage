package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 16:13
 * @Description: 年级实体类
 */
public class Grade {
    private int gId;    //年级编号
    private String gName;   //年级名称
    private int state;  //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

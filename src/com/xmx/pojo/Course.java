package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 16:15
 * @Description: 课程实体类
 */
public class Course {
    private int cId;    //课程编号
    private String cName;   //课程名称
    private String cImage;  //课程图片
    private int hour;   //课时
    private Grade grade;    //年级id -> 对象
    private int state;  //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据

    public Course() {
    }

    public Course(int cId, String cName, int hour, Grade grade) {
        this.cId = cId;
        this.cName = cName;
        this.hour = hour;
        this.grade = grade;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

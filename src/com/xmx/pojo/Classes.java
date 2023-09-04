package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 16:18
 * @Description: 班级实体类
 */
public class Classes {
    private int classId;    //班级编号
    private String className;   //班级名称
    private Grade grade;    //年级id ->对象
    private int state;  //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据

    public Classes() {
    }

    public Classes(String className, Grade grade) {
        this.className = className;
        this.grade = grade;
    }

    public Classes(int classId, String className, Grade grade) {
        this.classId = classId;
        this.className = className;
        this.grade = grade;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

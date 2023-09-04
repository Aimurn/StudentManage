package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/30 19:34
 * @Description: 请假表实体类
 */
public class StuLeave {
    private Student student;    //学号
    private String name;    //姓名
    private Classes classes;    //班级编号
    private String reason;      //请假原因
    private String startTime;   //开始时间
    private String endTime;     //结束时间
    private int approval;   //审批状态：0待审批，1同意请假，2不同意
    private int state;      //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据

    public StuLeave() {
    }

    public StuLeave(Student student, String name, Classes classes, String reason, String startTime, String endTime) {
        this.student = student;
        this.name = name;
        this.classes = classes;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getApproval() {
        return approval;
    }

    public void setApproval(int approval) {
        this.approval = approval;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

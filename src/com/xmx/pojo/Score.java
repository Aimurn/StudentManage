package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 16:10
 * @Description: 成绩实体类
 */
public class Score {
    private Student student; //学生学号 -> 对象
    private Course course;    //课程编号 -> 对象
    private int score;  //分数
    private int state;  //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据

    public Score() {
    }

    public Score(Student student, Course course, int score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

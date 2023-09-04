package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 16:01
 * @Description: 学生实体类
 */
public class Student {
    private int stuNo;  //学生学号
    private String stuName;   //学生姓名
    private String stuImg;    //头像
    private String pwd;     //密码
    private String sex;     //性别
    private Grade grade;    //年级id -> 对象
    private Classes Classes;    //班级id -> 对象
    private String born;    //出生日期
    private String phone;   //电话
    private String address; //地址
    private String idCard;  //身份证
    private int state;      //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据
    private int SFtype;     //学生登录身份，值为1

    //无参构造
    public Student() {

    }

    public Student(int stuNo, String stuName, String pwd, String sex, Grade grade, com.xmx.pojo.Classes classes,
                   String born, String phone, String address, String idCard) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.pwd = pwd;
        this.sex = sex;
        this.grade = grade;
        Classes = classes;
        this.born = born;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
    }

    //管理员添加构造方法
    public Student(String stuName, String pwd, String sex, Grade grade, com.xmx.pojo.Classes classes, String born,
                   String phone, String address, String idCard) {
        this.stuName = stuName;
        this.pwd = pwd;
        this.sex = sex;
        this.grade = grade;
        Classes = classes;
        this.born = born;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
    }

    public Student(String stuName, String stuImg, String pwd, String sex, Grade grade, com.xmx.pojo.Classes classes,
                   String born, String phone, String address, String idCard) {
        this.stuName = stuName;
        this.stuImg = stuImg;
        this.pwd = pwd;
        this.sex = sex;
        this.grade = grade;
        Classes = classes;
        this.born = born;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
    }

    //管理员编辑构造方法
    public Student(int stuNo, String stuName, String stuImg, String pwd, String sex, Grade grade,
                   com.xmx.pojo.Classes classes, String born, String phone, String address, String idCard) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuImg = stuImg;
        this.pwd = pwd;
        this.sex = sex;
        this.grade = grade;
        Classes = classes;
        this.born = born;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
    }

    public int getSFtype() {
        return SFtype;
    }

    public void setSFtype(int SFtype) {
        this.SFtype = SFtype;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuImg() {
        return stuImg;
    }

    public void setStuImg(String stuImg) {
        this.stuImg = stuImg;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Classes getClasses() {
        return Classes;
    }

    public void setClasses(Classes Classes) {
        this.Classes = Classes;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

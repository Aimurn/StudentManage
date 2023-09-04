package com.xmx.pojo;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 15:54
 * @Description: 管理员user表实体类
 */
public class User {
    private int id;    //用户id
    private String username;    //用户名
    private String pwd;     //密码
    private String image;   //头像
    private int state;      //启用状态，删除只做更改该字段的值为0，显示时只显示值为 1 的数据
    private int type;   //管理员类型，1-超级管理员 0-普通管理员
    private int SFtype; //管理员登录身份，值为2

    public User() {
    }

    public User(String username, String pwd, int type) {
        this.username = username;
        this.pwd = pwd;
        this.type = type;
    }

    public int getSFtype() {
        return SFtype;
    }

    public void setSFtype(int SFtype) {
        this.SFtype = SFtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

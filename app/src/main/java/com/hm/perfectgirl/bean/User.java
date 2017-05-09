package com.hm.perfectgirl.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by 清扬 on 2017/5/2.
 */

public class User extends BmobUser{
    private Integer age;
    private Boolean sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    private String nick;

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", sex=" + sex +
                ", nick='" + nick + '\'' +
                '}';
    }
}

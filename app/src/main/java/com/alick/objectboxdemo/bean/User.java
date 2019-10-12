package com.alick.objectboxdemo.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

/**
 * @author 崔兴旺
 * @createTime 2019/10/12 14:23
 * @description
 */
@Entity
public class User {
    @Id//必须设置@Id注解
    private long    _id;        //必须是long类型
    @Index//当频繁根据此字段查询时,推荐加上索引以提高查询效率
    private String  username;
    private boolean sex;
    private int     age;

    public User(String username, boolean sex, int age) {
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

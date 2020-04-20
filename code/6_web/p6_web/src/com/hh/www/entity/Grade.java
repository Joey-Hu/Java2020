package com.hh.www.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * @author: huhao
 * @time: 2020/4/20 17:35
 * @desc:
 */
public class Grade {

    private int id;
//    @JSONField(serialize = false)
    private String name;
    private ArrayList<Student> stus;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stus=" + stus +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStus() {
        return stus;
    }

    public void setStus(ArrayList<Student> stus) {
        this.stus = stus;
    }

    public Grade() {

    }

    public Grade(int id, String name, ArrayList<Student> stus) {

        this.id = id;
        this.name = name;
        this.stus = stus;
    }
}

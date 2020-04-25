package com.hh.www.entity;

/**
 * @author: huhao
 * @time: 2020/4/25 15:24
 * @desc:
 */
public class User {

    private int id;
    private String username;
    private String userpassword;
    private String phone;
    private int access;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", phone='" + phone + '\'' +
                ", access=" + access +
                '}';
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

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public User(int id, String username, String userpassword, String phone, int access) {

        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.phone = phone;
        this.access = access;
    }
}

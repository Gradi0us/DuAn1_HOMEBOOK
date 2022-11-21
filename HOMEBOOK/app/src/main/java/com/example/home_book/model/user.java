package com.example.home_book.model;

import java.util.Date;

public class user {
    int id,role,money,avatar;
    String fullname,email,password,phone,cccd;
    Date birth_day;

    public user() {
    }

    public user(int id, int avatar, String fullname, String email, String password, Date birth_day, String phone, int role, int money) {
        this.id = id;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birth_day = birth_day;
        this.avatar = avatar;
        this.phone = phone;
        this.money = money;
    }

    public user(int avatar, String fullname, String email, String password, Date birth_day, String phone, int role, int money) {
        this.role = role;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birth_day = birth_day;
        this.avatar = avatar;
        this.phone = phone;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
}

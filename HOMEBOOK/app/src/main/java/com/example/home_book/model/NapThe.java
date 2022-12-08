package com.example.home_book.model;

public class NapThe {
    int id,user_id,money,status;

    public NapThe(int id, int user_id, int money, int status) {
        this.id = id;
        this.user_id = user_id;
        this.money = money;
        this.status = status;
    }

    public NapThe(int user_id, int money, int status) {
        this.user_id = user_id;
        this.money = money;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

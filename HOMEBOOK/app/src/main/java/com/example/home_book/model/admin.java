package com.example.home_book.model;

public class admin {
    int id,money_reciever,avatar;

    public admin() {
    }

    public admin(int id, int avatar, int money_reciever) {
        this.id = id;
        this.money_reciever = money_reciever;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney_reciever() {
        return money_reciever;
    }

    public void setMoney_reciever(int money_reciever) {
        this.money_reciever = money_reciever;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}

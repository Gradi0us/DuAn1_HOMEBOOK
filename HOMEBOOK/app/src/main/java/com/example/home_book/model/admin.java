package com.example.home_book.model;

public class admin {
    int id,money_reciever,user_id;

    public admin() {
    }

    public admin(int id, int money_reciever, int user_id) {
        this.id = id;
        this.money_reciever = money_reciever;
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

package com.example.home_book.model;

public class admin {
    int money_reciever;
    String username,password;

    public admin() {
    }

    public admin(String username, String password,int money_reciever) {
        this.money_reciever = money_reciever;
        this.username = username;
        this.password = password;
    }

    public int getMoney_reciever() {
        return money_reciever;
    }

    public void setMoney_reciever(int money_reciever) {
        this.money_reciever = money_reciever;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

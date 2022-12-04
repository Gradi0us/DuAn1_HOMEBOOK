package com.example.home_book.model;

import java.util.Date;

public class DateCurrent {
    int id;
    Date date;
    int check;

    public DateCurrent(Date date, int check) {
        this.date = date;
        this.check = check;
    }

    public DateCurrent(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public DateCurrent(Date date) {
        this.date = date;
    }

    public DateCurrent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

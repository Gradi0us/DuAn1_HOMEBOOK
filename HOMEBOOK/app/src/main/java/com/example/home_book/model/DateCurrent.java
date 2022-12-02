package com.example.home_book.model;

import java.util.Date;

public class DateCurrent {
    int id;
    Date date;

    public DateCurrent(int id, Date date) {
        this.id = id;
        this.date = date;
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

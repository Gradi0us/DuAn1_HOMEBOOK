package com.example.home_book.model;

public class rating {
    int id,user_id,order_id,rating;
    String note;

    public rating(int id, int user_id, int order_id, int rating, String note) {
        this.id = id;
        this.user_id = user_id;
        this.order_id = order_id;
        this.rating = rating;
        this.note = note;
    }

    public rating(int user_id, int order_id, int rating, String note) {
        this.user_id = user_id;
        this.order_id = order_id;
        this.rating = rating;
        this.note = note;
    }

    public rating(int rating, String note) {
        this.rating = rating;
        this.note = note;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

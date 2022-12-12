package com.example.home_book.model;

public class rating {
    int id,user_id,room_id,rating;
    String note;

    public rating(int id, int user_id, int room_id, int rating, String note) {
        this.id = id;
        this.user_id = user_id;


        this.room_id = room_id;

        this.rating = rating;
        this.note = note;
    }

    public rating(int user_id, int room_id, int rating, String note) {
        this.user_id = user_id;


        this.room_id = room_id;

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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
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

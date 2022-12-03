package com.example.home_book.model;

public class Favourite {
    int id,room_id,user_id;

    public Favourite(int id, int room_id, int user_id) {
        this.id = id;
        this.room_id = room_id;
        this.user_id = user_id;
    }

    public Favourite(int room_id, int user_id) {
        this.room_id = room_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

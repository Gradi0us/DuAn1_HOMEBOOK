package com.example.home_book.model;

public class roomImage {
    int id,room_id,image;

    public roomImage() {
    }

    public roomImage(int room_id, int image) {
        this.room_id = room_id;
        this.image = image;
    }

    public roomImage(int id, int room_id, int image) {
        this.id = id;
        this.room_id = room_id;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

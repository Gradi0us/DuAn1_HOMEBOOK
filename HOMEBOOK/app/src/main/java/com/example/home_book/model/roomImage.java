package com.example.home_book.model;

public class roomImage {
    int id,room_id;
    byte[] image_room;

    public roomImage() {
    }

    public roomImage(int room_id, byte[] image) {
        this.room_id = room_id;
        this.image_room = image;
    }

    public roomImage(byte[] image_room) {
        this.image_room = image_room;
    }

    public roomImage(int id, int room_id, byte[] image) {
        this.id = id;
        this.room_id = room_id;
        this.image_room = image;
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

    public byte[] getImage_room() {
        return image_room;
    }

    public void setImage_room(byte[] image_room) {
        this.image_room = image_room;
    }
}

package com.example.home_book.model;

public class oderdetail {
    int id,orders_id,rooms_id;
    String name_orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getRooms_id() {
        return rooms_id;
    }

    public void setRooms_id(int rooms_id) {
        this.rooms_id = rooms_id;
    }

    public String getName_orders() {
        return name_orders;
    }

    public void setName_orders(String name_orders) {
        this.name_orders = name_orders;
    }

    public oderdetail(int id, int orders_id, int rooms_id, String name_orders) {
        this.id = id;
        this.orders_id = orders_id;
        this.rooms_id = rooms_id;
        this.name_orders = name_orders;
    }

    public oderdetail() {
    }
}

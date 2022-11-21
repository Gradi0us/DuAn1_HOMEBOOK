package com.example.home_book.model;

public class rooms {
    int id, rate, max_people, rooms, beds, status, cost;
    String service, note, size, name, brand, category;

    public rooms() {
    }

    public rooms(String name, String brand, String category, int max_people, int beds, int rooms, int rate, String note, String size, String service, int cost, int status) {
        this.rate = rate;
        this.max_people = max_people;
        this.rooms = rooms;
        this.beds = beds;
        this.status = status;
        this.cost = cost;
        this.service = service;
        this.note = note;
        this.size = size;
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    public rooms(int id, String name, String brand, String category, int max_people, int beds, int rooms, int rate, String note, String size, String service, int cost, int status) {
        this.id = id;
        this.rate = rate;
        this.max_people = max_people;
        this.rooms = rooms;
        this.beds = beds;
        this.status = status;
        this.cost = cost;
        this.service = service;
        this.note = note;
        this.size = size;
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
package com.example.home_book.model;

public class rooms {
    int id, rate, beds, status, cost;
    boolean wifi,ac,buffet,parking,pool,minibar;
    String note;
    String name;
    String category;
    String location;

    public byte[] getIMG() {
        return IMG;
    }

    public void setIMG(byte[] IMG) {
        this.IMG = IMG;
    }

    byte[] IMG;
    public rooms(int rate, int beds, int status, int cost, boolean wifi, boolean ac, boolean buffet, boolean parking, boolean pool, boolean minibar, String note, String name, String category, String location,byte[] IMG) {
        this.rate = rate;
        this.beds = beds;
        this.status = status;
        this.cost = cost;
        this.wifi = wifi;
        this.ac = ac;
        this.buffet = buffet;
        this.parking = parking;
        this.pool = pool;
        this.minibar = minibar;
        this.note = note;
        this.name = name;
        this.category = category;
        this.location = location;
        this.IMG = IMG;
    }

    public rooms(int id, int rate, int beds, int status, int cost, boolean wifi, boolean ac, boolean buffet, boolean parking, boolean pool, boolean minibar, String note, String name, String category, String location,byte[] IMG) {

        this.id = id;
        this.rate = rate;
        this.beds = beds;
        this.status = status;
        this.cost = cost;
        this.wifi = wifi;
        this.ac = ac;
        this.buffet = buffet;
        this.parking = parking;
        this.pool = pool;
        this.minibar = minibar;
        this.note = note;
        this.name = name;
        this.category = category;
        this.location = location;
        this.IMG = IMG;
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

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isBuffet() {
        return buffet;
    }

    public void setBuffet(boolean buffet) {
        this.buffet = buffet;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
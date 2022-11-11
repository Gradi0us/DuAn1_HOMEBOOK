package com.example.home_book.model;

public class rooms {
    int id,brands_id,category_id,rate_id;
            String status,service,cost,note,size;

    public rooms() {
    }

    public rooms(int brands_id, int category_id, int rate_id, String status, String service, String cost, String note, String size) {
        this.brands_id = brands_id;
        this.category_id = category_id;
        this.rate_id = rate_id;
        this.status = status;
        this.service = service;
        this.cost = cost;
        this.note = note;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrands_id() {
        return brands_id;
    }

    public void setBrands_id(int brands_id) {
        this.brands_id = brands_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getRate_id() {
        return rate_id;
    }

    public void setRate_id(int rate_id) {
        this.rate_id = rate_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
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
}
package com.example.home_book.model;

public class categories {
    int id,max_people,amount_bed,amount_bedroom,service_fee;
    String name_categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public int getAmount_bed() {
        return amount_bed;
    }

    public void setAmount_bed(int amount_bed) {
        this.amount_bed = amount_bed;
    }

    public int getAmount_bedroom() {
        return amount_bedroom;
    }

    public void setAmount_bedroom(int amount_bedroom) {
        this.amount_bedroom = amount_bedroom;
    }

    public int getService_fee() {
        return service_fee;
    }

    public void setService_fee(int service_fee) {
        this.service_fee = service_fee;
    }

    public String getName_categories() {
        return name_categories;
    }

    public void setName_categories(String name_categories) {
        this.name_categories = name_categories;
    }

    public categories(int id, int max_people, int amount_bed, int amount_bedroom, int service_fee, String name_categories) {
        this.id = id;
        this.max_people = max_people;
        this.amount_bed = amount_bed;
        this.amount_bedroom = amount_bedroom;
        this.service_fee = service_fee;
        this.name_categories = name_categories;
    }

    public categories() {
    }
}

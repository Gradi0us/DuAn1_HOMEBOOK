package com.example.home_book.model;

public class brands {
    int id;
    String Name_Brand,Vote,location;

    public brands() {
    }

    public brands(int id, String name_Brand, String vote, String location) {
        this.id = id;
        Name_Brand = name_Brand;
        Vote = vote;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_Brand() {
        return Name_Brand;
    }

    public void setName_Brand(String name_Brand) {
        Name_Brand = name_Brand;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String vote) {
        Vote = vote;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

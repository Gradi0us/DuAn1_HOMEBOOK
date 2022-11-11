package com.example.home_book.model;

public class order {
    int id,user_id,category_id,number_people,brand_id;
    String booking_date,return_date,time_checkin,time_checkout;

    public order() {
    }

    public order(int user_id, int category_id, int number_people, int brand_id, String booking_date, String return_date, String time_checkin, String time_checkout) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.number_people = number_people;
        this.brand_id = brand_id;
        this.booking_date = booking_date;
        this.return_date = return_date;
        this.time_checkin = time_checkin;
        this.time_checkout = time_checkout;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getNumber_people() {
        return number_people;
    }

    public void setNumber_people(int number_people) {
        this.number_people = number_people;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getTime_checkin() {
        return time_checkin;
    }

    public void setTime_checkin(String time_checkin) {
        this.time_checkin = time_checkin;
    }

    public String getTime_checkout() {
        return time_checkout;
    }

    public void setTime_checkout(String time_checkout) {
        this.time_checkout = time_checkout;
    }
}

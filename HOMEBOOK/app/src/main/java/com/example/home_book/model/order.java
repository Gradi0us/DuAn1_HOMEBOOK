package com.example.home_book.model;

import java.sql.Time;
import java.util.Date;

public class order {
    int id,user_id,number_people,room_id;
    Date booking_date,return_date;
    String time_checkin,time_checkout,note;
    int status;

    public order() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public order(int user_id, int number_people, Date booking_date, Date return_date, String time_checkin, String time_checkout, int room_id, String note, int status) {
        this.user_id = user_id;
        this.number_people = number_people;
        this.room_id = room_id;
        this.time_checkin = time_checkin;
        this.time_checkout = time_checkout;
        this.note = note;
        this.booking_date = booking_date;
        this.return_date = return_date;
        this.status = status;
    }
    public order(int id, int user_id, int number_people, Date booking_date, Date return_date, String time_checkin, String time_checkout, int room_id, String note,int status) {
        this.id = id;
        this.user_id = user_id;
        this.number_people = number_people;
        this.room_id = room_id;
        this.time_checkin = time_checkin;
        this.time_checkout = time_checkout;
        this.note = note;
        this.booking_date = booking_date;
        this.return_date = return_date;
        this.status = status;
    }
    public order(int id, int user_id, int number_people, Date booking_date, Date return_date, String time_checkin, String time_checkout, int room_id, String note) {
        this.id = id;
        this.user_id = user_id;
        this.number_people = number_people;
        this.room_id = room_id;
        this.time_checkin = time_checkin;
        this.time_checkout = time_checkout;
        this.note = note;
        this.booking_date = booking_date;
        this.return_date = return_date;
    }

    public order( int user_id, int number_people, Date booking_date, Date return_date, String time_checkin, String time_checkout, int room_id, String note) {
        this.user_id = user_id;
        this.number_people = number_people;
        this.room_id = room_id;
        this.time_checkin = time_checkin;
        this.time_checkout = time_checkout;
        this.note = note;
        this.booking_date = booking_date;
        this.return_date = return_date;
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

    public int getNumber_people() {
        return number_people;
    }

    public void setNumber_people(int number_people) {
        this.number_people = number_people;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

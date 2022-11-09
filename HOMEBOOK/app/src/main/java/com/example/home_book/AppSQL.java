package com.example.home_book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppSQL extends SQLiteOpenHelper {
    final String AdminstratorTable = "CREATE TABLE adminstrator_tb(id int primary key autoincrement, money_reciever money NOT NULL, user_id int references user_tb(id) NOT NULL)";
    final String UserTable = "CREATE TABLE user_tb(id int primary key autoincrement,fullname text NOT NULL, email text NOT NULL,password text NOT NULL, role int NOT NULL, birthday date NOT NULL, money money NOT NULL)";
    final String BrandTable = "create table brand_tb(id int primary key autoincrement, fullname text NOT NULL, vote int NOT NULL, location text NOT NULL)";
    final String OrderTable = "create table order_tb(id int primary key autoincrement, user_id int references user_tb(id) NOT NULL, number_people int NOT NULL, booking_date date NOT NULL, return_date date, time_checkin time NOT NULL, time_checkout time NOT NULL, category_id int references category_tb(id) NOT NULL, note text, brand_id references brand_tb(id) NOT NULL)";
    final String OrderDetailTable = "create table detail_tb(id int primary key autoincrement, fullname text NOT NULL, order_id int references order_tb(id) NOT NULL, room_id int references room_tb(id) NOT NULL)";
    final String CategoryTable = "create table category_tb(id int primary key autoincrement, fullname text NOT NULL, max_people int NOT NULL, beds int NOT NULL, bedrooms int NOT NULL, service_fee money NOT NULL)";
    final String RoomTable = "create table room_tb(id int primary key autoincrement, brand_id references brand_tb(id) NOT NULL, status int NOT NULL, cost money NOT NULL, note text, size text NOT NULL, category_id int references category_tb(id) NOT NULL)";

    // cái category tôi chưa hiểu gồm những gì đâu, có gì check nhé
    // thiếu gì thêm sao
        
    Context context;
    SQLiteDatabase database;

    public AppSQL(@Nullable Context context) {
        super(context, "db_name", null, 1);
        this.context = context;
        database = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AdminstratorTable);
        db.execSQL(UserTable);
        db.execSQL(BrandTable);
        db.execSQL(OrderTable);
        db.execSQL(OrderDetailTable);
        db.execSQL(CategoryTable);
        db.execSQL(RoomTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists adminstrator_tb");
            db.execSQL("drop table if exists user_tb");
            db.execSQL("drop table if exists brand_tb");
            db.execSQL("drop table if exists order_tb");
            db.execSQL("drop table if exists detail_tb");
            db.execSQL("drop table if exists category_tb");
            db.execSQL("drop table if exists room_tb");

            onCreate(db);
        }
    }
}

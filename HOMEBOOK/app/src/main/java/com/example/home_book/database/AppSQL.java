package com.example.home_book.database;

import static com.example.home_book.database.SQLInsert.adminstrator_Values;
import static com.example.home_book.database.SQLInsert.room_Values;
import static com.example.home_book.database.SQLInsert.user_Values;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppSQL extends SQLiteOpenHelper {
    final String AdminstratorTable = "CREATE TABLE adminstrator_tb(id integer primary key autoincrement, avatar integer NOT NULL, money_reciever integer NOT NULL)";
    final String UserTable = "CREATE TABLE user_tb(id integer primary key autoincrement, avatar integer NOT NULL, fullname text NOT NULL, email text NOT NULL,password text NOT NULL, role integer NOT NULL, birthday date NOT NULL, phonenumber nvarchar(11) NOT NULL, money integer NOT NULL)";
    final String OrderTable = "create table order_tb(id integer primary key autoincrement, user_id integer references user_tb(id) NOT NULL, number_people integer NOT NULL, booking_date date NOT NULL, return_date date, time_checkin text NOT NULL, time_checkout text NOT NULL, room_id integer references room_tb(id) NOT NULL, note text)";
    final String RoomTable = "create table room_tb(id integer primary key autoincrement,fullname text NOT NULL, brand_name text NOT NULL, category_name text NOT NULL, location text NOT NULL, collaborate_id integer references user_tb(id) NOT NULL, rate integer NOT NULL, max_people integer NOT NULL, beds integer NOT NULL, rooms integer NOT NULL, note text, size text NOT NULL, service text NOT NULL, cost integer NOT NULL, status integer NOT NULL)";
    final String RoomImageTable = "create table roomImage_tb(id integer primary key autoincrement,room_id integer references room_tb(id) NOT NULL,image integer NOT NULL)";

    // thằng adminsql vô dụng thế

    //Update 00:25 20/11/2022 - Lai : Update SQL nhé

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
        db.execSQL(OrderTable);
        db.execSQL(RoomTable);
        db.execSQL(RoomImageTable);

        db.execSQL(adminstrator_Values);
        db.execSQL(user_Values);
        db.execSQL(room_Values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists adminstrator_tb");
            db.execSQL("drop table if exists user_tb");
            db.execSQL("drop table if exists order_tb");
            db.execSQL("drop table if exists room_tb");
            db.execSQL("drop table if exists roomImage_tb");

            onCreate(db);
        }
    }
}

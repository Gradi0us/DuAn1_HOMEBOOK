package com.example.home_book.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home_book.database.AppSQL;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;

import java.util.ArrayList;

import com.example.home_book.model.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DAO {
    private SQLiteDatabase db;
    AppSQL appSQL;
    private Context context;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public DAO(Context context) {
        this.context = context;
        appSQL = new AppSQL(context);
        db = appSQL.getWritableDatabase();
    }
    /////////////////////////////////////////////////////////////////////

    public boolean checkLogin(String email, String pass) {
        String sql = "SELECT * FROM user_tb WHERE email=? and password=?";
        db = appSQL.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{email, pass});
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    } public List<user> getUser_name (String email, String pass) {
        List<user> list = new ArrayList<>();
        String sql = "SELECT * FROM user_tb WHERE email=? and password=?";
        db = appSQL.getReadableDatabase();
        Cursor c = db.rawQuery(sql, new String[]{email, pass});
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            int ava = c.getInt(1);
            String name = c.getString(2);
            String Email = c.getString(3);
            String Pass = c.getString(4);
            int role = c.getInt(5);
            Date ngay = null;
            try {
                ngay = format.parse(c.getString(6));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String phone = c.getString(7);
            int money = c.getInt(8);
            user x = new user(id, ava, name, Email, Pass, ngay, phone, role, money);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
//        if (cursor.getCount() != 0) {
//            return true;
//        }
//        return false;
    }

    public List<user> getUser(String sql, String... args) {
        List<user> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, args);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            int ava = c.getInt(1);
            String name = c.getString(2);
            String email = c.getString(3);
            String pass = c.getString(4);
            int role = c.getInt(5);
            Date ngay = null;
            try {
                ngay = format.parse(c.getString(6));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String phone = c.getString(7);
            int money = c.getInt(8);
            user x = new user(id, ava, name, email, pass, ngay, phone, role, money);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public user getUserId(String id) {
        String sql = "select * from user_tb where id=?";
        List<user> list = getUser(sql, id);
        return list.get(0);
    }


    public long AddUser(user x) {
        ContentValues value = new ContentValues();
        value.put("avatar", x.getAvatar());
        value.put("fullname", x.getFullname());
        value.put("email", x.getEmail());
        value.put("password", x.getPassword());
        value.put("role", x.getRole());
        value.put("birthday", format.format(x.getBirth_day()));
        value.put("phonenumber", x.getPhone());
        value.put("money", x.getMoney());
        return db.insert("user_tb", null, value);
    }

    public long UpdateUser(user x) {
        ContentValues value = new ContentValues();
        value.put("avatar", x.getAvatar());
        value.put("fullname", x.getFullname());
        value.put("email", x.getEmail());
        value.put("password", x.getPassword());
        value.put("role", x.getRole());
        value.put("birthday", format.format(x.getBirth_day()));
        value.put("phonenumber", x.getPhone());
        value.put("money", x.getMoney());
        return db.update("user_tb", value, "id=?", new String[]{String.valueOf(x.getId())});
    }

//    public ArrayList<Room> Search(String location1) {
//        String sql = " SELECT * FROM room_tb where location like '%" + location1 + "%'";
//        getRoom(sql);
//        return null;
//    }

    public List<Room> getRoom(String sql) {
        List<Room> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String category = c.getString(2);
            String location = c.getString(3);
            int rate = c.getInt(4);
            int beds = c.getInt(5);
            String note = c.getString(15);
            int cost = c.getInt(12);
            int status = c.getInt(13);
            int wf = c.getInt(6);
            int aC = c.getInt(7);
            int parKing = c.getInt(8);
            int miniBar = c.getInt(9);
            int Pool = c.getInt(10);
            int Buffet = c.getInt(11);
            boolean wifi, ac, buffet, pool, minibar, parking;
            if (wf == 0) {
                wifi = false;
            } else {
                wifi = true;
            }
            if (aC == 0) {
                ac = false;
            } else {
                ac = true;
            }
            if (Buffet == 0) {
                buffet = false;
            } else {
                buffet = true;
            }
            if (Pool == 0) {
                pool = false;
            } else {
                pool = true;
            }
            if (miniBar == 0) {
                minibar = false;
            } else {
                minibar = true;
            }
            if (parKing == 0) {
                parking = false;
            } else {
                parking = true;
            }
            byte[] IMG = c.getBlob(14);
            Room x = new Room(id, rate, beds, status, cost, wifi, ac, buffet, parking, pool, minibar, note, name, category, location, IMG);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public Room getRoom2(String sql, String... args) {
        List<Room> list = new ArrayList<>();
        Room x = null;
        SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql, args);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("fullname"));
            String category = c.getString(c.getColumnIndex("category_name"));
            String location = c.getString(c.getColumnIndex("location"));
            int rate = c.getInt(c.getColumnIndex("rate"));
            int beds = c.getInt(c.getColumnIndex("beds"));
            String note = c.getString(c.getColumnIndex("note"));
            int cost = c.getInt(c.getColumnIndex("cost"));
            int status = c.getInt(c.getColumnIndex("status"));
            int wf = c.getInt(c.getColumnIndex("wifi"));
            int aC = c.getInt(c.getColumnIndex("ac"));
            int parKing = c.getInt(c.getColumnIndex("parking"));
            int miniBar = c.getInt(c.getColumnIndex("minibar"));
            int Pool = c.getInt(c.getColumnIndex("pool"));
            int Buffet = c.getInt(c.getColumnIndex("buffet"));
            boolean wifi, ac, buffet, pool, minibar, parking;
            if (wf == 0) {
                wifi = false;
            } else {
                wifi = true;
            }
            if (aC == 0) {
                ac = false;
            } else {
                ac = true;
            }
            if (Buffet == 0) {
                buffet = false;
            } else {
                buffet = true;
            }
            if (Pool == 0) {
                pool = false;
            } else {
                pool = true;
            }
            if (miniBar == 0) {
                minibar = false;
            } else {
                minibar = true;
            }
            if (parKing == 0) {
                parking = false;
            } else {
                parking = true;
            }
            byte[] IMG = c.getBlob(c.getColumnIndex("image"));
             x = new Room(id, rate, beds, status, cost, wifi, ac, buffet, parking, pool, minibar, note, name, category, location, IMG);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return x;
    }

    public long AddRoom(Room x) {
        ContentValues value = new ContentValues();
        value.put("fullname", x.getName());
        value.put("category_name", x.getCategory());
        value.put("location", x.getLocation());
        value.put("rate", x.getRate());
        value.put("beds", x.getBeds());
        value.put("note", x.getNote());
        value.put("cost", x.getCost());
        value.put("status", x.getStatus());
        value.put("image", x.getIMG());
        if (x.isWifi() == false) {
            value.put("wifi", 0);
        } else {
            value.put("wifi", 1);
        }
        if (x.isAc() == false) {
            value.put("ac", 0);
        } else {
            value.put("ac", 1);
        }
        if (x.isBuffet() == false) {
            value.put("buffet", 0);
        } else {
            value.put("buffet", 1);
        }
        if (x.isMinibar() == false) {
            value.put("minibar", 0);
        } else {
            value.put("minibar", 1);
        }
        if (x.isPool() == false) {
            value.put("pool", 0);
        } else {
            value.put("pool", 1);
        }
        if (x.isParking() == false) {
            value.put("parking", 0);
        } else {
            value.put("parking", 1);
        }
        long a = db.insert("room_tb", null, value);
        return a;
    }

    public long UpdateRoom(Room x) {
        ContentValues value = new ContentValues();
        // update sau
        return db.update("room_tb", value, "id=?", new String[]{String.valueOf(x.getId())});
    }

    public void DeleteRoom(int ID) {
        db.delete("room_tb", "id=?", new String[]{String.valueOf(ID)});
    }

    public List<order> getOrder(String sql, String... args) {
        List<order> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, args);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Date ngayNhan = null , ngayTra = null;
            int id = c.getInt(0);
            int user_id = c.getInt(1);
            int number = c.getInt(2);
            try {
                ngayNhan = format.parse(c.getString(3));
                ngayTra = format.parse(c.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String gioNhan = c.getString(5);
            String gioTra = c.getString(6);
            int room_id = c.getInt(7);
            String note = c.getString(8);
            order x = new order(id, user_id, number, ngayNhan, ngayTra, gioNhan, gioTra, room_id, note);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public long AddOrder(order x) {
        ContentValues value = new ContentValues();
        value.put("user_id", x.getUser_id());
        value.put("number_people", x.getNumber_people());
        value.put("booking_date", format.format(x.getBooking_date()));
        value.put("return_date", format.format(x.getReturn_date()));
        value.put("time_checkin", x.getTime_checkin());
        value.put("time_checkout", x.getTime_checkout());
        value.put("room_id", x.getRoom_id());
        value.put("note", x.getNote());
         long a= db.insert("order_tb", null, value);
        return a;
    }

    public long UpdateOrder(order x) {
        ContentValues value = new ContentValues();
        value.put("user_id", x.getUser_id());
        value.put("number_people", x.getNumber_people());
        value.put("booking_date", format.format(x.getBooking_date()));
        value.put("return_date", format.format(x.getReturn_date()));
        value.put("time_checkin", x.getTime_checkin());
        value.put("time_checkout", x.getTime_checkout());
        value.put("room_id", x.getRoom_id());
        value.put("note", x.getNote());
        return db.update("order_tb", value, "id=?", new String[]{String.valueOf(x.getId())});
    }

    public void DeleteOrder(int ID) {
        db.delete("order_tb", "id=?", new String[]{String.valueOf(ID)});
    }
    
}

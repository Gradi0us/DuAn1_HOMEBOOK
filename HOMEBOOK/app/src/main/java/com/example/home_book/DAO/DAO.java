package com.example.home_book.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home_book.database.AppSQL;
import com.example.home_book.model.Room;
import com.example.home_book.model.rooms;
import com.example.home_book.model.roomImage;

import java.util.ArrayList;

import com.example.home_book.model.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//        db = appSQL.getWritableDatabase();
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

    public void DeletePhieuMuon(int ID) {
        db.delete("phieuMuon_tb", "idphieu=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<Room> Search(String location1) {
        ArrayList<Room> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(" SELECT * FROM room_tb where location like '%" + location1 + "%'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("fullname"));
            String category = c.getString(c.getColumnIndex("category_name"));
            String location = c.getString(c.getColumnIndex("location"));
            int rate = c.getInt(4);
            int beds = c.getInt(6);
            String note = c.getString(8);
            int cost = c.getInt(11);
            int status = c.getInt(12);
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
            Room x = new Room(id, rate, beds, status, cost, wifi, ac, buffet, parking, pool, minibar, note, name, category, location, IMG);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    //    public List<rooms> getRoom(String sql, String... args) {
//        List<rooms> list = new ArrayList<>();
//        Cursor c = db.rawQuery(sql, args);
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            int id = c.getInt(0);
//            String name = c.getString(1);
//            String brand = c.getString(2);
//            String category = c.getString(3);
//            int rate = c.getInt(4);
//            int max = c.getInt(5);
//            int beds = c.getInt(6);
//            int room = c.getInt(7);
//            String note = c.getString(8);
//            String size = c.getString(9);
//            String service = c.getString(10);
//            int cost = c.getInt(11);
//            int status = c.getInt(12);
//            rooms x = new rooms(id,name,brand,category,max,beds,room,rate,note,size,service,cost,status);
//            list.add(x);
//            c.moveToNext();
//        }
//        c.close();
//        return list;
//    }
    public List<rooms> getRoom1() {
        List<rooms> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
        String SELECT = "SELECT * FROM room_tb ";
        Cursor c = sqLiteDatabase.rawQuery(SELECT, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String brand = c.getString(2);
            String category = c.getString(3);
            String location = c.getString(c.getColumnIndex("location"));
            int rate = c.getInt(4);
            int max = c.getInt(5);
            int beds = c.getInt(6);
            int room = c.getInt(7);
            String note = c.getString(8);
            String size = c.getString(9);
            String service = c.getString(10);
            int cost = c.getInt(11);
            int status = c.getInt(12);
            rooms x = new rooms(id, name, brand, category, location, max, beds, room, rate, note, size, service, cost, status);
//            rooms x = new rooms(id,)
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public List<Room> getRoom2() {
        List<Room> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
        String SELECT = "SELECT * FROM room_tb ";
        Cursor c = sqLiteDatabase.rawQuery(SELECT, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("fullname"));
            String category = c.getString(c.getColumnIndex("category_name"));
            String location = c.getString(c.getColumnIndex("location"));
            int rate = c.getInt(4);
            int beds = c.getInt(6);
            String note = c.getString(8);
            int cost = c.getInt(11);
            int status = c.getInt(12);
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
            Room x = new Room(id, rate, beds, status, cost, wifi, ac, buffet, parking, pool, minibar, note, name, category, location, IMG);
            list.add(x);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public long AddRoom(rooms x) {
        ContentValues value = new ContentValues();
        SQLiteDatabase sqLiteDatabase = appSQL.getWritableDatabase();
        value.put("fullname", x.getName());
        value.put("brand_name", x.getBrand());
        value.put("category_name", x.getCategory());
        value.put("location", x.getLocation());
        value.put("rate", x.getRate());
        value.put("max_people", x.getMax_people());
        value.put("beds", x.getBeds());
        value.put("rooms", x.getRooms());
        value.put("note", x.getNote());
        value.put("size", x.getSize());
        value.put("service", x.getService());
        value.put("cost", x.getCost());
        value.put("status", x.getStatus());
        long a = sqLiteDatabase.insert("room_tb", null, value);
        return a;
    }

    public long AddRoom1(Room x) {
        ContentValues value = new ContentValues();
        SQLiteDatabase sqLiteDatabase = appSQL.getWritableDatabase();
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
        long a = sqLiteDatabase.insert("room_tb", null, value);
        return a;
    }

    public long UpdateRoom(rooms x) {
        ContentValues value = new ContentValues();
        value.put("fullname", x.getName());
        value.put("brand_name", x.getBrand());
        value.put("category_name", x.getCategory());
        value.put("location", x.getLocation());
        value.put("rate", x.getRate());
        value.put("max_people", x.getMax_people());
        value.put("beds", x.getBeds());
        value.put("rooms", x.getRooms());
        value.put("note", x.getNote());
        value.put("size", x.getSize());
        value.put("service", x.getService());
        value.put("cost", x.getCost());
        value.put("status", x.getStatus());
        return db.update("room_tb", value, "id=?", new String[]{String.valueOf(x.getId())});
    }

    public void DeleteRoom(int ID) {
        db.delete("room_tb", "id=?", new String[]{String.valueOf(ID)});
    }

    public long InsertHinhAnh(roomImage hinhAnh) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = appSQL.getWritableDatabase();
        values.put("image", hinhAnh.getImage_room());
        values.put("room_id", hinhAnh.getRoom_id());
        long a = sqLiteDatabase.insert("roomImage_tb", null, values);
        return a;
    }

    public List<roomImage> getAllHinhAnh() {
        List<roomImage> list1 = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
        String SELECT = "SELECT * FROM roomImage_tb";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                //Lấy ra dữ liệu ở các cột
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int room_id = cursor.getInt(cursor.getColumnIndex("room_id"));
                byte[] img = cursor.getBlob(cursor.getColumnIndex("image"));
                //Thêm dữ liệu vừa lấy ra từ cột vào đối tượng Laptop
                roomImage hinhAnh = new roomImage();
                hinhAnh.setImage_room(img);
                hinhAnh.setId(id);
                hinhAnh.setRoom_id(room_id);
                //Thêm đối tượng vào danh sách
                list1.add(hinhAnh);
                //
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list1;
    }
}

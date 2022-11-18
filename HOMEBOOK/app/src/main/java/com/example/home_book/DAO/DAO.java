package com.example.home_book.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home_book.database.AppSQL;
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
        db = appSQL.getWritableDatabase();
    }
    /////////////////////////////////////////////////////////////////////

    public boolean checkLogin(String email,String pass) {
        String sql = "SELECT * FROM user_tb WHERE email=? and password=?";
        db = appSQL.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{email,pass});
        if(cursor.getCount() != 0){
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

    public user getUserId(String id){
        String sql = "select * from user_tb where id=?";
        List<user> list = getUser(sql,id);
        return list.get(0);
    }


    public long AddUser(user x){
        ContentValues value = new ContentValues();
        value.put("avatar",x.getAvatar());
        value.put("fullname",x.getFullname());
        value.put("email",x.getEmail());
        value.put("password",x.getPassword());
        value.put("role",x.getRole());
        value.put("birthday",format.format(x.getBirth_day()));
        value.put("phonenumber","0");
        value.put("money",x.getMoney());
        return db.insert("user_tb",null,value);
    }
//
    public long UpdateUser(user x){
        ContentValues value = new ContentValues();
        value.put("avatar",x.getAvatar());
        value.put("fullname",x.getFullname());
        value.put("email",x.getEmail());
        value.put("password",x.getPassword());
        value.put("role",x.getRole());
        value.put("birthday",format.format(x.getBirth_day()));
        value.put("phonenumber","0");
        value.put("money",x.getMoney());
        return db.update("user_tb",value,"id=?",new String[]{String.valueOf(x.getId())});
    }
//
//    public void DeletePhieuMuon(int ID){
//        db.delete("phieuMuon_tb","idphieu=?",new String[]{String.valueOf(ID)});
//    }

}

package com.example.home_book.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home_book.database.AppSQL;
import com.example.home_book.model.user;

import java.text.SimpleDateFormat;

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

//    public List<PhieuMuon> getPhieuMuon(String sql, String... args) {
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        List<PhieuMuon> list = new ArrayList<>();
//        Cursor c = db.rawQuery(sql, args);
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            int id = c.getInt(0);
//            String usertt = c.getString(1);
//            int idtv = c.getInt(2);
//            int idsach = c.getInt(3);
//            String tien = c.getString(4);
//            Date ngay = null;
//            try {
//                ngay = format.parse(c.getString(5));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            int tra = c.getInt(6);
//            PhieuMuon x = new PhieuMuon(id, usertt, idtv, idsach, tien, ngay, tra);
//            list.add(x);
//            c.moveToNext();
//        }
//        c.close();
//        return list;
//    }
//
//    public PhieuMuon getPhieuMuonId(String id){
//        String sql = "select * from phieuMuon_tb where idmuon=?";
//        List<PhieuMuon> list = getPhieuMuon(sql,id);
//        return list.get(0);
//    }
//
//    public void AddPhieuMuon(PhieuMuon x){
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        ContentValues values = new ContentValues();
//        values.put("usertt",x.getUsertt());
//        values.put("idtv",x.getIdtv());
//        values.put("idsach",x.getIdsach());
//        values.put("tien",x.getPrice());
//        values.put("date",format.format(x.getDate()));
//        values.put("trasach",0);
//        db.insert("phieuMuon_tb",null,values);
//    }

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

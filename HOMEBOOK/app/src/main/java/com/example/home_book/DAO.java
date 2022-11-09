package com.example.home_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO {
    private SQLiteDatabase db;
    AppSQL appSQL;
    private Context context;

    public DAO(Context context) {
        this.context = context;
        appSQL = new AppSQL(context);
        db = appSQL.getWritableDatabase();
    }
    /////////////////////////////////////////////////////////////////////

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
//
//    public long UpdatePhieuMuon(PhieuMuon x){
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        ContentValues values = new ContentValues();
//        values.put("usertt",x.getUsertt());
//        values.put("idtv",x.getIdtv());
//        values.put("idsach",x.getIdsach());
//        values.put("tien",x.getPrice());
//        values.put("date",format.format(x.getDate()));
//        values.put("trasach",x.getTra());
//        return db.update("phieuMuon_tb",values,"idphieu=?",new String[]{String.valueOf(x.getId())});
//    }
//
//    public void DeletePhieuMuon(int ID){
//        db.delete("phieuMuon_tb","idphieu=?",new String[]{String.valueOf(ID)});
//    }

}

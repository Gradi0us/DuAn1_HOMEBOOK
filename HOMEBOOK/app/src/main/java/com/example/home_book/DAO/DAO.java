package com.example.home_book.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home_book.database.AppSQL;
import com.example.home_book.model.rooms;

import java.util.ArrayList;

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
public ArrayList<rooms> Search(String name, String trangthai) {
    ArrayList<rooms> list = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = appSQL.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM UnLock where name like '%" + name + "%' AND trangthai ='" + trangthai + "' ORDER BY name ASC", null);
    if (cursor.getCount() != 0) {
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String link = cursor.getString(cursor.getColumnIndex("package"));
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
            String trangthai1 = cursor.getString(cursor.getColumnIndex("trangthai"));
            String tinhtrang = cursor.getString(cursor.getColumnIndex("tinhtrang"));
            //Thêm dữ liệu vừa lấy ra từ cột vào đối tượng Laptop
            App app = new App();
            app.setId(id);
            app.setLinkpackage(link);
            app.setLabel(name1);
            app.setImg(img);
//                app.setImg();
            app.setTrangthai(trangthai1);
            app.setTinhtrang(tinhtrang);
            //Thêm đối tượng vào danh sách
            list.add(app);
        } while (cursor.moveToNext());
    }
    return list;
}
}

package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.Room;
import com.example.home_book.model.roomImage;
import com.example.home_book.model.rooms;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new DAO(this);
        findViewById(R.id.tomainsrc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BottomNavActivity.class);
                startActivity(intent);
            }
        });
//        rooms x = new rooms();
//        x.setName("Home1");
//        x.setBrand("A1");
//        x.setCategory("B");
//        x.setLocation("Hà Nội");
//        x.setMax_people(6);
//        x.setBeds(3);
//        x.setCost(2);
//        x.setNote("Note");
//        x.setRate(4);
//        x.setSize("To khổng lồ");
//        x.setService("Wifi");
//        x.setStatus(1);
//        x.setRooms(1);
//        dao.AddRoom(x);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.khachsan1);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] IMG = stream.toByteArray();
        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) getDrawable(R.drawable.khachsan2);
        Bitmap bitmap1 = bitmapDrawable1.getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] IMG1 = stream1.toByteArray();
        Room room = new Room();
        room.setRate(4);
        room.setBeds(2);
        room.setStatus(3);
        room.setCost(5000000);
//        dao.AddRoom(new Room(5,2,Integer.parseInt("1"),Integer.parseInt("500000"),false,true,false,true,false,true,"tung","true","Hotel","location",IMG));
//
//        dao.AddRoom(new Room(3,4,5,700000,false,true,true,true,true,true,"tung1","HIHI1","hoho1","HaNoi1",IMG1));
    }
}
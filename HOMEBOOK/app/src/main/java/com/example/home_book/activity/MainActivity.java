package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
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
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.homebook);
//        Bitmap bitmap1 = bitmapDrawable.getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] IMG = stream.toByteArray();
//        dao.InsertHinhAnh(new roomImage(0,IMG));

    }
}
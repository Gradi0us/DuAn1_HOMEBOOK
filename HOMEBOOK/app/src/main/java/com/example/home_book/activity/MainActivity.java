package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.DateCurrent;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.example.home_book.model.roomImage;
import com.example.home_book.model.rooms;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DAO dao;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate;
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

//        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.twitter_icon);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.twitter_icon);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] IMG = stream.toByteArray();

        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) getDrawable(R.drawable.fb_icon);
        Bitmap bitmap1 = bitmapDrawable1.getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] IMG1 = stream1.toByteArray();
//        dao.InsertHinhAnh(new roomImage(0,IMG));
//        List<Room> list = dao.getRoom("select * from room_tb",null);
        dao.AddRoom(new Room(5,2,Integer.parseInt("1"),Integer.parseInt("500000"),false,true,false,true,false,true,"tung","true","Hotel","location",IMG,2));

        dao.AddRoom(new Room(3,4,5,700000,false,true,true,true,true,true,"tung1","HIHI1","hoho1","HaNoi1",IMG1,4));

//        DAO dao = new DAO(this);
//        currentDate = dateFormat.format(date);
//        List<DateCurrent> currents = dao.getAllCurrent("select * from date_tb");
//
//
//        List<order> orderList = dao.getOrder("select * from order_tb");
//        //
//        if(currents.size()<=0){
//            dao.AddDateCurrent(new DateCurrent(date));
//        }else {
//            DateCurrent current = dao.getCurrent("select * from date_tb");
//            if(current.getDate().compareTo(date) >= 0){
//                Toast.makeText(this, "hihi", Toast.LENGTH_SHORT).show();
//            }else if(current.getDate() ==date ){
//                for (order x : orderList) {
//                    if (date.compareTo(x.getBooking_date()) >= 0 && date.compareTo(x.getReturn_date()) <= 0) {
//                        Room room =  dao.get1Room("select * from room_tb where id = ?", String.valueOf(x.getRoom_id()));
//                        if(room.getStatus() >0){
//                            int a = room.getStatus()-1;
//                            room.setStatus(a);
//                            Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
//                            dao.UpdateRoom(room);
//                            break;
//                        }
//                    }
//                }
//                dao.UpdateCurrent(new DateCurrent(date));
//            }
//            int b = current.getDate().compareTo(date);
//            Toast.makeText(this, ""+b, Toast.LENGTH_SHORT).show();
//        }
//        if(date.compareTo(current.getDate()) >0)
    }
}
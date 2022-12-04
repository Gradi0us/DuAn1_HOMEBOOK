package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DAO dao;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate;
    String date1 = "04/12/2022";
    Date date2 = null;
    EditText editTextEmail;
    Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAO(this);
        editTextEmail = findViewById(R.id.editTextEmail);
        cirLoginButton = findViewById(R.id.cirLoginButton);

        editTextEmail.setText("h");

        findViewById(R.id.tomainsrc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BottomNavActivity.class);
                startActivity(intent);
            }
        });

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editTextEmail.getText().toString();
                Intent i = new Intent(MainActivity.this, SearchDetailActivity.class);

                if(value.length() <= 0){
                    Toast.makeText(MainActivity.this,"HELLO HIẾU",Toast.LENGTH_SHORT).show();
                }else{
                    i.putExtra("key", value);
                    startActivity(i);
                }
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

        List<Room> list = dao.getRoom("select * from room_tb",null);
        if(list.size()==0){
            dao.AddRoom(new Room(5,2,Integer.parseInt("1"),Integer.parseInt("500000"),false,true,false,true,false,true,"tung","true","Hotel","location",IMG,2));

            dao.AddRoom(new Room(3,4,5,700000,false,true,true,true,true,true,"tung1","HIHI1","hoho1","HaNoi1",IMG1,4));

        }

        DAO dao = new DAO(this);
        currentDate = dateFormat.format(date);
        List<DateCurrent> currents = dao.getAllCurrent("select * from date_tb");
        String dataFo1 = dateFormat.format(date);
        Date dataFo2 = null;
        try {
            dataFo2= dateFormat.parse(dataFo1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<order> orderList = dao.getOrder("select * from order_tb");
        //
        //ktra dữ liệu
//        if(currents.size()==0){
//                dao.AddDateCurrent(new DateCurrent(dataFo2,0));
//        }else {
//
//            DateCurrent current = dao.getCurrent("select * from date_tb");
//            int check = current.getCheck();
//            try {
//                 date2 = dateFormat.parse(date1);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//             if(current.getDate() !=dataFo2){
//                     for (order x : orderList) {
//                         if (dataFo2.compareTo(x.getBooking_date()) >= 0 && dataFo2.compareTo(x.getReturn_date()) <= 0) {
//                             if(check ==0){
//                                 Room room =  dao.get1Room("select * from room_tb where id = ?", String.valueOf(x.getRoom_id()));
//                                 if(room.getStatus() >0){
//                                     int a = room.getStatus()-1;
//                                     room.setStatus(a);
//                                     Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
//                                     dao.UpdateRoom(room);
//
//                                 }
//                             }
//                         }else  if(dataFo2.compareTo(x.getReturn_date())>0){
//                             if(check==1){
//                                 Room room =  dao.get1Room("select * from room_tb where id = ?", String.valueOf(x.getRoom_id()));
//                                 if(room.getStatus() >0){
//                                     int a = room.getStatus()+1;
//                                     room.setStatus(a);
//                                     Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
//                                     dao.UpdateRoom(room);
//
//                                 }
//                             }
//
//                         }
//                     }
////                 current.setCheck(1);
////                 dao.UpdateCurrent(current);
//             }
//            int b = current.getDate().compareTo(date);
//            Toast.makeText(this, ""+b, Toast.LENGTH_SHORT).show();
//            Date a = current.getDate();
//            Date a1 = dataFo2;
//        }

//        đã add fomat vào đâu chỉ mới lấy ra theo tg thực tế
//        if(date.compareTo(current.getDate()) >0);
        try {
            date2 = dateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (order x: orderList){
            if(dataFo2.compareTo(x.getBooking_date())<0){
                Toast.makeText(this, "chưa đến ngày", Toast.LENGTH_SHORT).show();
            }else if(dataFo2.compareTo(x.getReturn_date())>0){
                x.setStatus(2);
                dao.UpdateOrder(x);
                Toast.makeText(this, " hết hạn", Toast.LENGTH_SHORT).show();
            }else if(dataFo2.compareTo(x.getBooking_date()) >= 0 && dataFo2.compareTo(x.getReturn_date()) <= 0){
                x.setStatus(1);
                dao.UpdateOrder(x);
                Toast.makeText(this, "Đang được sử dụng", Toast.LENGTH_SHORT).show();
            }
        }
        
        List<Room> list = dao.getRoom("select * from room_tb", null);
        dao.AddRoom(new Room(5, 2, Integer.parseInt("1"), Integer.parseInt("500000"), false, true, false, true, false, true, "tung", "true", "Hotel", "location", IMG, 2));

        dao.AddRoom(new Room(3, 4, 5, 700000, false, true, true, true, true, true, "tung1", "HIHI1", "hoho1", "HaNoi1", IMG1, 4));
    }
}
//đúng r
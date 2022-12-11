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

        editTextEmail.setText("HaNoi");

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
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.khachsan2);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] IMG = stream.toByteArray();
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) getDrawable(R.drawable.khachsan1);
        Bitmap bitmap2 = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] IMG2 = stream2.toByteArray();

        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) getDrawable(R.drawable.khachsan3);
        Bitmap bitmap1 = bitmapDrawable1.getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] IMG1 = stream1.toByteArray();
//        dao.InsertHinhAnh(new roomImage(0,IMG));
        BitmapDrawable bitmapDrawable3 = (BitmapDrawable) getDrawable(R.drawable.khachsan4);
        Bitmap bitmap3 = bitmapDrawable3.getBitmap();
        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
        byte[] IMG3 = stream3.toByteArray();

        BitmapDrawable bitmapDrawable4 = (BitmapDrawable) getDrawable(R.drawable.khachsan5);
        Bitmap bitmap4 = bitmapDrawable4.getBitmap();
        ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
        bitmap4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
        byte[] IMG4 = stream4.toByteArray();

        BitmapDrawable bitmapDrawable5 = (BitmapDrawable) getDrawable(R.drawable.khachsan6);
        Bitmap bitmap5 = bitmapDrawable5.getBitmap();
        ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
        bitmap5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
        byte[] IMG5 = stream5.toByteArray();

        BitmapDrawable bitmapDrawable6 = (BitmapDrawable) getDrawable(R.drawable.khachsan7);
        Bitmap bitmap6 = bitmapDrawable6.getBitmap();
        ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
        bitmap6.compress(Bitmap.CompressFormat.PNG, 100, stream6);
        byte[] IMG6 = stream6.toByteArray();

        BitmapDrawable bitmapDrawable7 = (BitmapDrawable) getDrawable(R.drawable.khachsan8);
        Bitmap bitmap7 = bitmapDrawable7.getBitmap();
        ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
        bitmap7.compress(Bitmap.CompressFormat.PNG, 100, stream7);
        byte[] IMG7 = stream7.toByteArray();

        BitmapDrawable bitmapDrawable8 = (BitmapDrawable) getDrawable(R.drawable.khachsan9);
        Bitmap bitmap8 = bitmapDrawable8.getBitmap();
        ByteArrayOutputStream stream8 = new ByteArrayOutputStream();
        bitmap8.compress(Bitmap.CompressFormat.PNG, 100, stream8);
        byte[] IMG8 = stream8.toByteArray();



        List<Room> list = dao.getRoom("select * from room_tb",null);
        if(list.size()==0){
            dao.AddRoom(new Room(5,2,14,2200000,true,true,true,true,true,true,"Khách sạn Sheraton Hà Nội 5 sao là sự lựa chọn phổ biến dành cho du khách ở tại thủ đô, cho dù du lịch khám phá hay chỉ ghé qua nơi đây. Cho dù là khách đi công tác hay khách đi nghỉ mát đều cảm thấy thoải mái với dịch vụ và tiện nghi tại khách sạn.","Sheraton","Apartment","HaNoi",IMG,2));

            dao.AddRoom(new Room(4,4,5,940000,true,true,true,true,true,true,"Mường Thanh Nha Trang đạt tiêu chuẩn 4 sao với nội thất cao cấp. Các phòng đều cho tầm nhìn ra thành phố, biển hoặc đồi núi, khiến không gian phòng càng thêm rộng mở, thoáng đãng, với nhiều tiện nghi và trang bị trang thiết bị hiện đại để tạo nét riêng biệt, mang lại cảm giác thư giãn, thoải mái cho khách lưu trú","Muong Thanh","Hotel","NhaTrang",IMG1,2));

            dao.AddRoom(new Room(5,2,5,1200000,true,true,true,true,true,true,"HomeBook Homestay sẽ là điểm đến tốt nhất với giá cả hợp lí cho mọi người.","HomeBook","Homestay","HCM",IMG2,3));

            dao.AddRoom(new Room(5,3,3,2300000,true,true,true,true,true,true,"FLC GrandHotel là quần thể  khách sạn du lịch sinh thái, nghỉ dưỡng sang trọng đẳng cấp đạt tiêu chuẩn 5 sao đầu tiên tại Bắc và Bắc Trung Bộ.","FLC_GrandHotel","Hotel","ThanhHoa",IMG3,4));

            dao.AddRoom(new Room(5,2,2,2900000,true,true,true,true,true,true,"Đà Nẵng Golden Bay Hotel là một khách sạn hướng về phía Tây thành phố, tầm nhìn bao trọn vịnh Đà Nẵng, rất phù hợp để ngắm hoàng hôn. Tọa lạc ngay tại vị trí nơi dòng sông Hàn đổ ra Biển Đông, Golden Bay Hotel hội tụ vẻ đẹp hài hòa của núi, biển, trời xanh và là điểm xuất phát lý tưởng để khám phá thành phố Đà Nẵng năng động.","GolderBay","Hotel","DaNang",IMG4,1));

            dao.AddRoom(new Room(3,3,5,400000,true,true,false,true,false,true,"Khách sạn Hoà Bình là hệ thống khách sạn số một tại Bắc Giang với tiêu chí hàng đầu là sự tín nghiệm của khách hàng ","Hoa Binh","Hotel","BacGiang",IMG5,2));

            dao.AddRoom(new Room(3,2,5,350000,true,true,true,false,false,true,"Homestay Đức Sensei là một Homestay đi đầu bởi giá tiền tốt và được nhiều người tin tưởng sử dụng","DucSensei","Homestay","HaNam",IMG6,5));

            dao.AddRoom(new Room(2,1,5,180000,true,true,false,true,false,true,"MinhHoang là khách sạn giá rẻ với nhiều tính năng ưu việt","MinhHoang","Hotel","LangSon",IMG7,1));

            dao.AddRoom(new Room(5,4,5,3900000,true,true,true,true,true,true,"HomeBook Apartment Situated 150 metres from Quy Nhon Beach,HomeBook APARTMENT  offers a restaurant, a shared lounge and air-conditioned accommodation with a terrace and free WiFi. Each unit has a balcony, a fully equipped kitchenette with a microwave, a seating area, a flat-screen TV, a washing machine, and a private bathroom with bidet and slippers. For added convenience, the property can provide towels and bed linen for an extra charge. Both a bicycle rental service and a car rental service are available at the apartment. The nearest airport is Phu Cat Airport, 28 km from FLC SEA TOWER APARTMENT Minh Minh.","HomeBook","Apartment","QuyNhon",IMG8,3));

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
//                dao.DeleteOrder(x.getId());
                Toast.makeText(this, " hết hạn", Toast.LENGTH_SHORT).show();
            }else if(dataFo2.compareTo(x.getBooking_date()) >= 0 && dataFo2.compareTo(x.getReturn_date()) <= 0){
                x.setStatus(1);
                dao.UpdateOrder(x);
                Toast.makeText(this, "Đang được sử dụng", Toast.LENGTH_SHORT).show();
            }
        }
        
//        List<Room> list = dao.getRoom("select * from room_tb", null);
//        dao.AddRoom(new Room(5, 2, Integer.parseInt("1"), Integer.parseInt("500000"), false, true, false, true, false, true, "tung", "true", "Hotel", "location", IMG, 2));
//
//        dao.AddRoom(new Room(3, 4, 5, 700000, false, true, true, true, true, true, "tung1", "HIHI1", "hoho1", "HaNoi1", IMG1, 4));
    }
}
//đúng r
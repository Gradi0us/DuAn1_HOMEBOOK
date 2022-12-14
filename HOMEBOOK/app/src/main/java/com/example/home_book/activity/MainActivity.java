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
                    Toast.makeText(MainActivity.this,"HELLO HI???U",Toast.LENGTH_SHORT).show();
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
//        x.setLocation("H?? N???i");
//        x.setMax_people(6);
//        x.setBeds(3);
//        x.setCost(2);
//        x.setNote("Note");
//        x.setRate(4);
//        x.setSize("To kh???ng l???");
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
            dao.AddRoom(new Room(5,2,14,2200000,true,true,true,true,true,true,"Kh??ch s???n Sheraton H?? N???i 5 sao l?? s??? l???a ch???n ph??? bi???n d??nh cho du kh??ch ??? t???i th??? ????, cho d?? du l???ch kh??m ph?? hay ch??? gh?? qua n??i ????y. Cho d?? l?? kh??ch ??i c??ng t??c hay kh??ch ??i ngh??? m??t ?????u c???m th???y tho???i m??i v???i d???ch v??? v?? ti???n nghi t???i kh??ch s???n.","Sheraton","Apartment","HaNoi",IMG,2));

            dao.AddRoom(new Room(4,4,5,940000,true,true,true,true,true,true,"M?????ng Thanh Nha Trang ?????t ti??u chu???n 4 sao v???i n???i th???t cao c???p. C??c ph??ng ?????u cho t???m nh??n ra th??nh ph???, bi???n ho???c ?????i n??i, khi???n kh??ng gian ph??ng c??ng th??m r???ng m???, tho??ng ????ng, v???i nhi???u ti???n nghi v?? trang b??? trang thi???t b??? hi???n ?????i ????? t???o n??t ri??ng bi???t, mang l???i c???m gi??c th?? gi??n, tho???i m??i cho kh??ch l??u tr??","Muong Thanh","Hotel","NhaTrang",IMG1,2));

            dao.AddRoom(new Room(5,2,5,1200000,true,true,true,true,true,true,"HomeBook Homestay s??? l?? ??i???m ?????n t???t nh???t v???i gi?? c??? h???p l?? cho m???i ng?????i.","HomeBook","Homestay","HCM",IMG2,3));

            dao.AddRoom(new Room(5,3,3,2300000,true,true,true,true,true,true,"FLC GrandHotel l?? qu???n th???  kh??ch s???n du l???ch sinh th??i, ngh??? d?????ng sang tr???ng ?????ng c???p ?????t ti??u chu???n 5 sao ?????u ti??n t???i B???c v?? B???c Trung B???.","FLC_GrandHotel","Hotel","ThanhHoa",IMG3,4));

            dao.AddRoom(new Room(5,2,2,2900000,true,true,true,true,true,true,"???? N???ng Golden Bay Hotel l?? m???t kh??ch s???n h?????ng v??? ph??a T??y th??nh ph???, t???m nh??n bao tr???n v???nh ???? N???ng, r???t ph?? h???p ????? ng???m ho??ng h??n. T???a l???c ngay t???i v??? tr?? n??i d??ng s??ng H??n ????? ra Bi???n ????ng, Golden Bay Hotel h???i t??? v??? ?????p h??i h??a c???a n??i, bi???n, tr???i xanh v?? l?? ??i???m xu???t ph??t l?? t?????ng ????? kh??m ph?? th??nh ph??? ???? N???ng n??ng ?????ng.","GolderBay","Hotel","DaNang",IMG4,1));

            dao.AddRoom(new Room(3,3,5,400000,true,true,false,true,false,true,"Kh??ch s???n Ho?? B??nh l?? h??? th???ng kh??ch s???n s??? m???t t???i B???c Giang v???i ti??u ch?? h??ng ?????u l?? s??? t??n nghi???m c???a kh??ch h??ng ","Hoa Binh","Hotel","BacGiang",IMG5,2));

            dao.AddRoom(new Room(3,2,5,350000,true,true,true,false,false,true,"Homestay ?????c Sensei l?? m???t Homestay ??i ?????u b???i gi?? ti???n t???t v?? ???????c nhi???u ng?????i tin t?????ng s??? d???ng","DucSensei","Homestay","HaNam",IMG6,5));

            dao.AddRoom(new Room(2,1,5,180000,true,true,false,true,false,true,"MinhHoang l?? kh??ch s???n gi?? r??? v???i nhi???u t??nh n??ng ??u vi???t","MinhHoang","Hotel","LangSon",IMG7,1));

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
        //ktra d??? li???u
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

//        ???? add fomat v??o ????u ch??? m???i l???y ra theo tg th???c t???
//        if(date.compareTo(current.getDate()) >0);
        try {
            date2 = dateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (order x: orderList){
            if(dataFo2.compareTo(x.getBooking_date())<0){
//                Toast.makeText(this, "ch??a ?????n ng??y", Toast.LENGTH_SHORT).show();
                x.setStatus(0);
                dao.UpdateOrder(x);
            }else if(dataFo2.compareTo(x.getReturn_date())>0){
                x.setStatus(2);
                dao.UpdateOrder(x);
//                dao.DeleteOrder(x.getId());
//                Toast.makeText(this, " h???t h???n", Toast.LENGTH_SHORT).show();
            }else if(dataFo2.compareTo(x.getBooking_date()) >= 0 && dataFo2.compareTo(x.getReturn_date()) <= 0){
                x.setStatus(1);
                dao.UpdateOrder(x);
//                Toast.makeText(this, "??ang ???????c s??? d???ng", Toast.LENGTH_SHORT).show();
            }
        }
        
//        List<Room> list = dao.getRoom("select * from room_tb", null);
//        dao.AddRoom(new Room(5, 2, Integer.parseInt("1"), Integer.parseInt("500000"), false, true, false, true, false, true, "tung", "true", "Hotel", "location", IMG, 2));
//
//        dao.AddRoom(new Room(3, 4, 5, 700000, false, true, true, true, true, true, "tung1", "HIHI1", "hoho1", "HaNoi1", IMG1, 4));
    }
}
//????ng r
package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.home_book.DAO.DAO;
import com.example.home_book.fragment.FavouriteFragment;
import com.example.home_book.fragment.Fragment3;
import com.example.home_book.fragment.fragmentNav.AcountFragment;
import com.example.home_book.model.Favourite;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.example.home_book.model.user;
import com.example.home_book.slideshow.The_Slide_Items_Model_Class;
import com.example.home_book.slideshow.adapter.The_Slide_Items_Pager_Adapter;
import com.example.home_book.R;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class OrderAcivity extends AppCompatActivity {
    LinearLayout imgWifi, imgAC, imgParking, imgBuffet, imgPool, imgMinibar;
    TextView tvLocation, tvBeds, tvName, tvCategory, tvNote, tvDetails, tvCost;
    RatingBar ratingBar;
    private List<The_Slide_Items_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;
    EditText edtBookingDate, edtReturnDate;
    Date dateBooking;
    Date dateReturn;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Button btnOrder;
    DAO dao;
    int id_room, status, cost, beds, rate, id_user;
    String name, category, note, location;
    String name_user, member_id;
    int dD, mM, yY, role;

    ToggleButton timF;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate;
    byte[] img;
    boolean wifi,parking,buffet,ac,pool,minibar;
    //System.out.println(dateFormat.format(date));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_room_detail);
        imgWifi = findViewById(R.id.img_wifi);
        imgAC = findViewById(R.id.img_ac);
        imgParking = findViewById(R.id.img_parking);
        imgPool = findViewById(R.id.img_pool);
        imgBuffet = findViewById(R.id.img_buffet);
        imgMinibar = findViewById(R.id.img_minibar);
        tvBeds = findViewById(R.id.tv_beds_order);
        tvLocation = findViewById(R.id.tv_location_order);
        tvName = findViewById(R.id.hotel_name_roomsdetail);
        tvCategory = findViewById(R.id.tv_category_order);
//        tvDetails = findViewById(R.id.tv_details_order);
        tvNote = findViewById(R.id.tv_note_order);
        tvCost = findViewById(R.id.tv_cost_order);
        ratingBar = findViewById(R.id.number_stars);
        edtBookingDate = findViewById(R.id.edt_bookingdate);
        edtReturnDate = findViewById(R.id.edt_returndate);
        btnOrder = findViewById(R.id.btn_order);

        timF = findViewById(R.id.timFavourite);

        //
        currentDate = dateFormat.format(date);
        //
        
        dao = new DAO(this);
        Calendar calendar = Calendar.getInstance();
        yY = calendar.get(Calendar.YEAR);
        mM = calendar.get(Calendar.MONTH);
        dD = calendar.get(Calendar.DATE);
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");
        if (bundle != null) {
//            name = bundle.getString("name");
//            category = bundle.getString("category");
//            note = bundle.getString("note");
            id_room = bundle.getInt("id");
            Room room = dao.get1Room("select * from room_tb where id = ?",String.valueOf(id_room));
//            location = bundle.getString("location");
//            status = bundle.getInt("status");
//            beds = bundle.getInt("beds");
//            cost = bundle.getInt("cost");
//            rate = bundle.getInt("rate");
//            byte[] img = bundle.getByteArray("img");
//            boolean wifi = false;
//            boolean parking = bundle.getBoolean("parking");
//            boolean pool = bundle.getBoolean("pool");
//            boolean minibar = bundle.getBoolean("minibar");
//            boolean ac = bundle.getBoolean("ac");
//            boolean buffet = true;
            name = room.getName();
            category = room.getCategory();
            note = room.getNote();
            location = room.getLocation();
            status = room.getStatus();
            beds = room.getBeds();
            cost = room.getCost();
            rate = room.getRate();
            img = room.getIMG();
            wifi = room.isWifi();
            ac = room.isAc();
            pool = room.isPool();
            parking = room.isParking();
            buffet = room.isBuffet();
            minibar = room.isMinibar();
            tvLocation.setText(location);
            switch (room.getBeds()){
                case 0:tvBeds.setText("Phòng đơn");break;
                case 1:tvBeds.setText("Phòng sinh đôi");break;
                case 2:tvBeds.setText("Phòng đôi");break;
                case 3:tvBeds.setText("Phòng ba");break;
                case 4:tvBeds.setText("Phòng bốn");break;
            }
            tvName.setText(name);
            ratingBar.setRating(rate);
            tvCategory.setText(category);
            tvNote.setText(note);
//            tvDetails.setText(String.valueOf(status));
            tvCost.setText(String.valueOf(cost));
            if (wifi == true) {
                imgWifi.setVisibility(View.VISIBLE);
            } else {
                imgWifi.setVisibility(View.GONE);
            }
            if (ac == true) {
                imgAC.setVisibility(View.VISIBLE);
            } else {
                imgAC.setVisibility(View.GONE);
            }
            if (parking == true) {
                imgParking.setVisibility(View.VISIBLE);
            } else {
                imgParking.setVisibility(View.GONE);
            }
            if (pool == true) {
                imgPool.setVisibility(View.VISIBLE);
            } else {
                imgPool.setVisibility(View.GONE);
            }
            if (minibar == true) {
                imgMinibar.setVisibility(View.VISIBLE);
            } else {
                imgMinibar.setVisibility(View.GONE);
            }
            if (buffet == true) {
                imgBuffet.setVisibility(View.VISIBLE);
            } else {
                imgBuffet.setVisibility(View.GONE);
            }

        }

        SharedPreferences sP = getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");

        if (dao.checkLogin(email, pass)) {
            user x = dao.get1User("select * from user_tb where email = ?", email);
            if (dao.checkFavourite(id_room + "", x.getId() + "")) {
                timF.setChecked(true);
            } else {
                timF.setChecked(false);
            }
        } else {
            timF.setChecked(false);
        }

        timF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room r = dao.get1Room("select * from room_tb where id = ?", id_room + "");
                if (email.equals("") || pass.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderAcivity.this);
                    builder.setMessage("Please login!");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    timF.setChecked(false);
                } else {
                    user u = dao.get1User("select * from user_tb where email = ?", email);
                    Favourite f = new Favourite(r.getId(), u.getId());
                    if (timF.isChecked()) {
                        dao.AddFavourite(f);
                        Toast.makeText(OrderAcivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        Log.d("add", "Thêm thành công");
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(OrderAcivity.this);
                        builder.setMessage("Bạn có muốn xóa không?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Favourite fa = dao.get1Favourite(r.getId()+"",u.getId()+"");
                                dao.DeleteFavourite(fa.getId());
                                Toast.makeText(OrderAcivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                timF.setChecked(false);
                            }
                        });
                        builder.setNegativeButton("KO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                timF.setChecked(true);
                            }
                        });
                        android.app.AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Log.d("add", "Xóa thành công");
                    }
                }
            }
        });

//        timF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Room r = dao.get1Room("select * from room_tb where id = ?",id_room+"");
//                if(email.equals("") || pass.equals("")){
//
//                }else{
//                    user u = dao.get1User("select * from user_tb where email = ?", email);
//                    id_user = u.getId();
//                    Favourite f = new Favourite(r.getId(),id_user);
//                    if(isChecked){
//                        dao.AddFavourite(f);
//                    }else{
//                        favouriteFragment.showDialogXoa(r.getId()+"",id_user+"" );
//                    }
//                }
//            }
//        });

        page = findViewById(R.id.my_pager);
        tabLayout = findViewById(R.id.my_tablayout);

        listItems = new ArrayList<>();
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan1, "Slider 1 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan2, "Slider 2 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan3, "Slider 3 Title"));

        The_Slide_Items_Pager_Adapter itemsPager_adapter = new The_Slide_Items_Pager_Adapter(this, listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(), 2000, 3000);
        tabLayout.setupWithViewPager(page, true);

        edtBookingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        int dD, mM, yY, role;
                        yY = year;
                        mM = month;
                        dD = dayOfMonth;
                        GregorianCalendar gC = new GregorianCalendar(yY, mM, dD);
                        edtBookingDate.setText(format.format(gC.getTime()));
                        try {
                            dateBooking = format.parse(format.format(gC.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                };

                DatePickerDialog d = new DatePickerDialog(OrderAcivity.this, 0, chonDate, yY, mM, dD);
                d.show();
            }
        });
        edtReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                        yY = year;
                        mM = month;
                        dD = dayOfMonth;
                        GregorianCalendar gC = new GregorianCalendar(yY, mM, dD);
                        edtReturnDate.setText(format.format(gC.getTime()));
                        try {
                            dateReturn = format.parse(format.format(gC.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                };

                DatePickerDialog d = new DatePickerDialog(OrderAcivity.this, 0, chonDate, yY, mM, dD);
                d.show();
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sethorizontal();

            }
        });

    }

    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {

            OrderAcivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem() < listItems.size() - 1) {
                        page.setCurrentItem(page.getCurrentItem() + 1);
                    } else
                        page.setCurrentItem(0);
                }
            });
        }
    }

    private void sethorizontal() {
        SharedPreferences sP = getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");
        if (email.equals("") || pass.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please login!");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager
//                            .beginTransaction()
//                            .replace(R.id.,new Fragment3())
//                            .commit();
                }
            });
            builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {

            dao = new DAO(this);
            if (dao.checkLogin(email, pass)) {
                user x = dao.get1User("select * from user_tb where email = ?", email);
                name_user = x.getFullname();
                id_user = x.getId();

// duoi cua main

                if (!edtReturnDate.getText().toString().equals("") && !edtBookingDate.getText().toString().equals("")) {
                    if (date.compareTo(dateBooking) > 0) {
                        Toast.makeText(this, "Ngày đặt phải sau ngày hiện tại", Toast.LENGTH_SHORT).show();
                    } else if (date.compareTo(dateReturn) > 0) {
                        Toast.makeText(this, "Ngày trả phải sau ngày hiện tại", Toast.LENGTH_SHORT).show();
                    } else if (dateBooking.compareTo(dateReturn) >= 0) {
                        Toast.makeText(this, "Ngày đặt phải trước ngày trả", Toast.LENGTH_SHORT).show();
                    } else {
                        DAO dao = new DAO(this);
                        if (dao.checkLogin(email, pass)) {
                            user x1 = dao.get1User("select * from user_tb where email = ?", email);
                            name_user = x1.getFullname();
                            id_user = x1.getId();

                            long diff = dateReturn.getTime() - dateBooking.getTime();
                            int dayCount = (int) diff/(24 * 60 * 60 * 1000);

                            if(x1.getMoney() >= (cost*dayCount)){
                                dao.AddOrder(new order(id_user, 5, dateBooking, dateReturn, "a", "b", id_room, note, 0,(cost*dayCount)));
                                Toast.makeText(this, "Order thành công. Cọc 5% tiền.", Toast.LENGTH_SHORT).show();
                                x1.setMoney(x1.getMoney()-((cost*dayCount)*5/100));
                                dao.UpdateUser(x1);
                            }else{
                                Toast.makeText(this, "Không đủ tiền", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Không được bỏ trống ngày đặt và ngày trả", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }}


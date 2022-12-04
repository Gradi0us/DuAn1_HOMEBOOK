package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.model.Room;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchDetailActivity extends AppCompatActivity {

    TextInputEditText editLocation, editSoNguoi;
    RatingBar star;
    Button search;
    RecyclerView recyclerView;
    DAO dao;
    Spinner spinnerBed, spinnerCategory;
    EditText beginD, returnD;
    int yy, MM, dd, chia;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail);

        String value = getIntent().getStringExtra("key");

        editLocation = findViewById(R.id.editLocation);
        editSoNguoi = findViewById(R.id.editSoNguoi);
        spinnerBed = findViewById(R.id.loai_giuong);
        spinnerCategory = findViewById(R.id.spn_category);
        recyclerView = findViewById(R.id.recyclerViewList);
        star = findViewById(R.id.star_homebook);
        search = findViewById(R.id.searchDetailButton);
        beginD = findViewById(R.id.beginNgay);
        returnD = findViewById(R.id.returnNgay);

        dao = new DAO(this);

        editLocation.setText(value);

        timCho(value);

        ArrayList<String> bed = new ArrayList<>();
        bed.add("Tất cả loại phòng");
        bed.add("Single Room");
        bed.add("Twin Room");
        bed.add("Double Room");
        bed.add("Triple Room");
        bed.add("Quad Room");
        ArrayAdapter adapterBed = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bed);
        spinnerBed.setAdapter(adapterBed);
        ArrayList<String> category = new ArrayList<>();
        category.add("Tất cả");
        category.add("Hotel");
        category.add("Homestay");
        category.add("Apartment");
        ArrayAdapter adapterCategory = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        spinnerCategory.setAdapter(adapterCategory);

        beginD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yy = year;
                        MM = month;
                        dd = dayOfMonth;
                        GregorianCalendar gC = new GregorianCalendar(yy, MM, dd);
                        beginD.setText(format.format(gC.getTime()));
                    }
                };

                Calendar calendar = Calendar.getInstance();
                yy = calendar.get(Calendar.YEAR);
                MM = calendar.get(Calendar.MONTH);
                dd = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(SearchDetailActivity.this, 0, chonDate, yy, MM, dd);
                d.show();
            }
        });

        returnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yy = year;
                        MM = month;
                        dd = dayOfMonth;
                        GregorianCalendar gC = new GregorianCalendar(yy, MM, dd);
                        returnD.setText(format.format(gC.getTime()));
                    }
                };

                Calendar calendar = Calendar.getInstance();
                yy = calendar.get(Calendar.YEAR);
                MM = calendar.get(Calendar.MONTH);
                dd = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(SearchDetailActivity.this, 0, chonDate, yy, MM, dd);
                d.show();
            }
        });


        editLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                timCho(editLocation.getText().toString().trim());
            }
        });

//        editSoNguoi.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                timNguoi(editSoNguoi.getText().toString().trim());
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bedP = spinnerBed.getSelectedItemPosition() - 1;
                String categoryP = (String) spinnerCategory.getSelectedItem();
                String ng = editSoNguoi.getText().toString().trim();
                int starRating = (int) star.getRating();

                if (ng.equals("")){
                    chia = 0;
                }else{
                    if (Integer.parseInt(ng) >= 0) {
                        if(bedP == -1){
                            chia = 0;
                        }
                        if (bedP == 0) {
                            chia = Integer.parseInt(ng) / 1;
                        }
                        if (bedP == 1) {
                            chia = lamTron(Integer.parseInt(ng),2);
                        }
                        if (bedP == 2) {
                            chia = lamTron(Integer.parseInt(ng),2);
                        }
                        if (bedP == 3) {
                            chia = lamTron(Integer.parseInt(ng),3);
                        }
                        if (bedP == 4) {
                            chia = lamTron(Integer.parseInt(ng),4);
                        }
                    }
                }

//                timPhong(categoryP);
//                timGiuong(bedP + "");
                tim(chia+"", bedP + "", categoryP, starRating + "");
            }
        });

    }

    private void timCho(String timkiem) {
        String sql = " SELECT * FROM room_tb where location like '%" + timkiem + "%'";
        ArrayList<Room> list;
        if (!timkiem.isEmpty()) {
            list = (ArrayList<Room>) dao.getRoom(sql, null);
        } else {
            list = (ArrayList<Room>) dao.getRoom("select * from room_tb", null);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);
    }

    private void timNguoi(String timkiem) {
        if (timkiem.isEmpty()) {
            timkiem = "0";
        }

        String sql = " SELECT * FROM room_tb where number_people >= ?";
        ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql, timkiem.trim());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);
    }

    private void timGiuong(String timkiem) {
        String sql = " SELECT * FROM room_tb where beds = ?";
        ArrayList<Room> list1;

        if (timkiem.equals("-1")) {
            list1 = (ArrayList<Room>) dao.getRoom(" SELECT * FROM room_tb", null);
        } else {
            list1 = (ArrayList<Room>) dao.getRoom(sql, timkiem);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);
    }

    private void timPhong(String timkiem) {
        if (timkiem.equals("Tất cả")) {
            timkiem = "%";
        }
        String sql = " SELECT * FROM room_tb where category_name like ?";

        ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql, timkiem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);
    }

    private void timSao(String timkiem) {
        String sql = " SELECT * FROM room_tb where rate >= ?";
        if (!timkiem.isEmpty()) {
            ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql, timkiem);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
            recyclerView.setAdapter(homeBookApdater);
        } else {
            ArrayList<Room> list2 = (ArrayList<Room>) dao.getRoom("select * from room_tb", null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list2, SearchDetailActivity.this);
            recyclerView.setAdapter(homeBookApdater);
        }
    }

    private void tim(String x, String y, String z, String s) {
        String sql = " SELECT * FROM room_tb where status >= ? and beds = ? and category_name like ? and rate >= ?";
        ArrayList<Room> list;


//        if (x.isEmpty()) {
//            x = "0";
//        }
        if (z.equals("Tất cả")) {
            z = "%";
        }

        if (y.equals("-1")) {
            sql = " SELECT * FROM room_tb where status >= ? and category_name like ? and rate >= ?";
            list = (ArrayList<Room>) dao.getRoom(sql, x, z, s);
        } else {
            list = (ArrayList<Room>) dao.getRoom(sql, x, y, z, s);
        }

        Log.d("tag", x + " + " + y + " + " + z + " + " + s);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);

    }

    private int lamTron(int ng, int i){
        int kq = 0;
        if(ng%i != 0){
            kq = Math.round(ng/i) + 1;
        }else{
            kq = Math.round(ng/i);
        }
        return kq;
    }


}
package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.model.Room;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SearchDetailActivity extends AppCompatActivity {

    TextInputEditText editLocation, editSoNguoi, editPhongDu, editSoGiuong;
    RatingBar star;
    Button search;
    RecyclerView recyclerView;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail);

        String value = getIntent().getStringExtra("key");

        editLocation = findViewById(R.id.editLocation);
        editSoNguoi = findViewById(R.id.editSoNguoi);
        editPhongDu = findViewById(R.id.editPhongDu);
        editSoGiuong = findViewById(R.id.editSoGiuong);
        recyclerView = findViewById(R.id.recyclerViewList);
        star = findViewById(R.id.star_homebook);
        search = findViewById(R.id.searchDetailButton);

        dao = new DAO(this);

        editLocation.setText(value);

        timCho(value);

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
//
//        editPhongDu.addTextChangedListener(new TextWatcher() {
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
//                timPhong(editPhongDu.getText().toString().trim());
//            }
//        });
//
//        editSoGiuong.addTextChangedListener(new TextWatcher() {
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
//                timGiuong(editSoGiuong.getText().toString().trim());
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timNguoi(editSoNguoi.getText().toString().trim());
//                timPhong(editPhongDu.getText().toString().trim());
//                timGiuong(editSoGiuong.getText().toString().trim());
//                timSao(Math.round(star.getRating()) + "");

                tim(editSoNguoi.getText().toString().trim(),
                        editPhongDu.getText().toString().trim(),
                        editSoGiuong.getText().toString().trim(),
                        Math.round(star.getRating()) + "");
            }
        });

    }

    private void timCho(String timkiem) {
        String sql = " SELECT * FROM room_tb where location like '%" + timkiem + "%'";
        if (!timkiem.isEmpty()) {
            ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql, null);
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

    private void timNguoi(String timkiem) {
        String sql = " SELECT * FROM room_tb where number_people >= ?";
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

    private void timGiuong(String timkiem) {
        String sql = " SELECT * FROM room_tb where beds >= ?";
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

    private void timPhong(String timkiem) {
        String sql = " SELECT * FROM room_tb where status >= ?";
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

        if(x.isEmpty()){
            x = "0";
        }
        if(y.isEmpty()){
            y = "0";
        }
        if(z.isEmpty()){
            z = "0";
        }

        Log.d("tag", x + " + " + y + " + " + z + " + " + s);
        String sql = " SELECT * FROM room_tb where number_people >= ? and beds >= ? and status >= ? and rate >= ?";

        ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql, x, y, z, s);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
        recyclerView.setAdapter(homeBookApdater);

    }
}
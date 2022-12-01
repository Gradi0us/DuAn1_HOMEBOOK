package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    TextInputEditText editLocation,editSoNguoi,editPhongDu;
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
        recyclerView = findViewById(R.id.recyclerViewList);

        dao = new DAO(this);

        editLocation.setText(value);

        tim(value);

        editLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tim(editLocation.getText().toString().trim());
            }
        });

    }

    private void tim(String timkiem){
        String sql = " SELECT * FROM room_tb where location like '%" + timkiem + "%'";
        if (!timkiem.isEmpty()) {
            ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql,null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this,LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list1, SearchDetailActivity.this);
            recyclerView.setAdapter(homeBookApdater);
        } else {
            ArrayList<Room> list2 = (ArrayList<Room>) dao.getRoom("select * from room_tb",null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDetailActivity.this,LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            HomeBookApdater homeBookApdater = new HomeBookApdater(SearchDetailActivity.this, list2, SearchDetailActivity.this);
            recyclerView.setAdapter(homeBookApdater);
        }
    }
}
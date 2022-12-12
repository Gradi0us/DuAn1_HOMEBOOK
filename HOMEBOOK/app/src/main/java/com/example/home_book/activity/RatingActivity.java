package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.BookingListAdapter;
import com.example.home_book.adapter.RatingAdapter;
import com.example.home_book.model.order;
import com.example.home_book.model.rating;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int user_id, room_id;
    RadioButton btnRatte, btnTe, btnBinhThuong, btnTot, btnRatTot;
    Button btnDanhGia;
    DAO dao;
    int danhgia;
    EditText edtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        recyclerView = findViewById(R.id.ds_rating);
        btnRatte = findViewById(R.id.btn_ratte);
        btnTe = findViewById(R.id.btn_te);
        btnBinhThuong = findViewById(R.id.btn_binhthuong);
        btnTot = findViewById(R.id.btn_tot);
        btnRatTot = findViewById(R.id.btn_rattot);
        btnDanhGia = findViewById(R.id.btn_addRating);
        edtNote = findViewById(R.id.edt_noteRating);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        user_id = bundle.getInt("user_id");
        room_id = bundle.getInt("room_id");
        dao = new DAO(this);
        List<rating> list = dao.GetAllRating("select * from rating_tb where room_id = " + room_id + "");
        loadData(list);
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnRatte.isChecked()) {
                    danhgia = 1;
                } else if (btnTe.isChecked()) {
                    danhgia = 2;
                } else if (btnBinhThuong.isChecked()) {
                    danhgia = 3;
                } else if (btnTot.isChecked()) {
                    danhgia = 4;
                } else if (btnRatTot.isChecked()) {
                    danhgia = 5;
                }
                List<rating> listCheck = dao.GetAllRating("select * from rating_tb where room_id = " + room_id + " and user_id ="+user_id+"");
                if(listCheck.size()==0){
                    String note = edtNote.getText().toString();
                    rating rating = new rating(user_id, room_id, danhgia, note);
                    dao.AddRating(rating);
                    List<rating> list = dao.GetAllRating("select * from rating_tb where room_id = " + room_id + "");
                    loadData(list);
                }else {
                    Toast.makeText(RatingActivity.this, "Bạn đã đánh giá", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void loadData(List<rating> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RatingAdapter adapter = new RatingAdapter(this, (ArrayList<rating>) list);
        recyclerView.setAdapter(adapter);
    }
}
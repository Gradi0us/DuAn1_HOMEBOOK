package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.RatingAdapter;
import com.example.home_book.model.rating;

import java.util.ArrayList;
import java.util.List;

public class ViewRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewrate);
        RecyclerView recyclerView = findViewById(R.id.ds_rate);
        DAO dao = new DAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int id_room = bundle.getInt("room_id");
        List<rating> list = dao.GetAllRating("select * from rating_tb where room_id =" + id_room + "");
        if (list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            RatingAdapter homeBookApdater = new RatingAdapter(this, (ArrayList<rating>) list);
            recyclerView.setAdapter(homeBookApdater);
        } else {
            DialongNull();
        }
    }
    public void DialongNull() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View alert = LayoutInflater.from(this).inflate(R.layout.layout_viewrate,null);
//        builder.setContentView(R.layout.layout_viewrate);
        builder.setMessage("Rating is a null!");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.create();
        alertDialog.show();
    }
}
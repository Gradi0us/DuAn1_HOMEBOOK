package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_book.R;

public class OrderAcivity extends AppCompatActivity {
    LinearLayout imgWifi,imgAC,imgParking,imgBuffet,imgPool,imgMinibar;
    TextView tvLocation,tvBeds,tvName,tvCategory,tvNote,tvDetails,tvCost;
    RatingBar ratingBar;
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
        tvDetails = findViewById(R.id.tv_details_order);
        tvNote = findViewById(R.id.tv_note_order);
        tvCost = findViewById(R.id.tv_cost_order);
        ratingBar = findViewById(R.id.number_stars);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");
        if(bundle!=null){
            String name = bundle.getString("name");
            String category = bundle.getString("category");
            String note = bundle.getString("note");
            int id = bundle.getInt("id");
            String location = bundle.getString("location");
            int status = bundle.getInt("status");
            int beds = bundle.getInt("beds");
            int cost = bundle.getInt("cost");
            int rate = bundle.getInt("rate");
            byte[] img = bundle.getByteArray("img");
            boolean wifi = false;
            boolean parking = bundle.getBoolean("parking");
            boolean pool = bundle.getBoolean("pool");
            boolean minibar = bundle.getBoolean("minibar");
            boolean ac = bundle.getBoolean("ac");
            boolean buffet = true;
            tvLocation.setText(location);
            tvBeds.setText(String.valueOf(beds));
            tvName.setText(name);
            ratingBar.setRating(rate);
            tvCategory.setText(category);
            tvNote.setText(note);
            tvDetails.setText(String.valueOf(status));
            tvCost.setText(String.valueOf(cost));
            if(wifi==true){
                imgWifi.setVisibility(View.VISIBLE);
            }else {
                imgWifi.setVisibility(View.GONE);
            }
            if(ac==true){
                imgAC.setVisibility(View.VISIBLE);
            }else {
                imgAC.setVisibility(View.GONE);
            }
            if(parking==true){
                imgParking.setVisibility(View.VISIBLE);
            }else {
                imgParking.setVisibility(View.GONE);
            }
            if(pool==true){
                imgPool.setVisibility(View.VISIBLE);
            }else {
                imgPool.setVisibility(View.GONE);
            }
            if(minibar==true){
                imgMinibar.setVisibility(View.VISIBLE);
            }else {
                imgMinibar.setVisibility(View.GONE);
            }
            if(buffet==true){
                imgBuffet.setVisibility(View.VISIBLE);
            }else {
                imgBuffet.setVisibility(View.GONE);
            }

        }
    }
}
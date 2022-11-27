package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.home_book.R;

public class OrderAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_room_detail);
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");
        if(bundle!=null){

        }
    }
}
package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.home_book.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText birthUp;
    int dD,mM,yY;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        birthUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                yY = calendar.get(Calendar.YEAR);
                mM = calendar.get(Calendar.MONTH);
                dD = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(RegisterActivity.this,0,chonDate,yY,mM,dD);
                d.show();
            }
        });

    }

    DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yY = year; mM = month; dD = dayOfMonth;
            GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
            birthUp.setText(format.format(gC.getTime()));
        }
    };
}
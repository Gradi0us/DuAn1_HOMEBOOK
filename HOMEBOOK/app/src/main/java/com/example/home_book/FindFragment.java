package com.example.home_book;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FindFragment extends Fragment {

    TextInputEditText ngayNhan,ngayTra;
    int dD,mM,yY;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);


        ngayNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                yY = calendar.get(Calendar.YEAR);
                mM = calendar.get(Calendar.MONTH);
                dD = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,dateNhan,yY,mM,dD);
                d.show();
            }
        });

        ngayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                yY = calendar.get(Calendar.YEAR);
                mM = calendar.get(Calendar.MONTH);
                dD = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,dateTra,yY,mM,dD);
                d.show();
            }
        });

        return view;
    }

    private void MoveAnimation(){
        Animation anime = new TranslateAnimation(-1000,Animation.ABSOLUTE,Animation.ABSOLUTE,Animation.ABSOLUTE);
        anime.setDuration(1000);
        anime.setFillAfter(true);
    }

    DatePickerDialog.OnDateSetListener dateNhan = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yY = year; mM = month; dD = dayOfMonth;
            GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
            ngayNhan.setText(format.format(gC.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener dateTra = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yY = year; mM = month; dD = dayOfMonth;
            GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
            ngayTra.setText(format.format(gC.getTime()));
        }
    };
}
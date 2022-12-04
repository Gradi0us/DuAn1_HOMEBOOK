package com.example.home_book.fragment.fragmentNav;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RateFragment extends Fragment {
    Button btntungay,btndenngay,btndoanhthu;
    EditText edttungay,edtdenngay;
    TextView tvdoanhthu;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    int myear,mmonth,mday;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_rate, container, false);
        edtdenngay=v.findViewById(R.id.eddenngay);
        edttungay=v.findViewById(R.id.edtungay);
        tvdoanhthu=v.findViewById(R.id.tvDoanhthu);
        btntungay=v.findViewById(R.id.btnTungay);
        btndenngay=v.findViewById(R.id.btndenngay);
        btndoanhthu=v.findViewById(R.id.btndoanhthu);
        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                myear=c.get(Calendar.YEAR);
                mmonth=c.get(Calendar.MONTH);
                mday=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDatetungay,myear,mmonth,mday);
                d.show();

            }
        });

        btndenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                myear=c.get(Calendar.YEAR);
                mmonth=c.get(Calendar.MONTH);
                mday=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDatedenngay,myear,mmonth,mday);
                d.show();
            }
        });
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay=edttungay.getText().toString();
                String denngay=edtdenngay.getText().toString();
                DAO dao = new DAO(getActivity());
                tvdoanhthu.setText("Doanh thu: "+dao.getDoanhThu(tungay,denngay)+" VNĐ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDatetungay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myear=year;
            mmonth=month;
            mday=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(myear,mmonth,mday);
            edttungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDatedenngay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myear=year;
            mmonth=month;
            mday=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(myear,mmonth,mday);
            edtdenngay.setText(sdf.format(c.getTime()));
        }
    };


}
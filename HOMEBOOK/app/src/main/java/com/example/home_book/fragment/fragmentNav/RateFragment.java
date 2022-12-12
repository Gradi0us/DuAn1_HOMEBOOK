package com.example.home_book.fragment.fragmentNav;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RateFragment extends Fragment {
    Button btntungay,btndenngay,btndoanhthu;
    EditText edttungay,edtdenngay;
    TextView tvdoanhthuPhong,tvdoanhthuCaNhan;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    int myear,mmonth,mday;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_rate, container, false);

        SharedPreferences sP = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        DAO dao = new DAO(getActivity());

        user x = dao.get1User("select * from user_tb where email = ?", email);

        edtdenngay=v.findViewById(R.id.eddenngay);
        edttungay=v.findViewById(R.id.edtungay);
        tvdoanhthuPhong=v.findViewById(R.id.tvDoanhthuPhong);
        tvdoanhthuCaNhan=v.findViewById(R.id.tvDoanhthuCaNhan);
        btntungay=v.findViewById(R.id.btnTungay);
        btndenngay=v.findViewById(R.id.btndenngay);
        btndoanhthu=v.findViewById(R.id.btndoanhthu);

        edtdenngay.setText(sdf.format(new Date()));
        edttungay.setText(sdf.format(new Date()));

        LoadData(x,edttungay.getText().toString(),edtdenngay.getText().toString());

        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Log.d("ngay",tungay + " - " + denngay);
                LoadData(x,tungay,denngay);
            }
        });
        return v;
    }

    private void LoadData(user x,String tungay,String denngay){
        DAO dao = new DAO(getActivity());
        tvdoanhthuPhong.setText("Room revenue: "+dao.getDoanhThuPhong(x.getId()+"",tungay,denngay)+" VNĐ");
        tvdoanhthuCaNhan.setText("Your revenue: "+dao.getDoanhThuCaNhan(x.getId()+"",tungay,denngay)+" VNĐ");
    }

}
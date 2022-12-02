package com.example.home_book.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.user;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RegisterFragment extends Fragment {

    TextInputEditText birthUp,emailUp,passUp,passUpAgain,nameUp,phoneUp;
    RadioButton radioCollaborate,radioMember;
    ArrayList<user> list = new ArrayList<>();
    int dD,mM,yY,role;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Button signUp;
    DAO dao;
    Date date;
    TextView already,clickForDetails;
    CheckBox checkAccept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        view.findViewById(R.id.thoat1).setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame,new Fragment3())
                    .commit();
        });

        AnhXa(view);

        DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yY = year; mM = month; dD = dayOfMonth;
                GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
                birthUp.setText(format.format(gC.getTime()));
                date = gC.getTime();
            }
        };
        birthUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                yY = calendar.get(Calendar.YEAR);
                mM = calendar.get(Calendar.MONTH);
                dD = calendar.get(Calendar.DATE);

                DatePickerDialog d = new DatePickerDialog(getActivity(),0,chonDate,yY,mM,dD);
                d.show();
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame,new LoginFragment())
                        .commit();
            }
        });

        clickForDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Điều khoản
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUp.getText().toString();
                String pass = passUp.getText().toString();
                String passAgain = passUpAgain.getText().toString();
                String name = nameUp.getText().toString();
                String phone = phoneUp.getText().toString();
                String birth = birthUp.getText().toString();
                Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

                Boolean check = true;
                role = 1;
                int ava = 0,money = 0;

                if(!radioCollaborate.isChecked() && !radioMember.isChecked()){
                    check = false;
                    Toast.makeText(getActivity(),"Choose your role",Toast.LENGTH_SHORT).show();
                    radioCollaborate.setAnimation(animShake);
                    radioMember.setAnimation(animShake);
                }
                if(!checkAccept.isChecked()){
                    check = false;
                    Toast.makeText(getActivity(),"Accept the license",Toast.LENGTH_SHORT).show();
                    checkAccept.setAnimation(animShake);
                }
                if(email.trim().length() <= 0){
                    check = false;
                    emailUp.setError("Enter the email.");
                }
                if(pass.trim().length() <= 0){
                    check = false;
                    passUp.setError("Enter the password.");
                }
                if(passAgain.trim().length() <= 0){
                    check = false;
                    passUpAgain.setError("Enter the password again.");
                }
                if(birth.trim().length() <= 0){
                    check = false;
                    birthUp.setError("Enter the birthday.");
                }
                if(name.trim().length() <= 0){
                    check = false;
                    nameUp.setError("Enter your name.");
                }
                if(phone.trim().length() <= 0){
                    check = false;
                    phoneUp.setError("Enter your phone number.");
                }
                else if(!passAgain.equals(pass)){
                    check = false;
                    passUpAgain.setError("The password again is not same as the password.");
                }

                if(check == true){
                    if(radioCollaborate.isChecked()){
                        role = 0;
                    }else if(radioMember.isChecked()){
                        role = 1;
                    }
                    Log.d("User","ADD OK");
                    user x = new user(ava,name,email,pass,date,phone,role,money);
                    dao.AddUser(x);
                    list.add(x);
                    Toast.makeText(getActivity(),"Add completed",Toast.LENGTH_SHORT).show();
                    list = (ArrayList<user>) dao.getUser("select * from user_tb",null);
                }
            }
        });

        return view;
    }

    private void AnhXa(View view){
        signUp = view.findViewById(R.id.signUp);
        emailUp = view.findViewById(R.id.emailUp);
        passUp = view.findViewById(R.id.passUp);
        passUpAgain = view.findViewById(R.id.passUpAgain);
        nameUp = view.findViewById(R.id.nameUp);
        phoneUp = view.findViewById(R.id.phoneUp);
        birthUp = view.findViewById(R.id.birthUp);
        checkAccept = view.findViewById(R.id.checkAccept);
        clickForDetails = view.findViewById(R.id.clickForDetails);
        radioCollaborate = view.findViewById(R.id.collaborateUP);
        radioMember = view.findViewById(R.id.memberUp);
        dao = new DAO(getActivity());
        already = view.findViewById(R.id.backToLogin);
    }

}
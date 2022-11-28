package com.example.home_book.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {

    TextInputEditText emailIn,passIn;
    CheckBox remember;
    TextView forget,create;
    Button signIn;
    DAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_login, container, false);
       view.findViewById(R.id.thoat).setOnClickListener(v -> {
           FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
           fragmentManager
                   .beginTransaction()
                   .replace(R.id.frame,new Fragment3())
                   .commit();
       });
        emailIn = view.findViewById(R.id.emailIn);
        passIn = view.findViewById(R.id.passIn);
        signIn = view.findViewById(R.id.signIn);
        remember = view.findViewById(R.id.rememberBox);
        forget = view.findViewById(R.id.quenMatKhau);
        create = view.findViewById(R.id.dangky);
        dao = new DAO(getContext());

        SharedPreferences sP = getActivity().getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
        Boolean rem = sP.getBoolean("Remember",false);
        
        emailIn.setText(email);
        passIn.setText(pass);
        remember.setChecked(rem);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame,new RegisterFragment())
                        .commit();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eI = emailIn.getText().toString();
                String pI = passIn.getText().toString();
                SharedPreferences.Editor editor = sP.edit();
                Boolean check = true;
                if(eI.trim().length() <= 0){
                    check = false;
                    emailIn.setError("Enter your email.");
                }
                if(pI.trim().length() <=0){
                    check = false;
                    passIn.setError("Enter your password.");
                }
                if(check){
                    if(dao.checkLogin(eI,pI)){
//                        rememberUser(eI,pI,remember.isChecked());
//                        editor.putString("Email",eI);
//                        editor.commit();
                        Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Log.d("ok","OK");
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("Email",eI);
                        edit.putString("Password",pI);
                        edit.commit();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame,new Fragment3())
                                .commit();

//                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }else{
                        Toast.makeText(getActivity(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        Log.d("ok","KO OK");
                    }
                }
            }
        });
        return view;
    }

    public void rememberUser(String e,String p, boolean s){
        SharedPreferences pref = getActivity().getSharedPreferences("User_File",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(s){
            editor.putString("Email",e);
            editor.putString("Password",p);
            editor.putBoolean("Remember",s);
        }else{
            editor.clear();
        }
        editor.commit();
    }

    }

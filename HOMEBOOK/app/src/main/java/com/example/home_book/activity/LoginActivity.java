package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText emailIn,passIn;
    CheckBox remember;
    TextView forget;
    Button signIn;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailIn = findViewById(R.id.emailIn);
        passIn = findViewById(R.id.passIn);
        signIn = findViewById(R.id.signIn);
        remember = findViewById(R.id.rememberBox);
        forget = findViewById(R.id.quenMatKhau);
        dao = new DAO(this);

        String eI = emailIn.getText().toString();
        String pI = passIn.getText().toString();

        SharedPreferences sP = getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
        Boolean rem = sP.getBoolean("Remember",false);

        emailIn.setText(email);
        passIn.setText(pass);
        remember.setChecked(rem);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sP.edit();
                Boolean check = true;
                if(eI.trim().length() <= 0){
                    check = false;
                }
                if(pI.trim().length() <=0){
                    check = false;
                }
                if(check){
//                    if(dao.checkLogin(eI,pI)){
//                        rememberUser(eI,pI,remember.isChecked());
//                        editor.putString("Email",eI);
//                        editor.commit();
//                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    }else{
//                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

    }

    public void rememberUser(String e,String p, boolean s){
        SharedPreferences pref = getSharedPreferences("User_File",MODE_PRIVATE);
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
    //Chuyển qua dùng fragment nhá lai
}
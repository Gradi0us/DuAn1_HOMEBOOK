package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginAdminActivity extends AppCompatActivity {

    TextInputEditText userAdmin, passAdmin;
    String user, pass;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        userAdmin = findViewById(R.id.adminUser);
        passAdmin = findViewById(R.id.adminPass);
        signIn = findViewById(R.id.adminSignIn);


        DAO dao = new DAO(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = true;
                user = userAdmin.getText().toString();
                pass = passAdmin.getText().toString();

                if (user.length() <= 0) {
                    check = false;
                    userAdmin.setError("Enter your email.");
                }
                if (pass.length() <= 0) {
                    check = false;
                    passAdmin.setError("Enter your email.");
                }
                if (check) {
                    if(dao.checkAdmin(user,pass)){
                        // tự thêm
                        Toast.makeText(LoginAdminActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginAdminActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
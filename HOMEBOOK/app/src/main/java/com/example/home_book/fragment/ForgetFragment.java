package com.example.home_book.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.user;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgetFragment extends Fragment {

    TextInputEditText getEmail,getNewPass,getNewPassAgain;
    TextInputLayout getNewPassLayout,getNewPassAgainLayout;
    Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
        view.findViewById(R.id.thoat4).setOnClickListener(vv -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame,new Fragment3())
                    .commit();
        });

        getEmail = view.findViewById(R.id.emailForget);
        getNewPass = view.findViewById(R.id.passNewForget);
        getNewPassLayout = view.findViewById(R.id.passNewForgetLayout);
        getNewPassAgain = view.findViewById(R.id.rePassForget);
        getNewPassAgainLayout = view.findViewById(R.id.rePassForgetLayout);
        save = view.findViewById(R.id.btn_saveForget);

        DAO dao = new DAO(getActivity());


//        Animation anime2 = new TranslateAnimation(Animation.ABSOLUTE,Animation.ABSOLUTE,-100,Animation.ABSOLUTE);
//        anime2.setDuration(1000);
//        anime2.setFillAfter(true);

        getNewPass.setVisibility(View.GONE);
        getNewPassAgain.setVisibility(View.GONE);
        getNewPassLayout.setVisibility(View.GONE);
        getNewPassAgainLayout.setVisibility(View.GONE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getEmail.getText().toString();
                String pass = getNewPass.getText().toString();
                String passAgain = getNewPassAgain.getText().toString();

                if(dao.checkEmail(email) && getEmail.isEnabled()){
                    Toast.makeText(getActivity(),"Đã tìm thấy",Toast.LENGTH_SHORT).show();
                    getEmail.setEnabled(false);
                    moveAnimation();
                    save.setText("Lưu");
                }else if(dao.checkEmail(email) && !getEmail.isEnabled()){
                    if(pass.trim().length() <= 0 || passAgain.trim().length() <= 0){
                        Toast.makeText(getActivity(), "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    }else if(!pass.equals(passAgain)){
                        Toast.makeText(getActivity(),"Mật khậu nhập lại khác mật khẩu trên",Toast.LENGTH_SHORT).show();
                    }else{
                        user user = dao.get1User("SELECT * FROM user_tb WHERE email=?",email);
                        user.setPassword(pass);
                        dao.UpdateUser(user);
                        Toast.makeText(getActivity(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame,new LoginFragment())
                                .commit();
                    }
                }else{
                    getEmail.setError("Tài khoản không tồn tại");
                    Toast.makeText(getActivity(),"Tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void moveAnimation(){
        Animation anime1 = new TranslateAnimation(-1000,Animation.ABSOLUTE,Animation.ABSOLUTE,Animation.ABSOLUTE);
        anime1.setDuration(1000);
        anime1.setFillAfter(true);
        getNewPass.setVisibility(View.VISIBLE);
        getNewPassAgain.setVisibility(View.VISIBLE);
        getNewPassLayout.setVisibility(View.VISIBLE);
        getNewPassAgainLayout.setVisibility(View.VISIBLE);
        getNewPass.startAnimation(anime1);
        getNewPassAgain.startAnimation(anime1);
        getNewPassLayout.startAnimation(anime1);
        getNewPassAgainLayout.startAnimation(anime1);
    }
}
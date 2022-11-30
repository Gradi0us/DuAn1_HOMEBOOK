package com.example.home_book.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.Language;
import com.example.home_book.menu.ChangeLanguage;
import com.example.home_book.menu.ChangePassActivity;
import com.example.home_book.menu.LienHeActivity;
import com.example.home_book.R;
import com.example.home_book.menu.Money;
import com.example.home_book.model.user;
import com.example.home_book.menu.TaiKhoan;
import com.example.home_book.menu.ThongTin;
import com.example.home_book.adapter.ListMenuAdapter;
import com.example.home_book.model.ListModelMenu;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

    ListView lv;
    String name;
    TextView textView;
    Toolbar toolbar;
    ImageView avatar;

    public Fragment3() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_3, container, false);

        ListView listView = v.findViewById(R.id.lvmenu);

        textView = v.findViewById(R.id.txtUsername1);
        avatar = v.findViewById(R.id.imgAva);

        v.findViewById(R.id.regis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, new RegisterFragment())
                        .commit();
            }

        });
        v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, new LoginFragment())
                        .commit();
            }
        });

        ArrayList<ListModelMenu> list = new ArrayList<>();
        SharedPreferences sP = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");
        DAO dao = new DAO(getContext());
        if (dao.checkLogin(email, pass)) {
            listView.setVisibility(View.VISIBLE);
            v.findViewById(R.id.regis).setVisibility(View.GONE);
            v.findViewById(R.id.login).setVisibility(View.GONE);
            user x = dao.get1User("select * from user_tb where email = ?", email);
            name = x.getFullname();
            textView.setText(name);
            avatar.setImageResource(x.getAvatar());

        } else {
            listView.setVisibility(View.GONE);
        }
        //add vao listview
        list.add(new ListModelMenu(R.drawable.setting_item, "Quản lý tài khoản"));
        list.add(new ListModelMenu(R.drawable.changepass_item, "Đổi mật khẩu"));
        list.add(new ListModelMenu(R.drawable.contact, "Liên hệ"));
        list.add(new ListModelMenu(R.drawable.in4, "Thông tin Ứng dụng"));
        list.add(new ListModelMenu(R.drawable.language, "Ngôn ngữ"));
        list.add(new ListModelMenu(R.drawable.add_money, "Thêm Tiền"));
        list.add(new ListModelMenu(R.drawable.exit, "Thoát ứng dụng"));

        ListMenuAdapter adapter = new ListMenuAdapter(getContext(), R.layout.item_menu, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    startActivity(new Intent(getContext(), TaiKhoan.class));
                }
                if (i == 1) {
                    startActivity(new Intent(getContext(), ChangePassActivity.class));
                }
                if (i == 2) {
                    startActivity(new Intent(getContext(), LienHeActivity.class));
                }
                if (i == 3) {
                    startActivity(new Intent(getContext(), ThongTin.class));
                }
                if (i == 4) {
                    startActivity(new Intent(getContext(), ChangeLanguage.class));
                }
                if (i == 5) {
                    startActivity(new Intent(getContext(), Money.class));
                }
                if (i == 6) {
                    System.exit(0);
                }


            }
        });


        return v;
    }
}
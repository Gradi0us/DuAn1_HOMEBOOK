package com.example.home_book.menu;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.LSDatAdapter;
import com.example.home_book.model.order;
import com.example.home_book.model.user;

import java.util.List;

public class ChuaFragment extends Fragment {

    DAO dao;
    List<order> list;
    ListView lv;
    public ChuaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chua, container, false);

        lv = v.findViewById(R.id.lvChua);
        dao = new DAO(getActivity());
        SharedPreferences sP = getActivity().getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
        if(!email.equals("")&&!pass.equals("")){
            if (dao.checkLogin(email, pass)) {
                user x = dao.get1User("select * from user_tb where email = ?", email);
                list = dao.getOrder("SELECT * FROM order_tb where user_id = "+x.getId()+" and status = 0");
                LSDatAdapter adapter = new LSDatAdapter(getActivity(), list);
                lv.setAdapter(adapter);
            }
        }else {
            Dialog();
        }
        return v;
    }
    private void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please login!");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
//            builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
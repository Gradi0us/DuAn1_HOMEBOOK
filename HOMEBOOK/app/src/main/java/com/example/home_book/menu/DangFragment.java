package com.example.home_book.menu;

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

import java.util.List;

public class DangFragment extends Fragment {

    DAO dao;
    List<order> list;
    ListView lv;

    public DangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dang, container, false);

        lv = v.findViewById(R.id.lvDang);
        dao = new DAO(getActivity());
        list = dao.getOrder("select * from order_tb where status = 1");
        LSDatAdapter adapter = new LSDatAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        return v;
    }
}
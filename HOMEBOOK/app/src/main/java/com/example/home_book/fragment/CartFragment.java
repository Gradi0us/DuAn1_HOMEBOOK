package com.example.home_book.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.order;

import java.util.List;


public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    DAO dao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = v.findViewById(R.id.ds_orders);
        dao = new DAO(getActivity());
        List<order> list = dao.getOrder("SELECT * FROM order_tb",null);

        return v;
    }
}
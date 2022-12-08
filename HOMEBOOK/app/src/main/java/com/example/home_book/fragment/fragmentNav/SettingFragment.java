package com.example.home_book.fragment.fragmentNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.BookingListAdapter;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.adapter.OrderAdapter;
import com.example.home_book.fragment.CartFragment;
import com.example.home_book.model.order;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class SettingFragment extends Fragment {
    RecyclerView recyclerView;
    TextInputEditText editText;
    RadioButton checkBox1,checkBox2,checkBox3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bookinglist_fragmentlayout, container, false);
        recyclerView = v.findViewById(R.id.ds_booking);
        editText = v.findViewById(R.id.adminUser);
        checkBox1 = v.findViewById(R.id.checkBox1);
        checkBox2 = v.findViewById(R.id.checkBox2);
        checkBox3 = v.findViewById(R.id.checkBox3);
        DAO dao = new DAO(getActivity());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkBox1.isChecked()){
                    ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb  where name ="+editable+" and status = 0");
                    loadData(list);
                }else if(checkBox2.isChecked()){
                    ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb where name ="+editable+" and status = 1");
                    loadData(list);
                }else if(checkBox3.isChecked()){
                    ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb where name ="+editable+" and status = 2");
                    loadData(list);
                }else {
                    ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb ");
                    loadData(list);
                }
            }
        });
        ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb ");
        loadData(list);
        return v;
    }

    void loadData(ArrayList<order> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        BookingListAdapter adapter = new BookingListAdapter(getContext(), (ArrayList<order>) list);
        recyclerView.setAdapter(adapter);
    }
}
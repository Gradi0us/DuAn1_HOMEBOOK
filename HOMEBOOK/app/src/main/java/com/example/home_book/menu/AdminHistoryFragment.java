package com.example.home_book.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.order;

import java.util.ArrayList;

public class AdminHistoryFragment extends Fragment {

    EditText txtBeginHistory,txtEndHistory;
    Button buttonLocHistory;
    Spinner historyAdminSpinner;
    ListView listMoneyHistory,listRateHistory;
    ArrayList<order> listOrder;
    ArrayList<> listRate;
    DAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_history, container, false);

        txtBeginHistory = view.findViewById(R.id.txtBeginHistory);
        txtEndHistory = view.findViewById(R.id.txtEndHistory);
        buttonLocHistory = view.findViewById(R.id.buttonLocHistory);
        historyAdminSpinner = view.findViewById(R.id.historyAdminSpinner);
        listMoneyHistory = view.findViewById(R.id.listMoneyHistory);
        listRateHistory = view.findViewById(R.id.listRateHistory);
        dao = new DAO(getActivity());


        return view;
    }


}
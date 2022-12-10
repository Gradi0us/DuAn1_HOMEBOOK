package com.example.home_book.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.NapThe;
import com.example.home_book.model.user;

import java.util.List;

public class Fragment2 extends Fragment {

    ListView listView;
    DAO dao;
    TextView txtTien;
    EditText txtThemTien;
    Button save,cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        listView = v.findViewById(R.id.listHistoryTien);
        txtTien = v.findViewById(R.id.currentMoneyAdmin);
        txtThemTien = v.findViewById(R.id.addMoneyAdmin);
        save = v.findViewById(R.id.saveMoneyAdmin);
        cancel = v.findViewById(R.id.cancelMoneyAdmin);

        dao = new DAO(getActivity());

        SharedPreferences sP = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        user x = dao.get1User("select * from user_tb where email = ?", email);
        txtTien.setText(x.getMoney()+"");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienAdd = txtThemTien.getText().toString().trim();

                if(tienAdd.trim().length() <= 0 || tienAdd.isEmpty()){
                    Toast.makeText(getActivity(),"Vui lòng nhập tiền cần thêm.",Toast.LENGTH_SHORT).show();
                } else if(Float.parseFloat(tienAdd) <= 0){
                    Toast.makeText(getActivity(),"Tiền không được bé hơn hoặc bằng 0.",Toast.LENGTH_SHORT).show();
                }else{
                    int tien = (int)Float.parseFloat(tienAdd);
                    NapThe y = new NapThe(x.getId(),tien,0);
                    dao.AddNapThe(y);
                    Toast.makeText(getActivity(),"Đã gửi đơn.",Toast.LENGTH_SHORT).show();
                    LoadData(x);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThemTien.setText("");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame,new Fragment3())
                        .commit();
            }
        });

        LoadData(x);

        return v;
    }

    private void LoadData(user user){
        List<NapThe> list = dao.getNapThe("select * from napthe_tb where user_id = ?",user.getId()+"");
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            class ViewHolder {
                TextView money, status;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_napthe, parent, false);
                    holder = new ViewHolder();
                    holder.money = convertView.findViewById(R.id.soTienHistory);
                    holder.status = convertView.findViewById(R.id.statusHistory);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                NapThe the = list.get(position);
                holder.money.setText(the.getMoney()+"");
                holder.status.setText("Đang xác nhận");
                holder.status.setTextColor(Color.RED);
                if(the.getStatus() == 1){
                    holder.status.setText("Nạp thành công");
                    holder.status.setTextColor(Color.GREEN);
                }

                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }
}
package com.example.home_book.menu;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.fragment.Fragment3;
import com.example.home_book.model.user;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentTaiKhoan extends Fragment {
    TextView name, changeName;
    EditText edtchangedName;
    Button btnsaveName;
    ImageView changeImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        name = view.findViewById(R.id.txtName);
        edtchangedName = view.findViewById(R.id.edtName);
        changeName = view.findViewById(R.id.changeName);
        btnsaveName = view.findViewById(R.id.saveChanged);
        changeImage = view.findViewById(R.id.changeImage);

        view.findViewById(R.id.thoat3).setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame,new Fragment3())
                    .commit();
        });

        edtchangedName.setVisibility(View.GONE);
        btnsaveName.setVisibility(View.GONE);


        SharedPreferences sP = getActivity().getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        DAO dao = new DAO(getContext());
        user x = dao.get1User("select * from user_tb where email = ?",email);
        String ten =  x.getFullname();
        name.setText(ten);
        changeImage.setImageResource(x.getAvatar());

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtchangedName.setVisibility(View.VISIBLE);
                btnsaveName.setVisibility(View.VISIBLE);
                name.setVisibility(View.GONE);
                changeName.setVisibility(View.GONE);

                btnsaveName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nameAC = edtchangedName.getText().toString();
                        edtchangedName.setVisibility(View.GONE);
                        btnsaveName.setVisibility(View.GONE);
                        name.setVisibility(View.VISIBLE);
                        changeName.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), "Đổi thành công", Toast.LENGTH_SHORT).show();

                        name.setText(nameAC);
                        x.setFullname(nameAC);
                        dao.UpdateUser(x);

                    }
                });
            }
        });

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridView androidGridView;

                ArrayList<Integer> listImg = new ArrayList<>();
                listImg.addAll(Arrays.asList(
                        R.drawable.bliz_cat,
                        R.drawable.vayne_cat,
                        R.drawable.m4_cat,
                        R.drawable.ys_cat,
                        R.drawable.doraemon,
                        R.drawable.neon_tired,
                        R.drawable.fade_tired,
                        R.drawable.phoenix_tired,
                        R.drawable.sova_tired
                ));


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view5 = inflater.inflate(R.layout.avatar_selection, null);
                builder.setView(view5);

                androidGridView = view5.findViewById(R.id.gridview_android_example);

                BaseAdapter ImageAdapterGridView = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return listImg.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return listImg.get(position);
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ava_item, parent, false);
                        ImageView anhAva = convertView.findViewById(R.id.imgAnh);
                        anhAva.setBackgroundResource(listImg.get(position));
                        return convertView;
                    }
                };

                androidGridView.setAdapter(ImageAdapterGridView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("==po==", ""+position);
                        changeImage.setImageResource(listImg.get(position));
                        x.setAvatar(listImg.get(position));
                        dao.UpdateUser(x);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
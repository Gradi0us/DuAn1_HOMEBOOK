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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.fragment.Fragment3;
import com.example.home_book.model.user;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentTaiKhoan extends Fragment {

    TextView name, changeName, role, changeRole;
    EditText edtchangedName;
    Button btnsaveName, btnsaveRole;
    
 /// của dương trên,main dưới

    TextView  sdt, changeSdt;
    EditText  edtChangeSdt;
    Button  btnsaveSdt;

    ImageView changeImage;
    Spinner spnrole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        name = view.findViewById(R.id.txtName);
        edtchangedName = view.findViewById(R.id.edtName);

        sdt = view.findViewById(R.id.txtSdt);
        edtChangeSdt= view.findViewById(R.id.edtSdt);

        role = view.findViewById(R.id.txtRole);
        changeRole = view.findViewById(R.id.changeRole);
        changeName = view.findViewById(R.id.changeName);
        changeSdt= view.findViewById(R.id.changeSdt);
        btnsaveName = view.findViewById(R.id.saveChanged);

        btnsaveSdt = view.findViewById(R.id.saveChangedSdt);
        btnsaveRole = view.findViewById(R.id.saveChangedRole);
        changeImage = view.findViewById(R.id.changeImage);
        spnrole = view.findViewById(R.id.edtRole);


        ArrayList<String> category = new ArrayList<>();
        category.add("Collaborate");

        category.add("Member");

        ArrayAdapter adapterCategory = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, category);
        spnrole.setAdapter(adapterCategory);


        view.findViewById(R.id.thoat3).setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame,new Fragment3())
                    .commit();
        });

        edtchangedName.setVisibility(View.GONE);
        btnsaveName.setVisibility(View.GONE);


        edtChangeSdt.setVisibility(View.GONE);
        btnsaveSdt.setVisibility(View.GONE);

        spnrole.setVisibility(View.GONE);
        btnsaveRole.setVisibility(View.GONE);


        SharedPreferences sP = getActivity().getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        DAO dao = new DAO(getContext());
        user x = dao.get1User("select * from user_tb where email = ?",email);
        String ten =  x.getFullname();

        String sdtt = x.getPhone();
        int role1 = x.getRole();
        if (role1 == 0){
            role.setText("Collaborate");
        }else if(role1 == 1) {
            role.setText("Member");
        }

        sdt.setText(sdtt);
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
                        if(nameAC.length()!=0){

                            edtchangedName.setVisibility(View.GONE);
                            btnsaveName.setVisibility(View.GONE);
                            name.setVisibility(View.VISIBLE);
                            changeName.setVisibility(View.VISIBLE);
                            x.setFullname(nameAC);
                            name.setText(nameAC);

                            dao.UpdateUser(x);
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        }else {

                        }
                    }
                });
            }
        });
        changeSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtChangeSdt.setVisibility(View.VISIBLE);
                btnsaveSdt.setVisibility(View.VISIBLE);
                sdt.setVisibility(View.GONE);
                changeSdt.setVisibility(View.GONE);

                btnsaveSdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sdtAC = edtChangeSdt.getText().toString();
                        if(sdtAC.length()!=0){
                            edtChangeSdt.setVisibility(View.GONE);
                            btnsaveSdt.setVisibility(View.GONE);
                            sdt.setVisibility(View.VISIBLE);
                            changeSdt.setVisibility(View.VISIBLE);
                            x.setPhone(sdtAC);
                            sdt.setText(sdtAC);
                            dao.UpdateUser(x);
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        changeRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spnrole.setVisibility(View.VISIBLE);
                btnsaveRole.setVisibility(View.VISIBLE);
                role.setVisibility(View.GONE);
                changeRole.setVisibility(View.GONE);

                btnsaveRole.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        spnrole.setVisibility(View.GONE);
                        btnsaveRole.setVisibility(View.GONE);
                        role.setVisibility(View.VISIBLE);
                        changeRole.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        String index = (String) spnrole.getSelectedItem();
                        if (index.equals("Collaborate")){
                            x.setRole(0);
                            role.setText("Collaborate");
                            dao.UpdateUser(x);
//
                            Toast.makeText(getActivity(), "hehe", Toast.LENGTH_SHORT).show();
                        }else if (index.equals("User")){
                            x.setRole(1);
                            role.setText("User");
                            dao.UpdateUser(x);
                            Toast.makeText(getActivity(), "hihi", Toast.LENGTH_SHORT).show();

                        }else if (index.equals("Member")){
                            x.setRole(1);
                            role.setText("Member");
                            dao.UpdateUser(x);
                        }
//

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
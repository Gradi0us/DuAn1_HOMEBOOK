package com.example.home_book.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.fragment.fragmentNav.AcountFragment;
import com.example.home_book.fragment.fragmentNav.HomeFragment;
import com.example.home_book.fragment.fragmentNav.RateFragment;
import com.example.home_book.fragment.fragmentNav.SettingFragment;
import com.example.home_book.model.ListModelMenu;
import com.example.home_book.model.Room;
import com.example.home_book.model.categories;
import com.example.home_book.model.rooms;
import com.example.home_book.model.user;
import com.google.android.material.navigation.NavigationView;
import com.example.home_book.model.rooms;
import com.example.home_book.model.roomImage;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FindFragment extends Fragment {
    categories category;
    ToggleButton toggle_hotel, toggle_homestays,toggle_apartment ;
    static final float END_SCALE = 0.7f;
    ImageView menuIcon;
    LinearLayout contentView, linear, polay;
    RelativeLayout visual1, visual2, visual3, visual4;
    Boolean checkLogin;
    ArrayList<Room> list;
    TextView txtUsername;
    Boolean toggle_hotelischeck = true;

    //Drawer Menu

    RecyclerView recyclerView, recyclerView1;
    DAO dao;
    EditText edtSearch;
    Toolbar toolbar;
    String sqlRoom = "select * from room_tb";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        recyclerView = view.findViewById(R.id.ds_homebook);
        recyclerView1 = view.findViewById(R.id.ds_favo_homebook);
        toggle_hotel = view.findViewById(R.id.toggle_hotel);
        toggle_apartment = view.findViewById(R.id.toggle_apartment);
        toggle_homestays = view.findViewById(R.id.toggle_homestays);


        edtSearch = view.findViewById(R.id.edt_search);
        linear = view.findViewById(R.id.linear);
        polay = view.findViewById(R.id.poLay);
//        toolbar = view.findViewById(R.id.toolbar);

        dao = new DAO(getContext());
        ArrayList<Room> list2 = (ArrayList<Room>) dao.getRoom(sqlRoom,null);
//        ArrayList<Room> list2 = (ArrayList<Room>) dao.getRoom2();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String timkiem = edtSearch.getText().toString().trim();
                String sql = " SELECT * FROM room_tb where location like '%" + timkiem + "%'";
                if (!timkiem.isEmpty()) {
                    ArrayList<Room> list1 = (ArrayList<Room>) dao.getRoom(sql,null);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list1, getActivity());
                    recyclerView.setAdapter(homeBookApdater);
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list2, getActivity());
                    recyclerView.setAdapter(homeBookApdater);
                }
            }
        });

        loadDaTa();
        FilterHome();

        return view;
    }

    //Navigation Drawer Functions
//    private void naviagtionDrawer() {
//
//        //Naviagtion Drawer
//        navigationView.bringToFront();
////        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        navigationView.setCheckedItem(R.id.Find);
//
//        menuIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (drawerLayout.isDrawerVisible(GravityCompat.START))
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                else drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//
//        animateNavigationDrawer();
//
//    }


//    private void animateNavigationDrawer() {
//
//
//        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//
//                // Scale the View based on current slide offset
//                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
//                final float offsetScale = 1 - diffScaledOffset;
//                contentView.setScaleX(offsetScale);
//                contentView.setScaleY(offsetScale);
//                // Translate the View, accounting for the scaled width
//                final float xOffset = drawerView.getWidth() * slideOffset;
//                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
//                final float xTranslation = xOffset - xOffsetDiff;
//                contentView.setTranslationX(xTranslation);
//            }
//        });
//
//    }
//      navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.mHome:
//                        Fragment fragment = new HomeFragment();
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.frame, fragment).commit();
//                        drawerLayout.close();
//                        break;
//                    case R.id.mAcount:
//                        Fragment fragment1 = new AcountFragment();
//                        FragmentManager fragmentManager1 =getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//                        fragmentTransaction1.replace(R.id.frame, fragment1).commit();
//                        drawerLayout.close();
//                        break;
//
//                    case R.id.mRate:
//                        Fragment fragment2 = new RateFragment();
//                        FragmentManager fragmentManager2 = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
//                        fragmentTransaction2.replace(R.id.frame, fragment2).commit();
//                        drawerLayout.close();
//                        break;
//                    case R.id.mSetting:
//                        Fragment fragment3 = new SettingFragment();
//                        FragmentManager fragmentManager3 = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
//                        fragmentTransaction3.replace(R.id.frame, fragment3).commit();
//                        drawerLayout.close();
//                        break;
////                    case R.id.back:
//////                        Intent intent = new Intent(MainActivity.this, SensorService.class);
//////                        startService(intent);
//////                        finish();
////                        break;
//                }
//                return false;
//            }
//        });


    //    public void gethomebook(){
//        ArrayList<rooms> list = (ArrayList<rooms>) dao.getRoom1();
//        ArrayList<roomImage> list1 = (ArrayList<roomImage>) dao.getAllHinhAnh();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(),list,list1);
//        recyclerView.setAdapter(homeBookApdater);
//    }
    public void loadDaTa() {
        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom(sqlRoom,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list, getActivity());
        recyclerView.setAdapter(homeBookApdater);
        recyclerView1.setAdapter(homeBookApdater);



    }
    private void FilterHome(){
        toggle_hotel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("TAG", "onCreateView: " + toggle_hotel);
                boolean to = true;
                if (toggle_hotel.isChecked() == true) {
                    Toast.makeText(getContext(), "hotel", Toast.LENGTH_SHORT).show();
                    toggle_apartment.setChecked(false);
                    toggle_homestays.setChecked(false);
                }
        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom(sqlRoom,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list, getActivity());
        recyclerView.setAdapter(homeBookApdater);
        recyclerView1.setAdapter(homeBookApdater);

//                }



//                if(toggle_apartment.isChecked() == true){
//                    Toast.makeText(getContext(), "apartment", Toast.LENGTH_SHORT).show();
//                    toggle_hotel.setChecked(false);
//                    toggle_homestays.setChecked(false);
//                }
//                if(toggle_homestays.isChecked() == true){
//                    Toast.makeText(getContext(), "homestays", Toast.LENGTH_SHORT).show();
//                    toggle_hotel.setChecked(false);
//                    toggle_apartment.setChecked(false);
//                }


            }
        });

        toggle_apartment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("TAG", "onCreateView: " + toggle_hotel);
                boolean to = true;
                if(toggle_apartment.isChecked() == true){
                    Toast.makeText(getContext(), "apartment", Toast.LENGTH_SHORT).show();
                    toggle_hotel.setChecked(false);
                    toggle_homestays.setChecked(false);
                }

                    ArrayList<Room> list = (ArrayList<Room>) dao.getRoom(sqlRoom,null);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView1.setLayoutManager(linearLayoutManager1);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list, getActivity());
                    recyclerView.setAdapter(homeBookApdater);
                    recyclerView1.setAdapter(homeBookApdater);
//                if(toggle_homestays.isChecked() == true){
//                    Toast.makeText(getContext(), "homestays", Toast.LENGTH_SHORT).show();
//                    toggle_hotel.setChecked(false);
//                    toggle_apartment.setChecked(false);
//                }


            }
        });
        toggle_homestays.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("TAG", "onCreateView: " + toggle_hotel);
                boolean to = true;
                if(toggle_homestays.isChecked() == true){
                    Toast.makeText(getContext(), "homestays", Toast.LENGTH_SHORT).show();
                    toggle_hotel.setChecked(false);
                    toggle_apartment.setChecked(false);
                }
                    ArrayList<Room> list = (ArrayList<Room>) dao.getRoom(sqlRoom,null);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView1.setLayoutManager(linearLayoutManager1);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), list, getActivity());
                    recyclerView.setAdapter(homeBookApdater);
                    recyclerView1.setAdapter(homeBookApdater);


            }
        });
    }
}

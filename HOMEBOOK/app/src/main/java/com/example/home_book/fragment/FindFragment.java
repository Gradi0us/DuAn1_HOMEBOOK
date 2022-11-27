package com.example.home_book.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.fragment.fragmentNav.AcountFragment;
import com.example.home_book.fragment.fragmentNav.HomeFragment;
import com.example.home_book.fragment.fragmentNav.RateFragment;
import com.example.home_book.fragment.fragmentNav.SettingFragment;
import com.example.home_book.model.rooms;
import com.google.android.material.navigation.NavigationView;
import com.example.home_book.model.rooms;
import com.example.home_book.model.roomImage;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FindFragment extends Fragment {

    static final float END_SCALE = 0.7f;
    ImageView menuIcon;
    LinearLayout contentView;
    RelativeLayout visual1, visual2, visual3, visual4;
    Boolean checkLogin;
    TextView txtUsername;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView;
    DAO dao;
    EditText edtSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        recyclerView = view.findViewById(R.id.ds_homebook);
        menuIcon = view.findViewById(R.id.menu_icon);
        contentView = view.findViewById(R.id.content);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.navigation_view);
        edtSearch = view.findViewById(R.id.edt_search);
        dao = new DAO(getContext());
        ArrayList<rooms> list2 = (ArrayList<rooms>) dao.getRoom();
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
                if (!timkiem.isEmpty()) {
                    ArrayList<rooms> list1 = dao.Search(timkiem);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(),list1);
                    recyclerView.setAdapter(homeBookApdater);
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(),list2);
                    recyclerView.setAdapter(homeBookApdater);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome:
                        Fragment fragment = new HomeFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment).commit();
                        drawerLayout.close();
                        break;
                    case R.id.mAcount:
                        Fragment fragment1 = new AcountFragment();
                        FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, fragment1).commit();
                        drawerLayout.close();
                        break;

                    case R.id.mRate:
                        Fragment fragment2 = new RateFragment();
                        FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2).commit();
                        drawerLayout.close();
                        break;
                    case R.id.mSetting:
                        Fragment fragment3 = new SettingFragment();
                        FragmentManager fragmentManager3 = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3).commit();
                        drawerLayout.close();
                        break;
//                    case R.id.back:
////                        Intent intent = new Intent(MainActivity.this, SensorService.class);
////                        startService(intent);
////                        finish();
//                        break;
                }
                return false;
            }
        });
        naviagtionDrawer();
        loadDaTa();
        return view;
    }

    //Navigation Drawer Functions
    private void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setCheckedItem(R.id.Find);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {


        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }
    
    //    public void gethomebook(){
//        ArrayList<rooms> list = (ArrayList<rooms>) dao.getRoom1();
//        ArrayList<roomImage> list1 = (ArrayList<roomImage>) dao.getAllHinhAnh();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(),list,list1);
//        recyclerView.setAdapter(homeBookApdater);
//    }
    public void loadDaTa(){
        ArrayList<rooms> list = (ArrayList<rooms>) dao.getRoom();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(),list);
        recyclerView.setAdapter(homeBookApdater);
    }
        }

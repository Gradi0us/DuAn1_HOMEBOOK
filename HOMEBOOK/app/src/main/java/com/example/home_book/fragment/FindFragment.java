package com.example.home_book.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_book.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FindFragment extends Fragment {

//    TextInputEditText ngayNhan,ngayTra;
//    int dD,mM,yY;
//    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
static final float END_SCALE = 0.7f;

    ImageView menuIcon;
    LinearLayout contentView;
    RelativeLayout visual1,visual2,visual3,visual4;
    Boolean checkLogin;
    TextView txtUsername;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        menuIcon = view.findViewById(R.id.menu_icon);
        contentView = view.findViewById(R.id.content);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.navigation_view);

        naviagtionDrawer();
//        ngayNhan = view.findViewById(R.id.ngayNhanUp);
//        ngayTra = view.findViewById(R.id.ngayTraUp);
//
//        ngayNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                yY = calendar.get(Calendar.YEAR);
//                mM = calendar.get(Calendar.MONTH);
//                dD = calendar.get(Calendar.DATE);
//
//                DatePickerDialog d = new DatePickerDialog(getActivity(),
//                        0,dateNhan,yY,mM,dD);
//                d.show();
//            }
//        });
//
//        ngayTra.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                yY = calendar.get(Calendar.YEAR);
//                mM = calendar.get(Calendar.MONTH);
//                dD = calendar.get(Calendar.DATE);
//
//                DatePickerDialog d = new DatePickerDialog(getActivity(),
//                        0,dateTra,yY,mM,dD);
//                d.show();
//            }
//        });
//
//        return view;
//    }
//
//    private void MoveAnimation(){
//        Animation anime = new TranslateAnimation(-1000,Animation.ABSOLUTE,Animation.ABSOLUTE,Animation.ABSOLUTE);
//        anime.setDuration(1000);
//        anime.setFillAfter(true);
//    }
//
//    DatePickerDialog.OnDateSetListener dateNhan = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            yY = year; mM = month; dD = dayOfMonth;
//            GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
//            ngayNhan.setText(format.format(gC.getTime()));
//        }
//    };
//
//    DatePickerDialog.OnDateSetListener dateTra = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            yY = year; mM = month; dD = dayOfMonth;
//            GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
//            ngayTra.setText(format.format(gC.getTime()));
//        }
//    };

//}
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




        }
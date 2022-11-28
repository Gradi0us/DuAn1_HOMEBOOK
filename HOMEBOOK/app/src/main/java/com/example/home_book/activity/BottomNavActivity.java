package com.example.home_book.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.fragment.CartFragment;
import com.example.home_book.fragment.FindFragment;
import com.example.home_book.fragment.Fragment2;
import com.example.home_book.fragment.Fragment3;
import com.example.home_book.fragment.fragmentNav.AcountFragment;
import com.example.home_book.fragment.fragmentNav.HomeFragment;
import com.example.home_book.fragment.fragmentNav.RateFragment;
import com.example.home_book.fragment.fragmentNav.SettingFragment;
import com.example.home_book.model.user;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class BottomNavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
Toolbar toolbar;
    ImageView menuIcon;
    LinearLayout linear,polay;
    FrameLayout contentView;
    RelativeLayout layout;
    TextView txtUsername;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String name;

    static final float END_SCALE = 0.7f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bottom_nav);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.frame);
        layout = findViewById(R.id.information);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        txtUsername = findViewById(R.id.txtUsername);

        SharedPreferences sP = getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
//        hiểu r =))
//        cái dữ liệu ở trên ý là nó set lúc vào onCreate và onCreate nó chạy 1 lần khởi tạo xong hết r nó k chạy lại nữa trừ khi cái activity này bị destroy
//            bây giờ là làm thế nào để nó load lại activity
//        load lại thì nó mới chạy vô cái thg lấy dữ liệu kia đc
//        mấy cái này k thấy khả quan cho lắm
//        nma cứ thử vậy
        DAO dao = new DAO(BottomNavActivity.this);
        if (dao.checkLogin(email,pass)){
            List<user> users = dao.getUser_name(email,pass);
            for(user x : users ){
                if(x.getEmail().equals( email) ){
                    name = x.getFullname();
                    txtUsername.setText(name);

                }
            }

        }

        final Fragment fragment1 = new FindFragment();
        final Fragment fragment2 = new Fragment2();
        final Fragment fragment3 = new Fragment3();
        final Fragment fragment4 = new CartFragment();
        naviagtionDrawer();
        sethorizontal();
        replaceFragment(new FindFragment());

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Find:
                        layout.setVisibility(View.VISIBLE);
                        replaceFragment(fragment1);
                        Intent refresh = new Intent(BottomNavActivity.this, BottomNavActivity.class);
                        startActivity(refresh);
                        overridePendingTransition(0, 0);
//                        cái này là cái gì đây ???
                        finish(); //
//                        vậy thì hay
//                        trông nó hơi ngu ngu xíu nma ra đcc kết quả =))
                        break;
                    case R.id.Cart:
                        layout.setVisibility(View.VISIBLE);
                        replaceFragment(fragment4);

                        break;

                    case R.id.Music:
                        layout.setVisibility(View.VISIBLE);
                        replaceFragment(fragment2);
                        break;

                    case R.id.Account:
                        layout.setVisibility(View.GONE);
                        replaceFragment(fragment3);
                        break;

                    default:
                        layout.setVisibility(View.VISIBLE);
                        replaceFragment(fragment1);
                        break;
                }
                return true;
            }
        });
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

    }
    private void sethorizontal(){
        SharedPreferences sP = getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
        DAO dao = new DAO(BottomNavActivity.this);
        if (dao.checkLogin(email,pass)){
            List<user> users = dao.getUser_name(email,pass);
            for(user x : users ){
                if(x.getEmail().equals( email) ){
                    name = x.getFullname();
                    txtUsername.setText(name);

                }
            }

        }
    }
    private void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mHome:
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                drawerLayout.close();
                break;
            case R.id.mAcount:
                Fragment fragment1 = new AcountFragment();
                FragmentManager fragmentManager1 =getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.frame, fragment1).commit();
                drawerLayout.close();
                break;

            case R.id.mRate:
                Fragment fragment2 = new RateFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.frame, fragment2).commit();
                drawerLayout.close();
                break;
            case R.id.mSetting:
                Fragment fragment3 = new SettingFragment();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
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

    @Override
    protected void onResume() {
        super.onResume();
//        Sao nó k nhảy vào đây ???

        SharedPreferences sP = getSharedPreferences("User_File",MODE_PRIVATE);
        String email = sP.getString("Email","");
        String pass = sP.getString("Password","");
    }
}
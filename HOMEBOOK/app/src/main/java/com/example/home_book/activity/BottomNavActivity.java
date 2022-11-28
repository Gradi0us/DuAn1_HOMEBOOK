package com.example.home_book.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_book.R;
import com.example.home_book.fragment.CartFragment;
import com.example.home_book.fragment.FindFragment;
import com.example.home_book.fragment.Fragment2;
import com.example.home_book.fragment.Fragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class BottomNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bottom_nav);



        final Fragment fragment1 = new FindFragment();
        final Fragment fragment2 = new Fragment2();
        final Fragment fragment3 = new Fragment3();
        final Fragment fragment4 = new CartFragment();

        replaceFragment(new FindFragment());

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Find:
                        replaceFragment(fragment1);

                        break;
                    case R.id.Cart:
                        replaceFragment(fragment4);

                        break;

                    case R.id.Music:
                        replaceFragment(fragment2);
                        break;

                    case R.id.Account:
                        replaceFragment(fragment3);
                        break;

                    default:
                        replaceFragment(fragment1);
                        break;
                }
                return true;
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
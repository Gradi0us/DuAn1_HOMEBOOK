package com.example.home_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.home_book.fragment.Fragment1;
import com.example.home_book.fragment.Fragment2;
import com.example.home_book.fragment.Fragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        final Fragment fragment1 = new FindFragment();
        final Fragment fragment2 = new Fragment2();
        final Fragment fragment3 = new Fragment3();

        replaceFragment(new FindFragment());

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Find:
                        replaceFragment(fragment1);

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
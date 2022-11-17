package com.example.home_book.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_book.R;
import com.example.home_book.activity.BottomNavActivity;
import com.example.home_book.slideshow.The_Slide_Items_Model_Class;
import com.example.home_book.slideshow.adapter.The_Slide_Items_Pager_Adapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class RoomDetail extends Fragment {
    private List<The_Slide_Items_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_room_detail, container, false);
        page = v.findViewById(R.id.my_pager) ;
        tabLayout = v.findViewById(R.id.my_tablayout);
        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan1,"Slider 1 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan2,"Slider 2 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.khachsan3,"Slider 3 Title"));

        The_Slide_Items_Pager_Adapter itemsPager_adapter = new The_Slide_Items_Pager_Adapter(getContext(),listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);
        return v;
    }
    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {

           getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }

    }

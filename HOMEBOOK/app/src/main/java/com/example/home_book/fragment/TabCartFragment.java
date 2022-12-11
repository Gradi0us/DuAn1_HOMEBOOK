package com.example.home_book.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_book.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class TabCartFragment extends Fragment {
    private TabLayout tabLayout1;
    private ViewPager2 viewPager1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tab_cart, container, false);
        tabLayout1 = v.findViewById(R.id.tab_chi);
        viewPager1 = v.findViewById(R.id.view_page1);
//        tabLayout1.setupWithViewPager(viewPager1);
//        AdapterFragment adapterFragment1 = new AdapterFragment(getActivity().getSupportFragmentManager());
//        viewPager1.setAdapter(adapterFragment1);
        CustomFragmentAdapter adapter = new CustomFragmentAdapter(getActivity());
        viewPager1.setAdapter(adapter);
        new TabLayoutMediator(tabLayout1, viewPager1, new TabLayoutMediator.TabConfigurationStrategy( ) {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0 :{
                        tab.setText("Order");
                        break;
                    }
                    case 1 :{
                        tab.setText( "History");
                        break;
                    }
                }
            }
        }).attach();
        return v;
    }
    public class CustomFragmentAdapter extends FragmentStateAdapter {
        public CustomFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:{
                    return new CartFragment();
                }
                case 1:{
                    return new HistoryFragment();
                }
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
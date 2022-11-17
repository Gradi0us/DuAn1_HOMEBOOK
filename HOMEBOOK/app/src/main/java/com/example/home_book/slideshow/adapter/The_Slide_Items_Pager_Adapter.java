package com.example.home_book.slideshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.home_book.R;
import com.example.home_book.slideshow.The_Slide_Items_Model_Class;

import java.util.List;

public class The_Slide_Items_Pager_Adapter extends PagerAdapter {

    private Context Mcontext;
    private List<The_Slide_Items_Model_Class> theSlideItemsModelClassList;




    public The_Slide_Items_Pager_Adapter(Context Mcontext, List<The_Slide_Items_Model_Class> theSlideItemsModelClassList) {
        this.Mcontext = Mcontext;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.the_items_layout,null);

        ImageView featured_image = sliderLayout.findViewById(R.id.my_featured_image);


        featured_image.setImageResource(theSlideItemsModelClassList.get(position).getFeatured_image());

        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return theSlideItemsModelClassList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

}

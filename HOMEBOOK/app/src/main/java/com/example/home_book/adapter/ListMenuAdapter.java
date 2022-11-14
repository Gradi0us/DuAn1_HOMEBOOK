package com.example.home_book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.home_book.R;
import com.example.home_book.fragment.Fragment3;
import com.example.home_book.model.ListModelMenu;

import java.util.ArrayList;

public class ListMenuAdapter extends BaseAdapter {

    private ArrayList<ListModelMenu> list;
    private Context context;
    int layout;
    TextView tvMenu;
    ImageView imgMenu;

    public ListMenuAdapter(Context context, int layout, ArrayList<ListModelMenu> list){
        this.context = context;
        this.layout = layout;
        this.list = list;

    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView tvMenu;
        ImageView imgMenu;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_menu, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.imgMenu = convertView.findViewById(R.id.imgMenu);
            viewHolder.tvMenu = convertView.findViewById(R.id.tvMenu);



            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        lastPosition = position;
        viewHolder.imgMenu.setImageResource(list.get(position).getImgMenu());
        viewHolder.tvMenu.setText(list.get(position).getTvMenu());

        return convertView;
    }
}

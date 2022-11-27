package com.example.home_book.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.R;
import com.example.home_book.database.AppSQL;
import com.example.home_book.model.Room;
import com.example.home_book.model.rooms;
import com.example.home_book.model.roomImage;
import java.util.ArrayList;

public class HomeBookApdater extends RecyclerView.Adapter<HomeBookApdater.ViewHolder>{
    Context context;
    ArrayList<Room> listRoom;

    public HomeBookApdater(Context context, ArrayList<Room> listRoom) {
        this.context = context;
        this.listRoom = listRoom;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nameHomebook.setText(listRoom.get(position).getName());
        holder.tv_locationHomebook.setText(listRoom.get(position).getLocation());
        byte[] hinhanh = listRoom.get(0).getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
        holder.img_homebook.setImageBitmap(bitmap);
        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickGoToDeTail(listRoom.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nameHomebook,tv_locationHomebook;
        private ImageView img_homebook,img_favoriteHomebook;
        LinearLayout layoutitem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameHomebook = itemView.findViewById(R.id.tv_name_homebook);
            tv_locationHomebook = itemView.findViewById(R.id.tv_location_homebook);
            img_homebook = itemView.findViewById(R.id.img_homebook);
            img_favoriteHomebook = itemView.findViewById(R.id.img_favorite_homebook);
            layoutitem = itemView.findViewById(R.id.layout_click);
        }
    }
//    private void onClickGoToDeTail(Room room){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_view,new NewspaperFragment())
//                .commit();
//        Bundle bundle = new Bundle();
//        bundle.putString("name",thuThu.getHoten());
//        intent.putExtra("bundle",bundle);
//
//    }
}

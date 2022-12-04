package com.example.home_book.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.R;
import com.example.home_book.activity.OrderAcivity;
import com.example.home_book.database.AppSQL;
import com.example.home_book.model.Room;
import com.example.home_book.model.rooms;
import com.example.home_book.model.roomImage;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;

public class HomeBookApdater extends RecyclerView.Adapter<HomeBookApdater.ViewHolder> {
    Context context;
    ArrayList<Room> listRoom;
    Activity activity;

    public HomeBookApdater(Context context, ArrayList<Room> listRoom, Activity activity) {
        this.context = context;
        this.listRoom = listRoom;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_list, parent, false);
        return new ViewHolder(v);
    }
//tung dau r
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nameHomebook.setText(listRoom.get(position).getName());
        String name = listRoom.get(position).getName();
        holder.tv_locationHomebook.setText(listRoom.get(position).getLocation());
        byte[] hinhanh = listRoom.get(position).getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);

        holder.img_homebook.setImageBitmap(bitmap);

        switch (listRoom.get(position).getBeds()){
            case 0:holder.tvBeds.setText("Phòng đơn");break;
            case 1:holder.tvBeds.setText("Phòng sinh đôi");break;
            case 2:holder.tvBeds.setText("Phòng đôi");break;
            case 3:holder.tvBeds.setText("Phòng ba");break;
            case 4:holder.tvBeds.setText("Phòng bốn");break;
        }

        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDeTail(listRoom.get(holder.getAdapterPosition()), activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nameHomebook, tv_locationHomebook,tvBeds,tvPeople;
        private ImageView img_homebook;
        private ToggleButton img_favoriteHomebook;
        FrameLayout layoutitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameHomebook = itemView.findViewById(R.id.tv_name_homebook);
            tv_locationHomebook = itemView.findViewById(R.id.tv_location_homebook);
            img_homebook = itemView.findViewById(R.id.img_homebook);
//            img_favoriteHomebook = itemView.findViewById(R.id.img_favorite_homebook);
            layoutitem = itemView.findViewById(R.id.layout_click);
            tvBeds=itemView.findViewById(R.id.tv_beds);
//            tvPeople=itemView.findViewById(R.id.tv_people);
        }
    }

    private void onClickGoToDeTail(Room room, Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_room_detail, null);
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        Intent intent = new Intent(activity, OrderAcivity.class);
        Bundle bundle = new Bundle();
//        bundle.putString("name", room.getName());
//        bundle.putString("location", room.getLocation());
//        bundle.putString("category", room.getCategory());
//        bundle.putInt("status", room.getStatus());
//        bundle.putString("note", room.getNote());
//        bundle.putInt("beds", room.getBeds());
//        bundle.putInt("cost", room.getCost());
        bundle.putInt("id", room.getId());
//        bundle.putInt("rate", room.getRate());
//        bundle.putByteArray("img", room.getIMG());
//        bundle.putBoolean("wifi", room.isWifi());
//        bundle.putBoolean("parking", room.isParking());
//        bundle.putBoolean("pool", room.isPool());
//        bundle.putBoolean("minibar", room.isMinibar());
//        bundle.putBoolean("ac", room.isAc());
//        bundle.putBoolean("buffet", room.isBuffet());
        intent.putExtra("bundle", bundle);
        activity.startActivity(intent);
    }
}


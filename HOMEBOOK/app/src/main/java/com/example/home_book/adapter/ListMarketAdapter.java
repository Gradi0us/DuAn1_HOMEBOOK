package com.example.home_book.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.R;
import com.example.home_book.model.Room;

import java.util.ArrayList;

public class ListMarketAdapter extends RecyclerView.Adapter<ListMarketAdapter.ViewHolder>{
    Context context;
    ArrayList<Room> lisRoom;

    public ListMarketAdapter(Context context, ArrayList<Room> lisRoom) {
        this.context = context;
        this.lisRoom = lisRoom;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_homebook, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        byte[] hinhanh = lisRoom.get(position).getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
        holder.imgHomeBook.setImageBitmap(bitmap);
        holder.name.setText(lisRoom.get(position).getName());
        holder.type.setText(lisRoom.get(position).getCategory());
        holder.location.setText(lisRoom.get(position).getLocation());
        holder.ratingBar.setRating(lisRoom.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return lisRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeBook;
        TextView name,location,type;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHomeBook = itemView.findViewById(R.id.img_HomeBook);
            name = itemView.findViewById(R.id.tv_Name);
            location = itemView.findViewById(R.id.tv_vitri);
            type = itemView.findViewById(R.id.tv_Type);
            ratingBar = itemView.findViewById(R.id.number_stars);
        }
    }
}

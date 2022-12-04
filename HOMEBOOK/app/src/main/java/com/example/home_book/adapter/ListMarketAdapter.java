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

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;

import java.util.ArrayList;
import java.util.List;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        List<order> list= dao.getOrder("select * from order_tb where status = 1");
        byte[] hinhanh = lisRoom.get(position).getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
        holder.imgHomeBook.setImageBitmap(bitmap);
        holder.name.setText(lisRoom.get(position).getName());
        holder.type.setText(lisRoom.get(position).getCategory());
        holder.location.setText(lisRoom.get(position).getLocation());
        holder.ratingBar.setRating(lisRoom.get(position).getRate());
        holder.beds.setText(lisRoom.get(position).getBeds()+"");
        holder.status.setText((lisRoom.get(position).getStatus()-list.size())+"");

    }

    @Override
    public int getItemCount() {
        return lisRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeBook;
        TextView name,location,type,beds,status,people;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHomeBook = itemView.findViewById(R.id.img_HomeBook);
            name = itemView.findViewById(R.id.tv_Name);
            location = itemView.findViewById(R.id.tv_vitri);
            type = itemView.findViewById(R.id.tv_Type);
            ratingBar = itemView.findViewById(R.id.number_stars);
            beds = itemView.findViewById(R.id.tv_beds);
            status = itemView.findViewById(R.id.tv_status);

        }
    }
}

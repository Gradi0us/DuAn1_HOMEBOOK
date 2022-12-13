package com.example.home_book.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.activity.RatingActivity;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.example.home_book.model.rating;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<order> list;
    DAO dao;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public HistoryAdapter(Context context, ArrayList<order> list) {
        this.context = context;
        this.list = list;
        dao = new DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historyy, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room roomList = dao.get1Room("select * from room_tb where id=?", String.valueOf(list.get(position).getRoom_id()));
        holder.tvNameHome.setText(roomList.getName());
//        holder.tvBeds.setText(roomList.getBeds()+"");
        holder.tvCategory.setText(roomList.getCategory());
        holder.tvLocation.setText(roomList.getLocation());
        holder.numberstar.setRating(roomList.getRate());
        byte[] hinhanh = roomList.getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
        holder.imageView.setImageBitmap(bitmap);
        switch (roomList.getBeds()) {
            case 0:
                holder.tvBeds.setText("Single room");
                break;
            case 1:
                holder.tvBeds.setText("Twin room");
                break;
            case 2:
                holder.tvBeds.setText("Double room");
                break;
            case 3:
                holder.tvBeds.setText("Triple room");
                break;
            case 4:
                holder.tvBeds.setText("Quad room");
                break;
        }
        holder.btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RatingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_id", list.get(holder.getAdapterPosition()).getUser_id());
                bundle.putInt("room_id", list.get(holder.getAdapterPosition()).getRoom_id());
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);
            }
        });
        holder.tvDatecheckin.setText(format.format(list.get(position).getBooking_date()));
        holder.tvDatecheckout.setText(format.format(list.get(position).getReturn_date()));
//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                order order = list.get(holder.getAdapterPosition());
//                order.setStatus(0);
//                dao.UpdateOrder(order);
//            }
//        });
        List<rating> ratingList = dao.GetAllRating("select * from rating_tb where user_id ="+list.get(position).getUser_id()+" and room_id ="+list.get(holder.getAdapterPosition()).getRoom_id()+"",null);
        if(ratingList.size() !=0){
            holder.layout.setVisibility(View.GONE);
        }else {
            holder.layout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameHome, tvLocation, tvBeds, tvCategory, tvDatecheckin, tvDatecheckout;
        Button btnDanhGia, btnDelete;
        RatingBar numberstar;
        ImageView imageView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHome = itemView.findViewById(R.id.tv_name_homebook_ls);
            tvLocation = itemView.findViewById(R.id.tv_location_homebook_ls);
            tvBeds = itemView.findViewById(R.id.tv_beds_ls);
            tvCategory = itemView.findViewById(R.id.tv_category_ls);
            imageView = itemView.findViewById(R.id.order_history);
            btnDanhGia = itemView.findViewById(R.id.btn_danhgia1);
            tvDatecheckin = itemView.findViewById(R.id.tv_datecheckin);
            tvDatecheckout = itemView.findViewById(R.id.tv_datecheckout);
            numberstar = itemView.findViewById(R.id.number_stars);
            layout = itemView.findViewById(R.id.layout_history);
//            btnDelete = itemView.findViewById(R.id.xoa_button);
        }
    }
}

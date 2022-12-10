package com.example.home_book.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.activity.RatingActivity;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;
    ArrayList<order> list;
    DAO dao ;

    public HistoryAdapter(Context context, ArrayList<order> list) {
        this.context = context;
        this.list = list;
        dao = new DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room roomList = dao.get1Room("select * from room_tb where id=?",String.valueOf(list.get(position).getRoom_id()));
        holder.tvNameHome.setText(roomList.getName());
        holder.tvBeds.setText(roomList.getBeds()+"");
        holder.tvCategory.setText(roomList.getCategory());
        holder.tvLocation.setText(roomList.getLocation());
        holder.btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RatingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_id",list.get(holder.getAdapterPosition()).getUser_id());
                bundle.putInt("room_id",list.get(holder.getAdapterPosition()).getRoom_id());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameHome,tvLocation,tvBeds,tvCategory;
        Button btnDanhGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHome = itemView.findViewById(R.id.tv_name_homebook_ls);
            tvLocation = itemView.findViewById(R.id.tv_location_homebook_ls);
            tvBeds = itemView.findViewById(R.id.tv_beds_ls);
            tvCategory = itemView.findViewById(R.id.tv_category_ls);
            btnDanhGia = itemView.findViewById(R.id.btn_danhgia1);
        }
    }
}
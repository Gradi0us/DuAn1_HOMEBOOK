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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    Context context;
    ArrayList<order> list;

    public OrderAdapter(Context context, ArrayList<order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        int id =  list.get(position).getId();
        Room roomList =  dao.getRoom2("select * from room_tb where "+id+"",null);
        if(roomList!=null){
            byte[] hinhanh = roomList.getIMG();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
            holder.imageView.setImageBitmap(bitmap);
            holder.tvCategory.setText(roomList.getLocation());
            holder.tvName.setText(roomList.getName());
            holder.tvCost.setText(roomList.getCost()+"");
            holder.tvBeds.setText(roomList.getBeds()+"");
            holder.tvDateCheckIn.setText(list.get(position).getBooking_date()+"");
            holder.tvDateCheckIn.setText(list.get(position).getReturn_date()+"");
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        ImageView imageView;
        TextView tvDateCheckIn,tvDateCheckOut,tvName,tvLocation,tvCategory,tvBeds,tvCost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBeds = itemView.findViewById(R.id.tv_beds);
            tvCost = itemView.findViewById(R.id.tv_cost);
            tvDateCheckIn = itemView.findViewById(R.id.tv_datecheckin);
            tvDateCheckOut = itemView.findViewById(R.id.tv_datecheckout);
            tvName = itemView.findViewById(R.id.tv_name_homebook);
            tvLocation = itemView.findViewById(R.id.tv_location_homebook);
            ratingBar = itemView.findViewById(R.id.number_stars);
            imageView = itemView.findViewById(R.id.img_homebook);
        }
    }
}

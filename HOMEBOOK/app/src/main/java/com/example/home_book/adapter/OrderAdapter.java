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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    Context context;
    ArrayList<order> list;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

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
        int id =  list.get(position).getRoom_id();
//        Room roomList =  dao.getRoom2("select * from room_tb where id = "+id+"",null);
        Room roomList = dao.get1Room("select * from room_tb where id = ?", String.valueOf(id));
        if(roomList!=null){
            byte[] hinhanh = roomList.getIMG();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
            holder.imageView.setImageBitmap(bitmap);
//            holder.tvCategory.setText(roomList.getLocation());
            holder.tvName.setText(roomList.getName());
            holder.tvCost.setText(roomList.getCost()+"");
            holder.tvBeds.setText(roomList.getBeds()+"");
            switch (roomList.getBeds()){
                case 0:holder.tvBeds.setText("Phòng đơn");break;
                case 1:holder.tvBeds.setText("Phòng sinh đôi");break;
                case 2:holder.tvBeds.setText("Phòng đôi");break;
                case 3:holder.tvBeds.setText("Phòng ba");break;
                case 4:holder.tvBeds.setText("Phòng bốn");break;
            }
            holder.tvDateCheckIn.setText(format.format(list.get(position).getBooking_date()));
            holder.tvDateCheckOut.setText(format.format(list.get(position).getReturn_date()));
            holder.ratingBar.setRating(roomList.getRate());

            holder.tvCategory.setText(roomList.getCategory());
            holder.tvLocation.setText(roomList.getLocation());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        ImageView imageView;
        TextView tvDateCheckIn,tvDateCheckOut,tvName,tvLocation,tvCategory,tvBeds,tvCost,tvPeople;
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

            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}

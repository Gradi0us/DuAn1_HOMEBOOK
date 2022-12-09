package com.example.home_book.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.fragment.CartFragment;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.example.home_book.model.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    ArrayList<order> list;
    CartFragment fragment;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    int tienHoaDon;

    public OrderAdapter(Context context, ArrayList<order> list, CartFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        DAO dao = new DAO(context);
        int id = list.get(position).getRoom_id();
//        Room roomList =  dao.getRoom2("select * from room_tb where id = "+id+"",null);
        Room roomList = dao.get1Room("select * from room_tb where id = ?", String.valueOf(id));
        if (roomList != null) {
            byte[] hinhanh = roomList.getIMG();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
            holder.imageView.setImageBitmap(bitmap);
//            holder.tvCategory.setText(roomList.getLocation());
            holder.tvName.setText(roomList.getName());

            holder.tvCost.setText(roomList.getCost() + "");
            holder.tvBeds.setText(roomList.getBeds() + "");

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


            long diff = list.get(position).getReturn_date().getTime() - list.get(position).getBooking_date().getTime();
            int dayCount = (int) diff/(24 * 60 * 60 * 1000);
            tienHoaDon = roomList.getCost() * dayCount;

            holder.tvCost.setText(tienHoaDon+"");

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog(list.get(i),tienHoaDon);
                }
            });

            Date date = new Date();
            if( (date.after(list.get(i).getBooking_date()) && date.before(list.get(i).getReturn_date()) ) || date.equals(list.get(i).getBooking_date())){
                holder.button.setText("Đang nhận phòng");
                holder.button.setBackgroundResource(R.drawable.type_red);
                holder.button.setEnabled(false);
                list.get(i).setStatus(1);
                dao.UpdateOrder(list.get(i));
                fragment.congThemTien(list.get(i),tienHoaDon);
            }
            if(date.after(list.get(i).getReturn_date()) || ( date.equals(list.get(i).getReturn_date()) && date.after(list.get(i).getBooking_date()) )){
                holder.button.setText("Đã trả phòng");
                holder.button.setBackgroundResource(R.drawable.type_green);
                holder.button.setEnabled(false);
                list.get(i).setStatus(2);
                dao.UpdateOrder(list.get(i));
            }
        }
    }

    private void Dialog(order x,int tien) {
        Log.d("tien",tien+"");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure about that ?");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                fragment.xoaDon(x,tien);
                DAO dao = new DAO(context);
                dao.DeleteOrder(x.getId());
                fragment.loadData();
                dao = new DAO(context);
                dao.getOrder("select*from order_tb");
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        ImageView imageView;
        Button button;
        TextView tvDateCheckIn, tvDateCheckOut, tvName, tvLocation, tvCategory, tvBeds, tvCost, tvPeople;

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
            button = itemView.findViewById(R.id.cancel_button);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}

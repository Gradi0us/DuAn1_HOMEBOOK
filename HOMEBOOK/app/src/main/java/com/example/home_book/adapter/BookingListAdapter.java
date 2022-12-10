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
import com.example.home_book.model.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.ViewHolder> {
    Context context;
    ArrayList<order> listBooking;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    int tienHoaDon;
    public BookingListAdapter(Context context, ArrayList<order> listBooking) {
        this.context = context;
        this.listBooking = listBooking;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinglist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        user user = dao.get1User("select * from user_tb where id=?",listBooking.get(position).getUser_id()+"");
        Room room = dao.get1Room("select * from room_tb where id=?",listBooking.get(position).getRoom_id()+"");
        holder.tvNameUser.setText(user.getFullname());
        holder.tvContact.setText(String.valueOf(user.getPhone()));
        holder.ratingBar.setRating(room.getRate());
        holder.tvCategroy.setText(room.getCategory());
        holder.tvLocation.setText(room.getLocation());
        holder.tvNameHomeBook.setText(room.getName());
        switch (room.getBeds()){
            case 0:holder.tvBeds.setText("Phòng đơn");break;
            case 1:holder.tvBeds.setText("Phòng sinh đôi");break;
            case 2:holder.tvBeds.setText("Phòng đôi");break;
            case 3:holder.tvBeds.setText("Phòng ba");break;
            case 4:holder.tvBeds.setText("Phòng bốn");break;
        }
        long diff = listBooking.get(position).getReturn_date().getTime() - listBooking.get(position).getBooking_date().getTime();
        int dayCount = (int) diff/(24 * 60 * 60 * 1000);
        tienHoaDon = room.getCost() * dayCount;
        holder.tvCost.setText(tienHoaDon+"");
        holder.tvDateCheckIn.setText(format.format(listBooking.get(position).getBooking_date()));
        holder.tvDateCheckOut.setText(format.format(listBooking.get(position).getReturn_date()));
        byte[] hinhanh = room.getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.imghomebook.setImageBitmap(bitmap);
        holder.txtAppFee.setText(tienHoaDon*5/100+"");
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameHomeBook,tvNameUser,tvCost,tvLocation,tvBeds,tvContact,tvDateCheckIn,tvDateCheckOut,tvCategroy,txtAppFee;
        ImageView imghomebook;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHomeBook = itemView.findViewById(R.id.tv_name_homebook);
            tvLocation = itemView.findViewById(R.id.tv_location_homebook);
            tvCategroy = itemView.findViewById(R.id.tv_category);
            tvNameUser = itemView.findViewById(R.id.tv_name_user);
            tvCost = itemView.findViewById(R.id.tv_cost);
            tvBeds = itemView.findViewById(R.id.tv_beds);
            tvContact = itemView.findViewById(R.id.tv_contact);
            tvDateCheckIn = itemView.findViewById(R.id.tv_datecheckin);
            tvDateCheckOut = itemView.findViewById(R.id.tv_datecheckout);
            imghomebook = itemView.findViewById(R.id.img_homebook);
            ratingBar = itemView.findViewById(R.id.number_stars);
            txtAppFee = itemView.findViewById(R.id.txtAppFee);
        }
    }
}

package com.example.home_book.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.rating;
import com.example.home_book.model.user;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder>{
    Context context;
    ArrayList<rating> list;

    public RatingAdapter(Context context, ArrayList<rating> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewrate, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        user user = dao.get1User("select * from user_tb where id=?",String.valueOf(list.get(position).getUser_id()));
        holder.tvNameUser.setText(user.getFullname());
        holder.tvRating.setText(list.get(position).getRating()+"");
        holder.tvNote.setText(list.get(position).getNote());
        holder.imageView.setImageResource(user.getAvatar());
        //có r mà ??? n
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRating,tvNote,tvNameUser;
        CircleImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvNote = itemView.findViewById(R.id.tv_note);
            tvNameUser = itemView.findViewById(R.id.tv_name_user);
            imageView = itemView.findViewById(R.id.avaImg);
        }
    }
}

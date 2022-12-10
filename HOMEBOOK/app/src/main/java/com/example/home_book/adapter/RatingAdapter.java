package com.example.home_book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.R;
import com.example.home_book.model.rating;

import java.util.ArrayList;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRating.setText(list.get(position).getRating());
        holder.tvNote.setText(list.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRating,tvNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvNote = itemView.findViewById(R.id.tv_note);
        }
    }
}

package com.example.home_book.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.activity.OrderAcivity;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter  extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    Context context;
    ArrayList<Room> listRoom;
    Activity activity;

    public FavoritesAdapter(Context context, ArrayList<Room> listRoom, Activity activity) {
        this.context = context;
        this.listRoom = listRoom;
        this.activity = activity;
    }
    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favoritee, parent, false);
        return new FavoritesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        List<order> listoder= dao.getOrder("select * from order_tb where status  > 0 and room_id = "+listRoom.get(position).getId()+"");
        //
        if(listRoom.get(position).getStatus()-listoder.size()>0){
            holder.tv_nameHomebook.setText(listRoom.get(position).getName());
            String name = listRoom.get(position).getName();
            holder.tv_locationHomebook.setText(listRoom.get(position).getLocation());
            byte[] hinhanh = listRoom.get(position).getIMG();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
            holder.img_homebook.setImageBitmap(bitmap);
            switch (listRoom.get(position).getBeds()){
                case 0:holder.tvBeds.setText("Single room");break;
                case 1:holder.tvBeds.setText("Twin room");break;
                case 2:holder.tvBeds.setText("Double room");break;
                case 3:holder.tvBeds.setText("Triple room");break;
                case 4:holder.tvBeds.setText("Quad room");break;
            }
            holder.layoutitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickGoToDeTail(listRoom.get(holder.getAdapterPosition()), activity);
                }
            });
        }
//        else {
//
//
//            listRoom.remove(holder.getAdapterPosition());
//        }
//
//        holder.tv_nameHomebook.setText(listRoom.get(position).getName());
//        String name = listRoom.get(position).getName();
//        holder.tv_locationHomebook.setText(listRoom.get(position).getLocation());
//        byte[] hinhanh = listRoom.get(position).getIMG();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//
//
//        holder.img_homebook.setImageBitmap(bitmap);
//
//        switch (listRoom.get(position).getBeds()) {
//            case 0:
//                holder.tvBeds.setText("Phòng đơn");
//                break;
//            case 1:
//                holder.tvBeds.setText("Phòng sinh đôi");
//                break;
//            case 2:
//                holder.tvBeds.setText("Phòng đôi");
//                break;
//            case 3:
//                holder.tvBeds.setText("Phòng ba");
//                break;
//            case 4:
//                holder.tvBeds.setText("Phòng bốn");
//                break;
//        }
//
//        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickGoToDeTail(listRoom.get(holder.getAdapterPosition()), activity);
//            }
//        });
//        check(holder, position);
    }


    @Override
    public int getItemCount() {
        return listRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nameHomebook, tv_locationHomebook, tvBeds;
        private ImageView img_homebook;
        private ToggleButton img_favoriteHomebook;
        FrameLayout layoutitem;
        RecyclerView recyclerView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameHomebook = itemView.findViewById(R.id.tv_name_homebook);
            recyclerView = itemView.findViewById(R.id.ds_homebook);
            tv_locationHomebook = itemView.findViewById(R.id.tv_location_homebook);
            img_homebook = itemView.findViewById(R.id.img_homebook);
            layoutitem = itemView.findViewById(R.id.layout_click);
            tvBeds = itemView.findViewById(R.id.tv_beds);


        }
    }

    public void check(HomeBookApdater.ViewHolder holder, int position) {
        DAO dao = new DAO(context);
        List<order> listoder = dao.getOrder("select * from order_tb where status = 1 and room_id = " + listRoom.get(position).getId() + "");
        if (listRoom.get(position).getStatus() - listoder.size() == 0) {
            holder.layoutitem.setVisibility(View.GONE);
        }
    }

    private void onClickGoToDeTail(Room room, Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_room_detail, null);
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        Intent intent = new Intent(activity, OrderAcivity.class);
        Bundle bundle = new Bundle();
//        bundle.putString("name", room.getName());
//        bundle.putString("location", room.getLocation());
//        bundle.putString("category", room.getCategory());
//        bundle.putInt("status", room.getStatus());
//        bundle.putString("note", room.getNote());
//        bundle.putInt("beds", room.getBeds());
//        bundle.putInt("cost", room.getCost());
        bundle.putInt("id", room.getId());
//        bundle.putInt("rate", room.getRate());
//        bundle.putByteArray("img", room.getIMG());
//        bundle.putBoolean("wifi", room.isWifi());
//        bundle.putBoolean("parking", room.isParking());
//        bundle.putBoolean("pool", room.isPool());
//        bundle.putBoolean("minibar", room.isMinibar());
//        bundle.putBoolean("ac", room.isAc());
//        bundle.putBoolean("buffet", room.isBuffet());
        intent.putExtra("bundle", bundle);
        activity.startActivity(intent);
    }
}

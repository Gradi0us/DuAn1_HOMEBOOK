package com.example.home_book.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ListMarketAdapter extends RecyclerView.Adapter<ListMarketAdapter.ViewHolder> {
    Context context;
    ArrayList<Room> lisRoom;
    Activity activity;
    int REQUESTS_CODE_FOLDER = 123;
    int RESULT_OK = 123;
    ImageView imgAvtHome;
    DAO dao;
    byte[] IMG;
    boolean wifi, ac, minibar, parking, pool, buffet;

    public ListMarketAdapter(Context context, ArrayList<Room> lisRoom, Activity activity) {
        this.context = context;
        this.lisRoom = lisRoom;
        this.activity = activity;
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
        List<order> list = dao.getOrder("select * from order_tb where status = 1 and room_id =" + lisRoom.get(position).getId() + "");
        byte[] hinhanh = lisRoom.get(position).getIMG();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        imageAVT.setImageBitmap(bitmap);
        holder.imgHomeBook.setImageBitmap(bitmap);
        holder.name.setText(lisRoom.get(position).getName());
        holder.type.setText(lisRoom.get(position).getCategory());
        holder.location.setText(lisRoom.get(position).getLocation());
        holder.ratingBar.setRating(lisRoom.get(position).getRate());
        holder.beds.setText(lisRoom.get(position).getBeds() + "");
        switch (lisRoom.get(position).getBeds()) {
            case 0:holder.beds.setText("Single room");break;
            case 1:holder.beds.setText("Twin room");break;
            case 2:holder.beds.setText("Double room");break;
            case 3:holder.beds.setText("Triple room");break;
            case 4:holder.beds.setText("Quad room");break;
        }
        holder.status.setText((lisRoom.get(position).getStatus() - list.size()) + "");
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.itemView);
                popupMenu.getMenuInflater().inflate(R.menu.menu_click_item, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.xoa) {
                            Xoa(lisRoom.get(holder.getAdapterPosition()));
                        } else if (item.getItemId() == R.id.sua) {
                            Sua(lisRoom.get(holder.getAdapterPosition()));
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
//                return false;
        });
    }

    @Override
    public int getItemCount() {
        return lisRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeBook;
        TextView name, location, type, beds, status, people;
        RatingBar ratingBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHomeBook = itemView.findViewById(R.id.img_HomeBook);
            name = itemView.findViewById(R.id.tv_Name);
            location = itemView.findViewById(R.id.tv_vitri);
            type = itemView.findViewById(R.id.tv_Type);
            ratingBar = itemView.findViewById(R.id.number_stars);
            beds = itemView.findViewById(R.id.tv_beds);
            status = itemView.findViewById(R.id.tv_status);
            cardView = itemView.findViewById(R.id.layout_click);

        }
    }

    void Xoa(Room room) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Please login!");
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DAO dao = new DAO(context);
                List<order> list = dao.getOrder("select * from order_tb where room_id=" + room.getId() + "");
                if (list.size() == 0) {
                    dao.DeleteRoom(room.getId());
                    dao.DeleteFavourite2(room.getId());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Phòng đã được đặt", Toast.LENGTH_SHORT).show();
                }
            }
        });
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void Sua(Room room) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.update_room, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        TextInputEditText edtcost = view.findViewById(R.id.edt_cost);
        TextInputEditText edtstatus = view.findViewById(R.id.edt_status);

//        TextInputEditText edtpeople = view.findViewById(R.id.edt_people);

        SwitchCompat swwifi = view.findViewById(R.id.sw_wifi);
        SwitchCompat swac = view.findViewById(R.id.sw_ac);
        SwitchCompat swbuffet = view.findViewById(R.id.sw_buffet);
        SwitchCompat swminibar = view.findViewById(R.id.sw_minibar);
        SwitchCompat swparking = view.findViewById(R.id.sw_parking);
        SwitchCompat swpool = view.findViewById(R.id.sw_pool);
        Button btnAdd = view.findViewById(R.id.btn_confirm);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        swwifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                wifi = b;
            }
        });
        swac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ac = b;
            }
        });
        swbuffet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buffet = b;
            }
        });
        swminibar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                minibar = b;
            }
        });
        swparking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                parking = b;
            }
        });
        swpool.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pool = b;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cost = edtcost.getText().toString().trim();
//                String people = edtpeople.getText().toString().trim();
                String status = edtstatus.getText().toString().trim();

                if (cost.length() <= 0 || status.length() <= 0) {
                    Toast.makeText(context, "Không để trống", Toast.LENGTH_SHORT).show();
                } else {
                    dao = new DAO(context);
                    dao.UpdateRoom(new Room(room.getId(), room.getRate(), room.getBeds(), Integer.parseInt(status), Integer.parseInt(cost), wifi, ac, buffet, parking, pool, minibar, room.getNote(), room.getName(), room.getCategory(), room.getLocation(), room.getIMG(), room.getCollaborate_id()));
                    alertDialog.cancel();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        alertDialog.show();

    }
}

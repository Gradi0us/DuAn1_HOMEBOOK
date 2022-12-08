package com.example.home_book.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.menu.AccountManagerFragment;
import com.example.home_book.model.user;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListAccountAdapter extends RecyclerView.Adapter<ListAccountAdapter.ViewHolder> {

    Context context;
    ArrayList<user> list;
    AccountManagerFragment fragment;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public ListAccountAdapter(Context context, ArrayList<user> list, AccountManagerFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ListAccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new ListAccountAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAccountAdapter.ViewHolder holder, int position) {
        int i = position;
        DAO dao = new DAO(context);

        holder.name.setText(list.get(position).getFullname());
        if(list.get(position).getRole() == 0){
            holder.role.setText(R.string.collaborate);
            holder.role.setTextColor(Color.BLACK);
        }
        if(list.get(position).getRole() == 1){
            holder.role.setText(R.string.member);
            holder.role.setTextColor(Color.BLUE);
        }
        holder.email.setText(list.get(position).getEmail());
        holder.pass.setText(list.get(position).getPassword());
        holder.birth.setText(format.format(list.get(position).getBirth_day()));
        holder.phone.setText(list.get(position).getPhone());
        holder.money.setText(list.get(position).getMoney()+" đ");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure about that ?");
                builder.setCancelable(true);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.DeleteUser(list.get(i).getId());
                        Toast.makeText(context,"Xóa thành công.",Toast.LENGTH_SHORT).show();
                        fragment.LoadData();
                    }
                });

                builder.setNegativeButton("KO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,money,role,email,birth,phone;
        TextInputEditText pass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameAccountItem);
            money = itemView.findViewById(R.id.moneyAccountItem);
            role = itemView.findViewById(R.id.roleAccountItem);
            email = itemView.findViewById(R.id.emailAccountItem);
            birth = itemView.findViewById(R.id.birthAccountItem);
            phone = itemView.findViewById(R.id.phoneAccountItem);
            pass = itemView.findViewById(R.id.passwordAccountItem);
        }
    }
}
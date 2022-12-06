package com.example.home_book.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.menu.MoneyAdminFragment;
import com.example.home_book.model.NapThe;
import com.example.home_book.model.user;

import java.util.ArrayList;

public class ListMoneyAdapter extends RecyclerView.Adapter<ListMoneyAdapter.ViewHolder> {

    Context context;
    ArrayList<NapThe> list;
    MoneyAdminFragment fragment;

    public ListMoneyAdapter(Context context, ArrayList<NapThe> list, MoneyAdminFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ListMoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_money, parent, false);
        return new ListMoneyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMoneyAdapter.ViewHolder holder, int position) {
        int i = position;
        DAO dao = new DAO(context);

        user user = dao.get1User("select * from user_tb where id = ?",list.get(position).getUser_id()+"");

        holder.name.setText(user.getFullname());
        holder.money.setText(list.get(position).getMoney()+" đ");

        if(list.get(position).getStatus() == 1){
            holder.card.setBackgroundColor(Color.GREEN);
            holder.itemView.setEnabled(false);
        }

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//                View view = inflater.inflate(R.layout.custom_dialog, null);
//                TextView txtTien = view.findViewById(R.id.currentMoneyAdmin);
//                EditText txtThemTien = view.findViewById(R.id.addMoneyAdmin);
//                Button save = view.findViewById(R.id.saveMoneyAdmin);
//                Button cancel = view.findViewById(R.id.cancelMoneyAdmin);
//
//                txtTien.setText(listUser.get(position).getMoney()+"");
//
//                builder.setView(view);
//                builder.setTitle("Thêm tiền");
//                AlertDialog alertDialog = builder.create();
//
//                save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String tienAdd = txtThemTien.getText().toString();
//                        if(tienAdd.trim().length() <= 0){
//                            Toast.makeText(context,"Vui lòng nhập tiền cần thêm.",Toast.LENGTH_SHORT).show();
//                        }else if(tienAdd.trim().equals("0")){
//                            Toast.makeText(context,"Tiền không được bằng 0.",Toast.LENGTH_SHORT).show();
//                        }else{
//                            listUser.get(position).setMoney(listUser.get(position).getMoney() + Integer.parseInt(tienAdd));
//                            dao.UpdateUser(listUser.get(position));
//                            Toast.makeText(context,tienAdd + " đã được thêm vào tài khoản " + listUser.get(position).getFullname(),Toast.LENGTH_SHORT).show();
//                            Log.d("add", "Thêm thành công");
//                            alertDialog.dismiss();
//
//
//                        }
//                    }
//                });
//
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertDialog.cancel();
//                    }
//                });
//
//                alertDialog.show();
//                Log.d("add", "Hiện thành công");
//                fragment.addTien(listUser.get(position));
//                return false;
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure about that ?");
                builder.setCancelable(true);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.get(i).setStatus(1);
                        dao.UpdateNapThe(list.get(i));
                        user.setMoney(user.getMoney() + list.get(i).getMoney());
                        dao.UpdateUser(user);
                        Toast.makeText(context,"Nạp thành công.",Toast.LENGTH_SHORT).show();
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,money;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameItemMoney);
            money = itemView.findViewById(R.id.moneyItemMoney);
            card = itemView.findViewById(R.id.cardViewMoney);
        }
    }
}
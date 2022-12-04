package com.example.home_book.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.menu.MoneyAdminFragment;
import com.example.home_book.model.user;

import java.util.ArrayList;

public class ListMoneyAdapter extends RecyclerView.Adapter<ListMoneyAdapter.ViewHolder> {

    Context context;
    ArrayList<user> listUser;
    MoneyAdminFragment fragment;

    public ListMoneyAdapter(Context context, ArrayList<user> listUser, MoneyAdminFragment fragment) {
        this.context = context;
        this.listUser = listUser;
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
        DAO dao = new DAO(context);

        holder.name.setText(listUser.get(position).getFullname());
        holder.money.setText(listUser.get(position).getMoney()+" đ");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
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
                fragment.addTien(listUser.get(position));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,money;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameItemMoney);
            money = itemView.findViewById(R.id.moneyItemMoney);

        }
    }
}
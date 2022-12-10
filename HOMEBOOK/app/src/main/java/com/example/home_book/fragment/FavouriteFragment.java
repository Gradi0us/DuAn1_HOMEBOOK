package com.example.home_book.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.model.Favourite;
import com.example.home_book.model.Room;
import com.example.home_book.model.user;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    RecyclerView recyclerView;
    TextView txtTrong;
    ArrayList<Favourite> list1;
    String user_id = "";
    List<Room> list2;
    DAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = view.findViewById(R.id.favouriteRecyclerView);
        txtTrong = view.findViewById(R.id.txtTrong);
        dao = new DAO(getActivity());

        list2 = new ArrayList<>();

        SharedPreferences sP = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");

        txtTrong.setText("Vui lòng đăng nhập để xem yêu thích");

        if (dao.checkLogin(email, pass)) {
            user x = dao.get1User("select * from user_tb where email = ?", email);
            user_id = x.getId() + "";
            Log.d("id", user_id);
            txtTrong.setVisibility(View.GONE);
        }

        if (user_id != "") {
            if (Integer.parseInt(user_id) >= 0) {
                list1 = (ArrayList<Favourite>) dao.getFavourite(user_id);
                if (list1.size() <= 0) {
                    txtTrong.setVisibility(View.VISIBLE);
                    txtTrong.setText("Không có yêu thích");
                } else {
                    for(Favourite f : list1){
                        String room_id = f.getRoom_id() +"";
                        Room r = dao.get1Room("select * from room_tb where id = ?",room_id);
                        list2.add(r);
                    }
                }
            }
        }

        LoadData();

        return view;
    }

    

//    public void showDialogXoa(String room_id,String member_id){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage("Bạn có muốn xóa không?");
//        builder.setCancelable(true);
//        builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                list1 = (ArrayList<Favourite>) dao.getFavourite(member_id);
////                for(Favourite fa : list1){
////                    if(Integer.parseInt(room_id) == fa.getRoom_id() && Integer.parseInt(member_id) == fa.getUser_id()){
////                        dao.DeleteFavourite(fa.getId());
////                    }
////                }
//
//                Favourite fa = dao.get1Favourite(room_id,member_id);
//                dao.DeleteFavourite(fa.getId());
//
//                Toast.makeText(getActivity(), "Xóa Thành Công",Toast.LENGTH_SHORT).show();
//                LoadData();
//            }
//        });
//        builder.setNegativeButton("KO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        android.app.AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    public void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeBookApdater homeBookApdater = new HomeBookApdater(getContext(), (ArrayList<Room>) list2, getActivity());
        recyclerView.setAdapter(homeBookApdater);
    }
}
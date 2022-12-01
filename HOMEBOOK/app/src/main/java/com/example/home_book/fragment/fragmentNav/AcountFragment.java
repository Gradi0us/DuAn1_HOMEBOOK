package com.example.home_book.fragment.fragmentNav;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.HomeBookApdater;
import com.example.home_book.adapter.ListMarketAdapter;
import com.example.home_book.model.Room;
import com.example.home_book.model.roomImage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.home_book.model.rooms;

import java.util.List;


public class AcountFragment extends Fragment {
    int REQUESTS_CODE_FOLDER = 123;
    int RESULT_OK = 123;
    ImageView imgAvtHome;
    int myRating;
    DAO dao;
    byte[] IMG;
    boolean wifi,ac,minibar,parking,pool,buffet;
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_upload_fragment, container, false);
        recyclerView = v.findViewById(R.id.recycles_market);
        dao = new DAO(getContext());
        FloatingActionButton floatingActionButton = v.findViewById(R.id.btn_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddRoom();
            }
        });
        loadDaTa();
        return v;
    }

    public void DialogAddRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.float_button_call, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        TextInputEditText edtname = view.findViewById(R.id.edt_namehomebook);
        TextInputEditText edthoteldetail = view.findViewById(R.id.edt_hoteldetail);
        TextInputEditText edtcost = view.findViewById(R.id.edt_cost);
        TextInputEditText edtstatus = view.findViewById(R.id.edt_status);
        TextInputEditText edtlocation = view.findViewById(R.id.edt_location);
        TextInputEditText edtpeople = view.findViewById(R.id.edt_people);
        ImageView imgAddimage = view.findViewById(R.id.img_addimage);
        imgAvtHome = view.findViewById(R.id.img_avthome);
        RatingBar ratingBara = view.findViewById(R.id.star_homebook);
        SwitchCompat swwifi = view.findViewById(R.id.sw_wifi);
        SwitchCompat swac = view.findViewById(R.id.sw_ac);
        SwitchCompat swbuffet = view.findViewById(R.id.sw_buffet);
        SwitchCompat swminibar = view.findViewById(R.id.sw_minibar);
        SwitchCompat swparking = view.findViewById(R.id.sw_parking);
        SwitchCompat swpool = view.findViewById(R.id.sw_pool);
        Button btnAdd = view.findViewById(R.id.btn_confirm);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Spinner spnbeds = view.findViewById(R.id.spn_beds);
        Spinner spncategory = view.findViewById(R.id.spn_category);
        ArrayList<Integer> bed = new ArrayList<>();
        bed.add(1);
        bed.add(2);
        bed.add(3);
        bed.add(4);
        bed.add(5);
        ArrayAdapter adapterBed = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, bed);
        spnbeds.setAdapter(adapterBed);
        ArrayList<String> category = new ArrayList<>();
        category.add("hotel");
        category.add("homestay");
        category.add("apartment");
        ArrayAdapter adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        spncategory.setAdapter(adapterCategory);
        imgAddimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUESTS_CODE_FOLDER);
            }
        });
        ratingBara.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                myRating = (int) ratingBar.getRating();
            }
        });
        swwifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                wifi=b;
            }
        });
        swac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ac=b;
            }
        });
        swbuffet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buffet=b;
            }
        });
        swminibar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                minibar=b;
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
                int beds = (int) spnbeds.getSelectedItem();
                String category = (String) spncategory.getSelectedItem();
                String name = edtname.getText().toString().trim();
                String hoteldetail = edthoteldetail.getText().toString().trim();
                String cost = edtcost.getText().toString().trim();
                String people = edtpeople.getText().toString().trim();
                String status = edtstatus.getText().toString().trim();
                String location = edtlocation.getText().toString().trim();
                myRating = (int) ratingBara.getRating();
                dao.AddRoom(new Room(myRating,beds,Integer.parseInt(status),Integer.parseInt(cost),wifi,ac,buffet,parking,pool,minibar,hoteldetail,name,category,location,IMG,Integer.parseInt(people)));
                alertDialog.cancel();
                loadDaTa();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESTS_CODE_FOLDER && requestCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAvtHome.setImageBitmap(bitmap);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAvtHome.getDrawable();
                Bitmap bitmap1 = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                IMG = stream.toByteArray();
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getUsername().equalsIgnoreCase(username)) {
//                        databaseQLTK.UpdateHinhAnh(new HinhAnh(IMG, username));
//                    } else {
//                        databaseQLTK.InsertHinhAnh(new HinhAnh(IMG, username));
//                    }
//                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void loadDaTa(){
        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom("select * from room_tb",null);
//        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom2();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ListMarketAdapter homeBookApdater = new ListMarketAdapter(getContext(),list);
        recyclerView.setAdapter(homeBookApdater);
    }
}
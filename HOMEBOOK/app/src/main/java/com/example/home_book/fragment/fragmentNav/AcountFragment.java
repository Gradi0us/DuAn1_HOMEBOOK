package com.example.home_book.fragment.fragmentNav;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.ListMarketAdapter;
import com.example.home_book.model.Room;
import com.example.home_book.model.user;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class AcountFragment extends Fragment {
    int REQUESTS_CODE_FOLDER = 123;
    int RESULT_OK = 123;
    ImageView imgAvtHome;
    int myRating,ctv_id;
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

        SharedPreferences sP = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");

        if (dao.checkLogin(email, pass)) {
            user x = dao.get1User("select * from user_tb where email = ?", email);
            ctv_id = x.getId();
        }

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
//        TextInputEditText edtpeople = view.findViewById(R.id.edt_people);
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
        ImageView btnOut = view.findViewById(R.id.thoat);
        ArrayList<String> bed = new ArrayList<>();
        bed.add("Single Room");
        bed.add("Twin Room");
        bed.add("Double Room");
        bed.add("Triple Room");
        bed.add("Quad Room");
        ArrayAdapter adapterBed = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, bed);
        spnbeds.setAdapter(adapterBed);
        ArrayList<String> category = new ArrayList<>();
        category.add("Hotel");
        category.add("Homestay");
        category.add("Apartment");
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
                int beds = spnbeds.getSelectedItemPosition();
                String category = (String) spncategory.getSelectedItem();
                String name = edtname.getText().toString().trim();
                String hoteldetail = edthoteldetail.getText().toString().trim();
                String cost = edtcost.getText().toString().trim();
//                String people = edtpeople.getText().toString().trim();
                String status = edtstatus.getText().toString().trim();
                String location = edtlocation.getText().toString().trim();
                myRating = (int) ratingBara.getRating();
                if (name.length() <= 0 ||
                        hoteldetail.length()<=0 ||
                        cost.length()<=0 ||
                        status.length()<=0 ||
                        location.length()<=0||IMG.length<=0){
                    Toast.makeText(getActivity(), "Do not leave blank", Toast.LENGTH_SHORT).show();
                    DialogNotification("Do not leave blank");
                }else{
                    dao.AddRoom(new Room(myRating,beds,Integer.parseInt(status),Integer.parseInt(cost),wifi,ac,buffet,parking,pool,minibar,hoteldetail,name,category,location,IMG,ctv_id));
                    alertDialog.cancel();
                    loadDaTa();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            alertDialog.cancel();
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
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
        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom("select * from room_tb where collaborate_id =?",String.valueOf(ctv_id));
//        ArrayList<Room> list = (ArrayList<Room>) dao.getRoom2();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ListMarketAdapter homeBookApdater = new ListMarketAdapter(getContext(),list,getActivity());
        recyclerView.setAdapter(homeBookApdater);
    }
    public void DialogNotification(String notification) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        View alert = LayoutInflater.from(this).inflate(R.layout.layout_viewrate,null);
//        builder.setContentView(R.layout.layout_viewrate);
        builder.setMessage(notification);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.create();
        alertDialog.show();
    }
}
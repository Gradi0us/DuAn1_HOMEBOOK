package com.example.home_book.menu;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.NapThe;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.example.home_book.model.rating;
import com.example.home_book.model.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AdminHistoryFragment extends Fragment {

    EditText txtBeginHistory, txtEndHistory;
    TextView txtTongGia;
    Button buttonLocHistory;
    Spinner historyAdminSpinner;
    ListView listMoneyHistory, listRateHistory;
    List<order> listOrder = new ArrayList<>();
    List<rating> listRate = new ArrayList<>();
    DAO dao;
    int myear, mmonth, mday;
    int tongGia;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_history, container, false);

        txtBeginHistory = view.findViewById(R.id.txtBeginHistory);
        txtEndHistory = view.findViewById(R.id.txtEndHistory);
        buttonLocHistory = view.findViewById(R.id.buttonLocHistory);
        historyAdminSpinner = view.findViewById(R.id.historyAdminSpinner);
        listMoneyHistory = view.findViewById(R.id.listMoneyHistory);
        listRateHistory = view.findViewById(R.id.listRateHistory);
        txtTongGia = view.findViewById(R.id.txtTongGia);
        dao = new DAO(getActivity());

        ArrayList<String> cheDo = new ArrayList<>();
        cheDo.add("Tổng chi phí của thành viên");
        cheDo.add("Thu nhập của collaborate");
        cheDo.add("Thu nhập của app");
        cheDo.add("Lịch sử đánh giá");
        ArrayAdapter adapterCheDo = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, cheDo);
        historyAdminSpinner.setAdapter(adapterCheDo);

        txtBeginHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener mDatetungay = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myear = year;
                        mmonth = month;
                        mday = dayOfMonth;
                        GregorianCalendar c = new GregorianCalendar(myear, mmonth, mday);
                        txtBeginHistory.setText(format.format(c.getTime()));
                    }
                };

                Calendar c = Calendar.getInstance();
                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDatetungay, myear, mmonth, mday);
                d.show();
            }
        });

        txtEndHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener mDatetungay = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myear = year;
                        mmonth = month;
                        mday = dayOfMonth;
                        GregorianCalendar c = new GregorianCalendar(myear, mmonth, mday);
                        txtEndHistory.setText(format.format(c.getTime()));
                    }
                };

                Calendar c = Calendar.getInstance();
                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDatetungay, myear, mmonth, mday);
                d.show();
            }
        });

        buttonLocHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        historyAdminSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        txtTongGia.setText("");
                        txtTongGia.setVisibility(View.GONE);
                        listMoneyHistory.setVisibility(View.VISIBLE);
                        listRateHistory.setVisibility(View.GONE);
                        listOrder = dao.getOrder("select * from order_tb where status != '0'");
                        LoadMoneyMember();
                        break;
                    case 1:
                        txtTongGia.setText("");
                        txtTongGia.setVisibility(View.GONE);
                        listRateHistory.setVisibility(View.GONE);
                        listOrder = dao.getOrder("select * from order_tb where status != '0'");
                        LoadMoneyCollaborate();
                        break;
                    case 2:
                        txtTongGia.setVisibility(View.VISIBLE);
                        listMoneyHistory.setVisibility(View.VISIBLE);
                        listRateHistory.setVisibility(View.GONE);
                        listOrder = dao.getOrder("select * from order_tb");
                        LoadMoneyAdmin();
                        break;
                    case 3:
                        txtTongGia.setText("");
                        txtTongGia.setVisibility(View.GONE);
                        listMoneyHistory.setVisibility(View.GONE);
                        listRateHistory.setVisibility(View.VISIBLE);
                        listRate = dao.GetAllRating("select * from rating_tb",null);
                        LoadRate();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        LoadMoneyMember();

        return view;
    }

    private void LoadMoneyMember() {
        tongGia = 0;
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listOrder.size();
            }

            @Override
            public Object getItem(int position) {
                return listOrder.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            class ViewHolder {
                TextView ten, phong, money, day, status,note;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
                    holder = new ViewHolder();
                    holder.ten = convertView.findViewById(R.id.txtTenHistory);
                    holder.phong = convertView.findViewById(R.id.txtPhongHistory);
                    holder.money = convertView.findViewById(R.id.txtMoneyHistory);
                    holder.day = convertView.findViewById(R.id.txtNgayHistory);
                    holder.status = convertView.findViewById(R.id.txtStatusHistory);
                    holder.note = convertView.findViewById(R.id.txtNoteHistory);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                String loaiPhong = "";
                holder.note.setVisibility(View.GONE);

                user user = dao.get1User("select * from user_tb where id = ?",listOrder.get(position).getUser_id()+"");
                Room room = dao.get1Room("select * from room_tb where id = ?",listOrder.get(position).getRoom_id()+"");

                switch (room.getBeds()){
                    case 0:loaiPhong = "Phòng đơn";break;
                    case 1:loaiPhong = "Phòng sinh đôi";break;
                    case 2:loaiPhong = "Phòng đôi";break;
                    case 3:loaiPhong = "Phòng ba";break;
                    case 4:loaiPhong = "Phòng bốn";break;
                }

                long diff = listOrder.get(position).getReturn_date().getTime() - listOrder.get(position).getBooking_date().getTime();
                int dayCount = (int) diff/(24 * 60 * 60 * 1000);

                holder.ten.setText(user.getFullname());
                holder.phong.setText(room.getName() + " - " + loaiPhong);
                holder.money.setText("Giá: " + (int)(room.getCost() * dayCount)+"đ");
                holder.day.setText(format.format(listOrder.get(position).getBooking_date()) + " - " + format.format(listOrder.get(position).getReturn_date()));

                if(listOrder.get(position).getStatus() == 1){
                    holder.status.setText("Đang nhận phòng");
                    holder.status.setTextColor(Color.RED);
                }
                if(listOrder.get(position).getStatus() == 2){
                    holder.status.setText("Đã trả phòng");
                    holder.status.setTextColor(Color.GREEN);
                }

                return convertView;
            }
        };

        listMoneyHistory.setAdapter(adapter);

    }

    private void LoadMoneyCollaborate() {
        tongGia = 0;
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listOrder.size();
            }

            @Override
            public Object getItem(int position) {
                return listOrder.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            class ViewHolder {
                TextView ten, phong, money, day, status,note;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
                    holder = new ViewHolder();
                    holder.ten = convertView.findViewById(R.id.txtTenHistory);
                    holder.phong = convertView.findViewById(R.id.txtPhongHistory);
                    holder.money = convertView.findViewById(R.id.txtMoneyHistory);
                    holder.day = convertView.findViewById(R.id.txtNgayHistory);
                    holder.status = convertView.findViewById(R.id.txtStatusHistory);
                    holder.note = convertView.findViewById(R.id.txtNoteHistory);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.note.setVisibility(View.GONE);

                String loaiPhong = "";
                Room room = dao.get1Room("select * from room_tb where id = ?",listOrder.get(position).getRoom_id()+"");
                user user = dao.get1User("select * from user_tb where id = ?",room.getCollaborate_id()+"");

                long diff = listOrder.get(position).getReturn_date().getTime() - listOrder.get(position).getBooking_date().getTime();
                int dayCount = (int) diff/(24 * 60 * 60 * 1000);

                switch (room.getBeds()){
                    case 0:loaiPhong = "Phòng đơn";break;
                    case 1:loaiPhong = "Phòng sinh đôi";break;
                    case 2:loaiPhong = "Phòng đôi";break;
                    case 3:loaiPhong = "Phòng ba";break;
                    case 4:loaiPhong = "Phòng bốn";break;
                }

                holder.ten.setText(user.getFullname());
                holder.phong.setText(room.getName() + " - " + loaiPhong);
                holder.money.setText("Tiền: " + (((int)(room.getCost() * dayCount)) - ((int)(room.getCost() * dayCount))*5/100)*1/100+"đ");
                holder.day.setText(format.format(listOrder.get(position).getBooking_date()) + " - " + format.format(listOrder.get(position).getReturn_date()));

                if(listOrder.get(position).getStatus() == 1){
                    holder.status.setText("Đang nhận phòng");
                    holder.status.setTextColor(Color.RED);
                }
                if(listOrder.get(position).getStatus() == 2){
                    holder.status.setText("Đã trả phòng");
                    holder.status.setTextColor(Color.GREEN);
                }

                return convertView;
            }
        };

        listMoneyHistory.setAdapter(adapter);

    }

    private void LoadMoneyAdmin() {
        tongGia = 0;
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listOrder.size();
            }

            @Override
            public Object getItem(int position) {
                return listOrder.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            class ViewHolder {
                TextView ten, phong, money, day, status,note;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
                    holder = new ViewHolder();
                    holder.ten = convertView.findViewById(R.id.txtTenHistory);
                    holder.phong = convertView.findViewById(R.id.txtPhongHistory);
                    holder.money = convertView.findViewById(R.id.txtMoneyHistory);
                    holder.day = convertView.findViewById(R.id.txtNgayHistory);
                    holder.status = convertView.findViewById(R.id.txtStatusHistory);
                    holder.note = convertView.findViewById(R.id.txtNoteHistory);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.note.setVisibility(View.GONE);

                String loaiPhong = "";
                user user = dao.get1User("select * from user_tb where id = ?",listOrder.get(position).getUser_id()+"");
                Room room = dao.get1Room("select * from room_tb where id = ?",listOrder.get(position).getRoom_id()+"");

                long diff = listOrder.get(position).getReturn_date().getTime() - listOrder.get(position).getBooking_date().getTime();
                int dayCount = (int) diff/(24 * 60 * 60 * 1000);

                switch (room.getBeds()){
                    case 0:loaiPhong = "Phòng đơn";break;
                    case 1:loaiPhong = "Phòng sinh đôi";break;
                    case 2:loaiPhong = "Phòng đôi";break;
                    case 3:loaiPhong = "Phòng ba";break;
                    case 4:loaiPhong = "Phòng bốn";break;
                }

                holder.ten.setText(user.getFullname());
                holder.phong.setText(room.getName() + " - " + loaiPhong);
                holder.money.setText("Tiền: " + ((int)(room.getCost() * dayCount))*5/100 + "đ");
                holder.day.setText(format.format(listOrder.get(position).getBooking_date()) + " - " + format.format(listOrder.get(position).getReturn_date()));

                if(listOrder.get(position).getStatus() == 0){
                    holder.status.setText("Đang đợi phòng");
                    holder.status.setTextColor(Color.BLACK);
                }

                if(listOrder.get(position).getStatus() == 1){
                    holder.status.setText("Đã nhận phòng");
                    holder.status.setTextColor(Color.RED);
                }
                if(listOrder.get(position).getStatus() == 2){
                    holder.status.setText("Đã trả phòng");
                    holder.status.setTextColor(Color.GREEN);
                }

                if(listOrder.get(position).getStatus() == 3){
                    holder.status.setText("Đã hủy phòng");
                    holder.status.setTextColor(Color.BLACK);
                }

                tongGia+=((int)(room.getCost() * dayCount))*5/100;

                return convertView;
            }
        };

        listMoneyHistory.setAdapter(adapter);
        txtTongGia.setText("Tổng: " + tongGia);

    }

    private void LoadRate() {
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listRate.size();
            }

            @Override
            public Object getItem(int position) {
                return listRate.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            class ViewHolder {
                TextView ten, phong, money, day, status,note;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
                    holder = new ViewHolder();
                    holder.ten = convertView.findViewById(R.id.txtTenHistory);
                    holder.phong = convertView.findViewById(R.id.txtPhongHistory);
                    holder.money = convertView.findViewById(R.id.txtMoneyHistory);
                    holder.day = convertView.findViewById(R.id.txtNgayHistory);
                    holder.status = convertView.findViewById(R.id.txtStatusHistory);
                    holder.note = convertView.findViewById(R.id.txtNoteHistory);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.note.setVisibility(View.VISIBLE);

                String loaiPhong = "";
                user user = dao.get1User("select * from user_tb where id = ?",listRate.get(position).getUser_id()+"");
                order order = dao.getOrder1("select * from order_tb where id = ?",listRate.get(position).getOrder_id()+"");
                Room room = dao.get1Room("select * from room_tb where id = ?",order.getRoom_id() + "");

                long diff = order.getReturn_date().getTime() - order.getBooking_date().getTime();
                int dayCount = (int) diff/(24 * 60 * 60 * 1000);

                switch (room.getBeds()){
                    case 0:loaiPhong = "Phòng đơn";break;
                    case 1:loaiPhong = "Phòng sinh đôi";break;
                    case 2:loaiPhong = "Phòng đôi";break;
                    case 3:loaiPhong = "Phòng ba";break;
                    case 4:loaiPhong = "Phòng bốn";break;
                }

                holder.ten.setText(user.getFullname());
                holder.phong.setText(room.getName() + " - " + loaiPhong);
                holder.money.setText("Giá: " + (int)(room.getCost() * dayCount) + "đ");
                holder.day.setText(format.format(order.getBooking_date()) + " - " + format.format(order.getReturn_date()));

                switch (listRate.get(position).getRating()){
                    case 1:
                        holder.status.setText("Very bad");
                        holder.status.setTextColor(Color.RED);
                        break;
                    case 2:
                        holder.status.setText("Bad");
                        holder.status.setTextColor(Color.YELLOW);
                        break;
                    case 3:
                        holder.status.setText("Normal");
                        holder.status.setTextColor(Color.BLACK);
                        break;
                    case 4:
                        holder.status.setText("Good");
                        holder.status.setTextColor(Color.BLUE);
                        break;
                    case 5:
                        holder.status.setText("Very good");
                        holder.status.setTextColor(Color.GREEN);
                        break;
                }

                holder.note.setText("Note: " + listRate.get(position).getNote());

                return convertView;
            }
        };

        listRateHistory.setAdapter(adapter);

    }

}
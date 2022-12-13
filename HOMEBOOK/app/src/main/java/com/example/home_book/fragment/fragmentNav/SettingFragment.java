package com.example.home_book.fragment.fragmentNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.adapter.BookingListAdapter;
import com.example.home_book.model.Room;
import com.example.home_book.model.order;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class SettingFragment extends Fragment {
    RecyclerView recyclerView;
    TextInputEditText editText;
    RadioButton checkBox1, checkBox2, checkBox3;
    List<Room> listRoom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bookinglist_fragmentlayout, container, false);
        recyclerView = v.findViewById(R.id.ds_booking);
        editText = v.findViewById(R.id.adminUser);
        checkBox1 = v.findViewById(R.id.checkBox1);
        checkBox2 = v.findViewById(R.id.checkBox2);
        checkBox3 = v.findViewById(R.id.checkBox3);
        DAO dao = new DAO(getActivity());

//        listRoom = new ArrayList<>();
//        if (editText.getText().toString().isEmpty() || editText.getText().toString().length() <= 0) {
//            listRoom = dao.getRoom("select * from room_tb", null);
//        } else {
//            listRoom = dao.getRoom("select * from room_tb where fullname like '" + editText.getText().toString() + "%'", null);
//        }

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty() || editText.getText().toString().length() <= 0) {
                    listRoom = dao.getRoom("select * from room_tb", null);
                } else {
                    listRoom = dao.getRoom("select * from room_tb where fullname like '" + editText.getText().toString() + "%'", null);
                }

                List<order> list = new ArrayList<>();
                if (listRoom.size() <= 0) {
                    list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 0");
                } else {
                    for (Room x : listRoom) {
                        List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 0", x.getId() + "");
                        list.addAll(list1);
                    }
                }
                Log.d("list order", list.size() + " + " + listRoom.size());
                loadData((ArrayList<order>) list);
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty() || editText.getText().toString().length() <= 0) {
                    listRoom = dao.getRoom("select * from room_tb", null);
                } else {
                    listRoom = dao.getRoom("select * from room_tb where fullname like '" + editText.getText().toString() + "%'", null);
                }

                List<order> list = new ArrayList<>();
                if (listRoom.size() <= 0) {
                    list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 1");
                } else {
                    for (Room x : listRoom) {
                        List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 1", x.getId() + "");
                        list.addAll(list1);
                    }
                }
                Log.d("list order", list.size() + " + " + listRoom.size());
                loadData((ArrayList<order>) list);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty() || editText.getText().toString().length() <= 0) {
                    listRoom = dao.getRoom("select * from room_tb", null);
                } else {
                    listRoom = dao.getRoom("select * from room_tb where fullname like '" + editText.getText().toString() + "%'", null);
                }

                List<order> list = new ArrayList<>();
                if (listRoom.size() <= 0) {
                    list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 2");
                } else {
                    for (Room x : listRoom) {
                        List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?  and status = 2", x.getId() + "");
                        list.addAll(list1);
                    }
                }
                Log.d("list order", list.size() + " + " + listRoom.size());
                loadData((ArrayList<order>) list);
            }
        });
//
//        else if (checkBox1.isChecked() && checkBox2.isChecked()) {
//            List<order> list = new ArrayList<>();
//            if (listRoom.size() <= 0) {
//                list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 0");
//            } else {
//                for (Room x : listRoom) {
//                    List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 0 or status = 1",x.getId()+"");
//                    list.addAll(list1);
//                }
//            }
//            Log.d("list order",list.size()+" + " + listRoom.size());
//            loadData((ArrayList<order>) list);
//        } else if (checkBox2.isChecked() && checkBox3.isChecked()) {
//            List<order> list = new ArrayList<>();
//            if (listRoom.size() <= 0) {
//                list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 1");
//            } else {
//                for (Room x : listRoom) {
//                    List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 1 or status = 2",x.getId()+"");
//                    list.addAll(list1);
//                }
//            }
//            Log.d("list order",list.size()+" + " + listRoom.size());
//            loadData((ArrayList<order>) list);
//        } else if (checkBox3.isChecked() && checkBox1.isChecked()) {
//            List<order> list = new ArrayList<>();
//            if (listRoom.size() <= 0) {
//                list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 2");
//            } else {
//                for (Room x : listRoom) {
//                    List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?  and status = 2 or status = 0",x.getId()+"");
//                    list.addAll(list1);
//                }
//            }
//            Log.d("list order",list.size()+" + " + listRoom.size());
//            loadData((ArrayList<order>) list);
//        }
//
//        else {
//            List<order> list = new ArrayList<>();
//            if (listRoom.size() <= 0) {
//                list = (ArrayList<order>) dao.getOrder("select * from order_tb");
//            } else {
//                for (Room x : listRoom) {
//                    List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?",x.getId()+"");
//                    list.addAll(list1);
//                }
//            }
//            Log.d("list order",list.size()+" + " + listRoom.size());
//            loadData((ArrayList<order>) list);
//        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String xxx = editText.getText().toString();
                List<Room> listRoom = dao.getRoom("select * from room_tb where fullname like '" + xxx + "%'", null);
                if (checkBox1.isChecked()) {
                    List<order> list = new ArrayList<>();
                    if (listRoom.size() <= 0) {
                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 0");
                    } else {
                        for (Room x : listRoom) {
                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 0", x.getId() + "");
                            list.addAll(list1);
                        }
                    }
                    Log.d("list order", list.size() + " + " + listRoom.size());
                    loadData((ArrayList<order>) list);
                } else if (checkBox2.isChecked()) {
                    List<order> list = new ArrayList<>();
                    if (listRoom.size() <= 0) {
                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 1");
                    } else {
                        for (Room x : listRoom) {
                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 1", x.getId() + "");
                            list.addAll(list1);
                        }
                    }
                    Log.d("list order", list.size() + " + " + listRoom.size());
                    loadData((ArrayList<order>) list);
                } else if (checkBox3.isChecked()) {
                    List<order> list = new ArrayList<>();
                    if (listRoom.size() <= 0) {
                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 2");
                    } else {
                        for (Room x : listRoom) {
                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?  and status = 2", x.getId() + "");
                            list.addAll(list1);
                        }
                    }
                    Log.d("list order", list.size() + " + " + listRoom.size());
                    loadData((ArrayList<order>) list);
                }

//                else if (checkBox1.isChecked() && checkBox2.isChecked()) {
//                    List<order> list = new ArrayList<>();
//                    if (listRoom.size() <= 0) {
//                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 0");
//                    } else {
//                        for (Room x : listRoom) {
//                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 0 or status = 1",x.getId()+"");
//                            list.addAll(list1);
//                        }
//                    }
//                    Log.d("list order",list.size()+" + " + listRoom.size());
//                    loadData((ArrayList<order>) list);
//                } else if (checkBox2.isChecked() && checkBox3.isChecked()) {
//                    List<order> list = new ArrayList<>();
//                    if (listRoom.size() <= 0) {
//                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 1");
//                    } else {
//                        for (Room x : listRoom) {
//                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ? and status = 1 or status = 2",x.getId()+"");
//                            list.addAll(list1);
//                        }
//                    }
//                    Log.d("list order",list.size()+" + " + listRoom.size());
//                    loadData((ArrayList<order>) list);
//                } else if (checkBox3.isChecked() && checkBox1.isChecked()) {
//                    List<order> list = new ArrayList<>();
//                    if (listRoom.size() <= 0) {
//                        list = (ArrayList<order>) dao.getOrder("select * from order_tb and status = 2");
//                    } else {
//                        for (Room x : listRoom) {
//                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?  and status = 2 or status = 0",x.getId()+"");
//                            list.addAll(list1);
//                        }
//                    }
//                    Log.d("list order",list.size()+" + " + listRoom.size());
//                    loadData((ArrayList<order>) list);
//                }

                else {
                    List<order> list = new ArrayList<>();
                    if (listRoom.size() <= 0) {
                        list = (ArrayList<order>) dao.getOrder("select * from order_tb");
                    } else {
                        for (Room x : listRoom) {
                            List<order> list1 = dao.getNhieuOrder("select * from order_tb where room_id = ?", x.getId() + "");
                            list.addAll(list1);
                        }
                    }
                    Log.d("list order", list.size() + " + " + listRoom.size());
                    loadData((ArrayList<order>) list);
                }
            }
        });
        ArrayList<order> list = (ArrayList<order>) dao.getOrder("select * from order_tb ");
        loadData(list);
        return v;
    }

    void loadData(ArrayList<order> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        BookingListAdapter adapter = new BookingListAdapter(getContext(), (ArrayList<order>) list);
        recyclerView.setAdapter(adapter);
    }
}
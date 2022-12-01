package com.example.home_book.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.home_book.DAO.DAO;
import com.example.home_book.R;
import com.example.home_book.model.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView logo;
    private Handler handler = new Handler();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logo = findViewById(R.id.logo);
        progressBar = findViewById(R.id.progess);

        ObjectAnimator ani1 = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f);
        ObjectAnimator aniX = ObjectAnimator.ofFloat(logo, "scaleX", 0f, 2.5f);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(logo, "scaleY", 0f, 2.5f);
        ani1.setDuration(1500);
        aniX.setDuration(1500);
        aniY.setDuration(1500);

        AnimatorSet set = new AnimatorSet();
        set.play(ani1).with(aniX).with(aniY);
        set.start();

//        doStartProgressBar();
        aniprogress();
        SharedPreferences sharedPreferences = this.getSharedPreferences("User_File", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("Email", "");
        edit.putString("Password", "");
        edit.commit();
        //
        currentDate = dateFormat.format(date);
        DAO dao = new DAO(this);
        List<order> orderList = dao.getOrder("select * from order_tb");
        //
        for (order x : orderList) {
            if (date.compareTo(x.getBooking_date()) >= 0 && date.compareTo(x.getReturn_date()) <= 0) {

            }
        }
    }

    private void doStartProgressBar() {
        final int MAX = 100;
        this.progressBar.setMax(MAX);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                    }
                });
                for (int i = 0; i < MAX; i++) {
                    final int progress = i + 1;

                    // Do something (Download, Upload, Update database,..)
                    SystemClock.sleep(50); // Sleep 50 milliseconds.

                    // Update interface.
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progress);
                            if (progress == MAX) {
                                Toast.makeText(StartActivity.this, "Chào Mừng", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                                        finish();
                                    }
                                }, 2000);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void aniprogress() {
//        if(android.os.Build.VERSION.SDK_INT >= 11){
        // will update the "progress" propriety of seekbar until it reaches progress
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 30000);
        animation.setDuration(3000); // 0.5 second
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        }.start();
//        }
    }
}
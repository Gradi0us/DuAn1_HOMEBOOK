package com.example.home_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView logo;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logo = findViewById(R.id.logo);
        progressBar = findViewById(R.id.progess);

        ObjectAnimator ani1 = ObjectAnimator.ofFloat(logo,"alpha",0f,1f);
        ObjectAnimator aniX = ObjectAnimator.ofFloat(logo,"scaleX",0f,2.5f);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(logo,"scaleY",0f,2.5f);
        ani1.setDuration(1500);
        aniX.setDuration(1500);
        aniY.setDuration(1500);

        AnimatorSet set = new AnimatorSet();
        set.play(ani1).with(aniX).with(aniY);
        set.start();

        doStartProgressBar();

    }

    private void doStartProgressBar()  {
        final int MAX = 100;
        this.progressBar.setMax(MAX);

        Thread thread = new Thread(new Runnable()  {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                    }
                });
                for( int i =0; i < MAX; i++) {
                    final int progress = i + 1;

                    // Do something (Download, Upload, Update database,..)
                    SystemClock.sleep(50); // Sleep 50 milliseconds.

                    // Update interface.
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progress);
                            if(progress == MAX)  {
                                Toast.makeText(StartActivity.this,"Chào Mừng",Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(StartActivity.this,BottomNavActivity.class));
                                        finish();
                                    }
                                },2000);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }
}
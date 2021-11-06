package com.safwa.zwadatkom;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.safwa.zwadatkom.Home.MainActivity;
import com.safwa.zwadatkom.OnBoard.OnBoardActivity;
import com.safwa.zwadatkom.Utils.GlobalPrefrencies;
import com.safwa.zwadatkom.usercontrol.LoginSignupActivity;

public class SplashActivity extends AppCompatActivity {


    ImageView imgLogo;
    GlobalPrefrencies globalPrefrencies;
    LinearProgressIndicator progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgLogo = findViewById(R.id.imgLogo);
        progressBar = findViewById(R.id.pb_loading);
        layout = findViewById(R.id.layBg);
        globalPrefrencies=new GlobalPrefrencies(this);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_anim_fall_down);
        imgLogo.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                changeBackground();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();

            }
        }).start();
    }

    ConstraintLayout layout;

    @SuppressLint({"ResourceAsColor", "RestrictedApi"})
    private void changeBackground() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int from = ContextCompat.getColor(SplashActivity.this, R.color.white);
                final int to = ContextCompat.getColor(SplashActivity.this, R.color.greenbackground);

                ValueAnimator anim = new ValueAnimator();
                anim.setIntValues(from, to);
                anim.setEvaluator(new ArgbEvaluator());
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        layout.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
                    }
                });

                anim.setDuration(5000);
                anim.start();
            }
        });
        // imgLogo.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);


    }

    private void doWork() {
        for (int progress = 1; progress <= 100; progress += 1) {
            try {
                Thread.sleep(50);  // 5 seconds
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        if(globalPrefrencies.getIsFirstOpeen()){
            Intent intent = new Intent(SplashActivity.this, OnBoardActivity.class);
            startActivity(intent);
            finish();
        }else {
            if (globalPrefrencies.getLoginStatus()) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
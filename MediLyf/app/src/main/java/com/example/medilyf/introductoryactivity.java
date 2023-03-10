package com.example.medilyf;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class introductoryactivity extends AppCompatActivity {

    ImageView logo,bb;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductoryactivity);

        logo = findViewById(R.id.logo);
        bb = findViewById(R.id.background);
        lottieAnimationView = findViewById(R.id.mainanim);

        bb.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(2600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2600).setDuration(1000).setStartDelay(4000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(introductoryactivity.this, OnBoardingActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }
}
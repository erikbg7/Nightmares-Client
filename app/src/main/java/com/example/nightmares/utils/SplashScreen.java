package com.example.nightmares.utils;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nightmares.R;
import com.example.nightmares.activities.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView img_loading_icon;
    Boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(!stop){
            img_loading_icon=(ImageView) findViewById(R.id.iv_loading_icon);
            img_loading_icon.setBackgroundResource(R.drawable.loading);
            AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_icon.getBackground();
            frameAnimation.start();
        }

    }

    @Override
    protected void onResume() {
        stop = true;
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);

            }
        },1300);
    }


}

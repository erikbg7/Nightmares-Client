package com.example.nightmares.activities;

//import dsa.UPC.Nightgame.UnityPlayerActivity;
//import unityplayer.UnityActtivity;
//import com.example.*;
//import com.unity3d.player.UnityPlayerActivity;
//import dsa.UPC.Nightgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.nightmares.R;

public class MenuActivity extends AppCompatActivity {

    Context myContext = this;
    Button btnExit;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnStart = (Button) findViewById(R.id.btn_start);
        btnExit = (Button) findViewById(R.id.btn_exit);



        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(myContext, UnityPlayerActivity.class);
                //startActivity(intent);

            }
        });


    }
}

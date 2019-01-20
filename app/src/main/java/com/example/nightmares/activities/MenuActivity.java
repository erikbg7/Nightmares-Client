package com.example.nightmares.activities;


import com.upc.nightgame.UnityPlayerActivity;


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
    Button btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnStart = (Button) findViewById(R.id.btn_start);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnScore = (Button) findViewById(R.id.btn_top_scorers);

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, ScorersActivity.class);
                startActivity(intent);
            }
        });


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
                Intent intent = new Intent(myContext, UnityPlayerActivity.class);
                startActivity(intent);
            }
        });


    }
}

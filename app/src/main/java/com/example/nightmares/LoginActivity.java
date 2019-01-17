package com.example.nightmares;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nightmares.models.LogSignTemplate;
import com.example.nightmares.services.NightAPI;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final String BaseURL = "http://10.0.2.2:8080/dsaApp/";
    private NightAPI nightAPI;

    EditText name, password;
    TextView tvRegister;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nightAPI = retrofit.create(NightAPI.class);

        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogSignTemplate credentials = new LogSignTemplate(name.toString(), password.toString());
                logIn(credentials);
                /*de momento sin comprobar si ese usuario esta en base datos solo y unicamente acceder cualquiera solo con darle boton*/
                //Intent i2 = new Intent(LoginActivity.this, MenuActivity.class);
                //startActivity(i2);

            }
        });
        
    }




    //Devuelve el contexto, lo utilizamos desde los Callbacks
    public Context getContext() {
        return (Context)this;
    }

    // CALL para hacer un POST de login con las credenciales del usuario
    private void logIn(LogSignTemplate credentials){
        //LogSignTemplate credentials = new LogSignTemplate("Tfue", "123");
        Call<LogSignTemplate> callNewTrack = nightAPI.authorize(credentials);
        callNewTrack.enqueue(new Callback<LogSignTemplate>() {
            @Override
            public void onResponse(Call<LogSignTemplate> call, Response<LogSignTemplate> response) {
                if(response.isSuccessful()){
                    Log.d("QuestionsCallback", "////////////////////////////////////   SUCCESFUL LOGIN !!!!!!!!!!!!!!!  /////////////////////////////////////");
                   // Toasty.success(getContext(), "Succes, welcome.", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.d("QuestionsCallback", "////////////////////////////////////   NO SUCCESFUL RESPONSE   /////////////////////////////////////");
                    Toasty.error(getContext(), "Incorrect username or password.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<LogSignTemplate> call, Throwable t) {
                Log.d("QuestionsCallback", "////////////////////////////////////////   ERROR   /////////////////////////////////");
               // Toasty.error(getContext(), "Error while validating..", Toast.LENGTH_SHORT, true).show();
                t.printStackTrace();
            }
        });
    }
}

package com.example.nightmares.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nightmares.R;
import com.example.nightmares.activities.MenuActivity;
import com.example.nightmares.models.LogSignTemplate;
import com.example.nightmares.services.NightAPI;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private final String BaseURL = "http://10.0.2.2:8080/dsaApp/";
    private NightAPI nightAPI;
    EditText name, password;
    Button btnSignup;
    CheckBox policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signUp);
        policy = (CheckBox) findViewById(R.id.termsofservice);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nightAPI = retrofit.create(NightAPI.class);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toasty.error(getContext(), "Fill the fields first.", Toast.LENGTH_SHORT, true).show();
            }
            else{
                if(policy.isChecked()){
                    LogSignTemplate credentials = new LogSignTemplate(name.getText().toString(), password.getText().toString());
                    signUp(credentials);
                }
                else{
                    Toasty.error(getContext(), "Accept privacy policy, please.", Toast.LENGTH_SHORT, true).show();
                }
            }
            }
        });

    }


    //Devuelve el contexto, lo utilizamos desde los Callbacks
    public Context getContext() {
        return (Context)this;
    }

    // CALL para hacer un POST de registro con las credenciales del usuario
    private void signUp(LogSignTemplate credentials){

        Log.d("QuestionsCallback", "////////////////////////////////////" +credentials.getName() + " "+credentials.getPassword() +"!!!!!!!!!!!!!!!  /////////////////////////////////////");

        Call<LogSignTemplate> callNewTrack = nightAPI.register(credentials);
        callNewTrack.enqueue(new Callback<LogSignTemplate>() {
            @Override
            public void onResponse(Call<LogSignTemplate> call, Response<LogSignTemplate> response) {
                if(response.isSuccessful()){
                    Log.d("QuestionsCallback", "////////////////////////////////////   SUCCESFUL SIGN UP !!!!!!!!!!!!!!!  /////////////////////////////////////");
                    Toasty.success(getContext(), "Succesfuly registred.", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.d("QuestionsCallback", "////////////////////////////////////   NO SUCCESFUL RESPONSE   /////////////////////////////////////");
                    Toasty.error(getContext(), "Name already in use.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<LogSignTemplate> call, Throwable t) {
                Log.d("QuestionsCallback", "////////////////////////////////////////   ERROR SIGNING   /////////////////////////////////");
                Toasty.error(getContext(), "Error while signing.", Toast.LENGTH_SHORT, true).show();
                t.printStackTrace();
            }
        });
    }

}

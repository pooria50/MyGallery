package com.example.pooria.mygallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_user_name,edt_user_password;
    private TextView txt_register;
    private MyGalleryAPI mService;
    private Button btn_Login;
    public static Integer UserId ;
    private String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getControls();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendreQuest();
                Intent intent = new Intent(LoginActivity.this, ShowListMainPostsActivity.class);
                startActivity(intent);
            }
        });

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void sendreQuest() {
        mService = Common.getAPI();
        String user_name = edt_user_name.getText().toString();
        String user_password = edt_user_password.getText().toString();
        mService.performUserLogin(user_name,user_password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User body = response.body();
                //Log.d("activity : ", body.toString());
                Log.d("activity : ", "id Of User is : " + String.valueOf(body.getId()));
               // Log.d("activity : ", body.getUser_password());
               // Log.d("activity : ", body.getUser_email());
                UserId = body.getId();
                Log.d("activity : ", String.valueOf(UserId));


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    private void getControls() {
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_user_password = findViewById(R.id.edt_user_password);
        txt_register = findViewById(R.id.txt_register);
        btn_Login = findViewById(R.id.btn_Login);
    }
}

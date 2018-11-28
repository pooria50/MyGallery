package com.example.pooria.mygallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText edt_name,edt_user_name,edt_user_email,edt_user_password ;
    private Button btn_Register;
    private MyGalleryAPI mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getControls();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDates();
            }
        });

    }

    private void registerDates() {
        String name = edt_name.getText().toString();
        String user_name = edt_user_name.getText().toString();
        String user_email = edt_user_email.getText().toString();
        String user_password = edt_user_password.getText().toString();
        mService = Common.getAPI();
        mService.performRegistration(name,user_name,user_password,user_email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User body = response.body();
                Log.d("activity", body.toString());
                Toast.makeText(RegisterActivity.this, "Register Compeleted :) ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("error : ", t.toString());
            }
        });

    }

    private void getControls() {
        edt_name = findViewById(R.id.edt_name);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_user_email = findViewById(R.id.edt_user_email);
        edt_user_password = findViewById(R.id.edt_user_password);
        btn_Register = findViewById(R.id.btn_Register);
    }
}

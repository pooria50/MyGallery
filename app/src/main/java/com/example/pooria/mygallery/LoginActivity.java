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
import com.orhanobut.hawk.Hawk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_user_name,edt_user_password;
    private TextView txt_register;
    private MyGalleryAPI mService;
    private Button btn_Login;
    public static Integer UserId ;
    public static String Image_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getControls();
        Hawk.init(this).build();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendreQuest();
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
        mService.performUserLogin(edt_user_name.getText().toString(), edt_user_password.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    UserId = response.body().getId();
                    Image_User = response.body().getImage_user();
                    Log.d("tags", String.valueOf(UserId));
                    Log.d("tags", response.body().getImage_user());
                    Toast.makeText(LoginActivity.this, "Login Ook", Toast.LENGTH_SHORT).show();
                    Hawk.put("user_id", UserId);
                    Hawk.put("image_user", Image_User);
                    Intent intent = new Intent(LoginActivity.this, ShowListMainPostsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id", String.valueOf(UserId));
                    intent.putExtra("person", bundle);
                    startActivity(intent);
                } else if (response.body().getResponse().equals("failed")) {
                    Log.d("tags", "Error : (( ");
                    Toast.makeText(LoginActivity.this, "Username Or Password Is Wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

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

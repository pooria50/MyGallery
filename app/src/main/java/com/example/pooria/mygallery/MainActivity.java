package com.example.pooria.mygallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test", "First");
        Log.d("token", FirebaseInstanceId.getInstance().getToken());
        Intent intent = new Intent(MainActivity.this, MainSendPostActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Chekced", Toast.LENGTH_SHORT).show();
        //test

    }
}

package com.example.pooria.mygallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {



    //http://localhost/MyGallery/FireBaase_PushNotifica
    // tions/PushNotifications.php?
    // send_notification&token=f3P3Xp4GwS4:APA91bGK0b5z7upJVwrW6
    // 4WI2GpKsHWi31NHQN4xskQLMfOc4iioYJIJrvaEQg1uy
    // b5F7pP6fE2CTl0-hq0fbMpRf2qRmp6eYPciPtmyd86x
    // yWsqtoNrk4WopWXHRCreCh8cG1fLv8vT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("token", FirebaseInstanceId.getInstance().getToken());
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

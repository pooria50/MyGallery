package com.example.pooria.mygallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pooria.mygallery.Fragment.LoginCourseFragment;
import com.example.pooria.mygallery.Fragment.RegisterCourseFragment;
import com.example.pooria.mygallery.Fragment.WelcomeFragment;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Retrofit.RetrofitClient;
import com.example.pooria.mygallery.Utils.Common;
import com.example.pooria.mygallery.Utils.PrefConfig;

public class TestActivity extends AppCompatActivity implements LoginCourseFragment.onLoggingFormActivityListener,WelcomeFragment.onLogoutInterface {

    public static PrefConfig prefConfig;
    public static MyGalleryAPI mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        prefConfig = new PrefConfig(this);
        mService = RetrofitClient.getClient(Common.BASE_URL).create(MyGalleryAPI.class);
        if (findViewById(R.id.fragment_container)!= null) {
            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new WelcomeFragment()).commit();

            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginCourseFragment()).commit();

            }
        }
    }

    @Override
    public void performReister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterCourseFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();

    }


    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginCourseFragment()).commit();

    }
}






















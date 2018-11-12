package com.example.pooria.mygallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pooria.mygallery.Utils.PrefConfig;

public class TestActivity extends AppCompatActivity implements LoginCourseFragment.onLoggingFormActivityListener {

    public static PrefConfig prefConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        prefConfig = new PrefConfig(this);
        if (findViewById(R.id.fragment_container)!= null) {
            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new RegisterCourseFragment()).commit();

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
    public void performLoin(String name) {
        prefConfig.writeName(name);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginCourseFragment()).commit();

    }
}






















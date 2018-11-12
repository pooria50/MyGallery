package com.example.pooria.mygallery.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.pooria.mygallery.R;

public class PrefConfig {
    SharedPreferences sharedPreferences;
    Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);

    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);

    }

    public void writeName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name), name);
        editor.commit();
    }

    public boolean readName() {
        return Boolean.parseBoolean(sharedPreferences.getString(context.getString(R.string.pref_user_name), "User"));
    }

    public void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}

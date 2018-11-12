package com.example.pooria.mygallery;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginCourseFragment extends Fragment  {

    private TextView Regtext;
    private EditText edt_user_name, edt_user_password;
    private Button btn_Login;
    private MyGalleryAPI mService;
    onLoggingFormActivityListener onLoggingFormActivityListener;
    public LoginCourseFragment() {
        // Required empty public constructor
    }


    public interface onLoggingFormActivityListener {
        public void performReister();
        public void performLoin(String name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_course, container, false);
        Regtext = view.findViewById(R.id.Regtext);
        edt_user_name = view.findViewById(R.id.edt_user_name);
        edt_user_password = view.findViewById(R.id.edt_user_password);
        btn_Login = view.findViewById(R.id.btn_Login);
        Regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onLoggingFormActivityListener = (LoginCourseFragment.onLoggingFormActivityListener) activity;

    }

    private void performLogin() {
        String username = edt_user_name.getText().toString();
        String userpassword = edt_user_password.getText().toString();
        mService = Common.getAPI();
        mService.performUserLogin(username,userpassword).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    TestActivity.prefConfig.writeLoginStatus(true);
                    onLoggingFormActivityListener.performLoin(response.body().getName());
                } else if (response.body().getResponse().equals("failed")) {
                    TestActivity.prefConfig.displayToast("Logging Failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}

package com.example.pooria.mygallery.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pooria.mygallery.Adapter.MainPostsAdapter;
import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.R;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.SendPostActivity;
import com.example.pooria.mygallery.ShowListMainPostsActivity;
import com.example.pooria.mygallery.TestActivity;
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
    private String id;
    onLoggingFormActivityListener onLoggingFormActivityListener;
    public Activity context;
    public LoginCourseFragment() {
        // Required empty public constructor
    }


    public interface onLoggingFormActivityListener {
        public void performReister();
        public void performLogin(String name);
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

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

            }
        });


        Regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoggingFormActivityListener.performReister();
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
                Log.d("abcd", "come");
                Log.d("abcd", response.toString());
                if (response.body().getResponse().equals("ok")) {
                    TestActivity.prefConfig.writeLoginStatus(true);
                    onLoggingFormActivityListener.performLogin(response.body().getName());
                    id = String.valueOf(response.body().getId());
                    Log.d("abcd", response.body().getName());
                    Log.d("abcd", String.valueOf(response.body().getId()));
                    context = getActivity();
                    Intent intent = new Intent(context, ShowListMainPostsActivity.class);
                    intent.putExtra("key", id);
                    startActivity(intent);
                } else if (response.body().getResponse().equals("failed")) {
                    TestActivity.prefConfig.displayToast("Logging Failed");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("abcd", t.toString());
            }
        });
    }
}

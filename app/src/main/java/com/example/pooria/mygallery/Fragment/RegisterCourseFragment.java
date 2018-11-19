package com.example.pooria.mygallery.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.R;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.TestActivity;
import com.example.pooria.mygallery.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterCourseFragment extends Fragment {
    private EditText edt_name,edt_user_name, edt_user_password;
    private Button btn_Login;
    public static MyGalleryAPI mService;
    public RegisterCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_course, container, false);
        edt_name = view.findViewById(R.id.edt_name);
        edt_user_name = view.findViewById(R.id.edt_user_name);
        edt_user_password = view.findViewById(R.id.edt_user_password);
        btn_Login = view.findViewById(R.id.btn_Login);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });

        return view;
    }

    public void Registration() {
        String name = edt_name.getText().toString();
        String user_name = edt_user_name.getText().toString();
        String user_password = edt_user_password.getText().toString();
        Call<User> call = TestActivity.mService.performRegistration(name, user_name, user_password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    TestActivity.prefConfig.displayToast("Compeleted : ) ");
                }
                else if (response.body().getResponse().equals("exist")) {
                    TestActivity.prefConfig.displayToast("User Existed, Im Sorry :( ");
                }
                else if (response.body().getResponse().equals("error")) {
                    TestActivity.prefConfig.displayToast("Something wrong !, Im Sorry :( ");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}

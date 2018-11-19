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
import android.widget.TextView;

import com.example.pooria.mygallery.R;
import com.example.pooria.mygallery.ShowListMainPostsActivity;
import com.example.pooria.mygallery.TestActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private Button btn_logout,btn_go;
    private TextView txt_name;
    private Activity context;
    onLogoutInterface onLogoutInterface;
    public WelcomeFragment() {
        // Required empty public constructor
    }


    public interface onLogoutInterface {
        public void logoutPerformed();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        txt_name = view.findViewById(R.id.txt_name);
        txt_name.setText(" Welcome "+ TestActivity.prefConfig.readName());
        Log.d("namess", TestActivity.prefConfig.readName());
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_go = view.findViewById(R.id.btn_go);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = getActivity();
                Intent intent = new Intent(context, ShowListMainPostsActivity.class);
                startActivity(intent);
            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutInterface.logoutPerformed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onLogoutInterface = (WelcomeFragment.onLogoutInterface) activity;

    }
}

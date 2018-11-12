package com.example.pooria.mygallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.mygallery.Model.Main_Posts_Model;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSendPostActivity extends AppCompatActivity implements DatePickerFragment.OnFragmentInteractionListener,AdapterView.OnItemSelectedListener{

    private Button main_btn_saveDates;//private DatePicker datePicker;
    private ImageView img_main_send_post;
    private EditText main_edittext_caption,main_edittext_min,main_edittext_max,main_edittext_hazine;
    private Spinner spinner_dasteha,spinner_dastresi;
    private MyGalleryAPI mService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String[] dasteha={"علمی","فرهنگی","تاریخی","ورزشی","سیاسی"};
    private String[] dastresi={"شخصی","عمومی"};
    private String ChooseSpinnerDaste = "";
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    private DatePicker datePicker;
    private Integer y;
    private Integer d;
    private Integer m;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_send_post);
        getControlsById();




        spinner_dasteha.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dasteha);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spinner_dasteha.setAdapter(aa);


        /*main_btn_setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "date Picker");
            }
            });*/



        main_btn_saveDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMainPosts();
            }
        });


        img_main_send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        datePicker = findViewById(R.id.dataPicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(MainSendPostActivity.this, "Select is : " + view.getYear() + " /// "
                                + (view.getMonth() + 1) + " /// " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();
                        y = view.getYear();
                        d = view.getDayOfMonth();
                        m = (view.getMonth() + 1);
                        Log.d("abcdef", y + "-" + m + "-" + d);
                    }
                });
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            datePicker.init(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Toast.makeText(MainSendPostActivity.this, "Select is : " + view.getYear() + " /// "
                                    + (view.getMonth() + 1) + " /// " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }




    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void getControlsById() {
        img_main_send_post = findViewById(R.id.img_main_send_post);
        main_edittext_caption = findViewById(R.id.main_edittext_caption);
        main_edittext_min = findViewById(R.id.main_edittext_min);
        main_edittext_max = findViewById(R.id.main_edittext_max);
        spinner_dasteha = findViewById(R.id.spinner);
        main_btn_saveDates = findViewById(R.id.main_btn_saveDates);
        main_edittext_hazine = findViewById(R.id.main_edittext_hazine);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), dasteha[position], Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), dastresi[position], Toast.LENGTH_LONG).show();
        String item = parent.getItemAtPosition(position).toString();
        ChooseSpinnerDaste = item;
        Toast.makeText(this, ChooseSpinnerDaste, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    

    public void sendMainPosts() {
        String link = imageToString();
        String caption = main_edittext_caption.getText().toString();
        mService = Common.getAPI();
        mService.getMainPosts(link, caption,
                "Public", Integer.valueOf(main_edittext_min.getText().toString()),
                Integer.valueOf(main_edittext_max.getText().toString())
                , ChooseSpinnerDaste.toString()
                , Integer.valueOf(main_edittext_hazine.getText().toString()), y + "-" + m + "-" + d)
                .enqueue(new Callback<Main_Posts_Model>() {
                    @Override
                    public void onResponse(Call<Main_Posts_Model> call, Response<Main_Posts_Model> response) {
                        response.toString();
                        Toast.makeText(MainSendPostActivity.this, " Compeleted ", Toast.LENGTH_SHORT).show();
                        Log.d("retro", response.toString());
                    }

                    @Override
                    public void onFailure(Call<Main_Posts_Model> call, Throwable t) {
                        //Toast.makeText(MainSendPostActivity.this, " Failed !", Toast.LENGTH_SHORT).show();
                        Log.d("retro", t.getMessage().toString());
                    }
                });

    }



    private String imageToString(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imgByte = outputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                img_main_send_post.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.pooria.mygallery;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pooria.mygallery.Model.SendPostsModel;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendPostActivity extends AppCompatActivity {

    private Button btn_send,btn_show_Posts;
    private EditText edt_caption;
    private ImageView img_post;
    private BottomSheetDialog mBottomSheetDialog;
    private Intent intent;
    private Bitmap bitmap;
    private static final int IMG_REQUEST = 777;
    private MyGalleryAPI mService;
    private String id, daste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_post);
        intent = getIntent();
        id = intent.getStringExtra("id");
        daste = intent.getStringExtra("daste");
        getControls();
        //View bottomSheet = findViewById(R.id.framelayout_bottom_sheet);
        final View bottomSheetLayout = getLayoutInflater().inflate(R.layout.activity_alert_dialog_posts, null);
        (bottomSheetLayout.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                Toast.makeText(SendPostActivity.this, "Salam : )", Toast.LENGTH_SHORT).show();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPosts();
            }
        });

        img_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        btn_show_Posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendPostActivity.this, ShowListPostActivity.class);
                startActivity(intent);
            }
        });
    }


    public void sendPosts() {
        final String user_id = LoginActivity.UserId.toString();
        Toast.makeText(this, user_id, Toast.LENGTH_SHORT).show();
        String link = imageToString();
        mService = Common.getAPI();
        mService.sendPosts(link,edt_caption.getText().toString(),
                Integer.valueOf(id),daste, Integer.valueOf(user_id)).enqueue(new Callback<SendPostsModel>() {
            @Override
            public void onResponse(Call<SendPostsModel> call, Response<SendPostsModel> response) {
                response.toString();
                Log.d("retro", "OOOOOOOkkkkk"+response.toString());
                Log.d("retro", "oo"+user_id);

            }

            @Override
            public void onFailure(Call<SendPostsModel> call, Throwable t) {
                Log.d("retro", "OOOOOOOkkkkk"+user_id);
            }
        });
    }

    private void getControls() {
        btn_send = findViewById(R.id.btn_send);
        edt_caption = findViewById(R.id.edt_caption);
        img_post = findViewById(R.id.img_post);
        btn_show_Posts = findViewById(R.id.btn_show_Posts);
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
                img_post.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

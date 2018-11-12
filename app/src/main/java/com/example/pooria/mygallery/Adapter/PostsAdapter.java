package com.example.pooria.mygallery.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pooria.mygallery.Model.SendPostsModel;
import com.example.pooria.mygallery.Model.User_Post_Comments;
import com.example.pooria.mygallery.Model.User_Post_Likes;
import com.example.pooria.mygallery.R;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostAdapterViewHolder> {

     Context context;
     List<SendPostsModel> list;
     private MyGalleryAPI mService;

    public PostsAdapter(Context context, List<SendPostsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PostsAdapter.PostAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_post_items_list, null);
        return new PostAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsAdapter.PostAdapterViewHolder holder, final int i) {
        String url = Common.BASE_URL + "User_Posts/" + list.get(i).getImg_url();
        Picasso.with(context).load(url)
                .into(holder.img_post_item);
        Log.d("inform", Common.BASE_URL+list.get(i).getImg_url());

        holder.img_post_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("informs", "id "+String.valueOf(list.get(i).getId()));

            }
        });


        holder.img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer post_id = list.get(i).getId();
                Integer like = 1;
                mService = Common.getAPI();
                mService.sendLike(post_id,like).enqueue(new Callback<User_Post_Likes>() {
                    @Override
                    public void onResponse(Call<User_Post_Likes> call, Response<User_Post_Likes> response) {
                        response.toString();
                        Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User_Post_Likes> call, Throwable t) {
                        Log.d("informs", t.toString());

                    }
                });

            }
        });



        holder.img_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer post_id = list.get(i).getId();
                String comment = holder.edt_comment.getText().toString();
                mService = Common.getAPI();
                mService.sendComments(post_id,comment).enqueue(new Callback<User_Post_Comments>() {
                    @Override
                    public void onResponse(Call<User_Post_Comments> call, Response<User_Post_Comments> response) {
                        response.toString();
                        Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<User_Post_Comments> call, Throwable t) {
                        Log.d("informs", t.toString());
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PostAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_like,img_post_item,img_comment;
        public EditText edt_comment;
        public PostAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            img_like = itemView.findViewById(R.id.img_like);
            img_post_item = itemView.findViewById(R.id.img_post_item);
            edt_comment = itemView.findViewById(R.id.edt_comment);
            img_comment = itemView.findViewById(R.id.img_comment);
        }
    }
}

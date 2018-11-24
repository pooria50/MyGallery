package com.example.pooria.mygallery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pooria.mygallery.Model.ReadMainPostsModel;
import com.example.pooria.mygallery.R;
import com.example.pooria.mygallery.SendPostActivity;
import com.example.pooria.mygallery.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainPostsAdapter extends RecyclerView.Adapter<MainPostsAdapter.MainPostsAdapterHolder> {

    Context context;
    List<ReadMainPostsModel>list ;

    public MainPostsAdapter(Context context, List<ReadMainPostsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainPostsAdapter.MainPostsAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_show_main_pos_itemst, null);
        return new MainPostsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPostsAdapter.MainPostsAdapterHolder holder, final int i) {
        Picasso.with(context).load(Common.BASE_URL+list.get(i).getImg_url())
                .into(holder.img_post);
        holder.txt_caption.setText(list.get(i).getCaption());
        holder.txt_date.setText(list.get(i).getTarikh());
        holder.txt_daste.setText(list.get(i).getDaste());
        holder.txt_price.setText(list.get(i).getHazine());
        holder.txt_minimum.setText(list.get(i).getMinimum());
        holder.txt_maximum.setText(list.get(i).getMaximum());

        Log.d("inform", String.valueOf(list.get(i).getId()));
        Log.d("inform", list.get(i).getDaste());


        holder.img_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("inform", String.valueOf(list.get(i).getId()).toString());
                String id = String.valueOf(list.get(i).getId());
                String daste = list.get(i).getDaste();
                Intent intent = new Intent(context, SendPostActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("daste",daste);
                context.startActivity(intent);
                Log.d("inform", "id: "+id);
                Log.d("inform", "daste: "+daste);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MainPostsAdapterHolder extends RecyclerView.ViewHolder {

        ImageView img_post;
        TextView txt_caption,txt_date,txt_daste,txt_price,txt_minimum,txt_maximum;

        public MainPostsAdapterHolder(@NonNull View itemView) {
            super(itemView);
            img_post = itemView.findViewById(R.id.img_post);
            txt_caption = itemView.findViewById(R.id.txt_caption);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_daste = itemView.findViewById(R.id.txt_daste);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_minimum = itemView.findViewById(R.id.txt_minimum);
            txt_maximum = itemView.findViewById(R.id.txt_maximum);

        }
    }
}

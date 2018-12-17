package com.example.pooria.mygallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.mygallery.Adapter.MainPostsAdapter;
import com.example.pooria.mygallery.Model.Main_Posts_Model;
import com.example.pooria.mygallery.Model.ReadMainPostsModel;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;
import com.orhanobut.hawk.Hawk;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowListMainPostsActivity extends AppCompatActivity {
    private RecyclerView recycler_main_posts;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MyGalleryAPI mService;
    private List<ReadMainPostsModel> list = new ArrayList<>();
    private MainPostsAdapter adapter;
    public  Integer user_id;
    private Intent intent;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_main_posts);
        getControls();

        String img = Hawk.get("image_user");

        View view = nav.inflateHeaderView(R.layout.header);
        CircleImageView img_user = view.findViewById(R.id.img_user);
        TextView user_name = view.findViewById(R.id.user_name);
        //img_user.setImageResource(Integer.parseInt(img));
        user_name.setText("test");


        Integer user_id = Hawk.get("user_id");
        String image_user = Hawk.get("image_user");
        Log.d("absd", "onCreate: "+user_id);
        Log.d("absd", "onCreate: "+image_user);

        mtoggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.dbbbbbb:
                        Toast.makeText(ShowListMainPostsActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }

        });



        intent = getIntent();
        Bundle b = intent.getBundleExtra("person");
        this.user_id = Integer.valueOf(b.getString("user_id"));
        Log.d("user_id", String.valueOf(this.user_id));

        mService = Common.getAPI();


        getPosts();

    }

    private void getControls() {
        recycler_main_posts = findViewById(R.id.recycler_main_posts);
        mDrawerLayout = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav);
    }

    private void getPosts() {
        mService.ShowMainPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<ReadMainPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ReadMainPostsModel> main_posts_models) {
                        for (int i = 0; i < main_posts_models.size(); i++) {

                           /* Log.d("inform", String.valueOf(main_posts_models.get(i).getId()));
                            Log.d("inform", main_posts_models.get(i).getHazine());*/
                            adapter = new MainPostsAdapter(ShowListMainPostsActivity.this, list);
                            recycler_main_posts.setHasFixedSize(true);
                            recycler_main_posts.setLayoutManager(new LinearLayoutManager(ShowListMainPostsActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            ReadMainPostsModel readMainPostsModel = new ReadMainPostsModel();
                            readMainPostsModel.setCaption(main_posts_models.get(i).getCaption());
                            readMainPostsModel.setTarikh(main_posts_models.get(i).getTarikh());
                            readMainPostsModel.setDaste(main_posts_models.get(i).getDaste());
                            readMainPostsModel.setHazine(main_posts_models.get(i).getHazine());
                            readMainPostsModel.setMinimum(main_posts_models.get(i).getMinimum());
                            readMainPostsModel.setMaximum(main_posts_models.get(i).getMaximum());
                            readMainPostsModel.setImg_url(main_posts_models.get(i).getImg_url());
                            readMainPostsModel.setId(main_posts_models.get(i).getId());
                            list.add(readMainPostsModel);


                        }
                        adapter.notifyDataSetChanged();
                        recycler_main_posts.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

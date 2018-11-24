package com.example.pooria.mygallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pooria.mygallery.Adapter.MainPostsAdapter;
import com.example.pooria.mygallery.Model.Main_Posts_Model;
import com.example.pooria.mygallery.Model.ReadMainPostsModel;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

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
    public String user_id;
    private Intent intent;

    public static Integer user_idInteger = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_main_posts);
        getControls();




        intent = getIntent();
        this.user_id = intent.getStringExtra("key");
        Log.d("user_id",  String.valueOf(this.user_id));


        mService = Common.getAPI();


        getPosts();
    }

    private void getControls() {
        recycler_main_posts = findViewById(R.id.recycler_main_posts);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert:
                Intent intent = new Intent(ShowListMainPostsActivity.this, MainSendPostActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

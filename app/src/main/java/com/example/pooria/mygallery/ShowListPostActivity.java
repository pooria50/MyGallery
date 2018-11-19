package com.example.pooria.mygallery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pooria.mygallery.Adapter.PostsAdapter;
import com.example.pooria.mygallery.Model.SendPostsModel;
import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShowListPostActivity extends AppCompatActivity {

    private RecyclerView recycler_posts;


    private MyGalleryAPI mService;
    private PostsAdapter postsAdapter;
    private List<SendPostsModel> models = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getControls();
        mService = Common.getAPI();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    private void getControls() {
        recycler_posts = findViewById(R.id.recycler_posts);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.siasi:
                Toast.makeText(this, "Siasi", Toast.LENGTH_SHORT).show();
                GetPostsBySiasi();
                return true;
            case R.id.varzeshi:
                Toast.makeText(this, "Varzeshi", Toast.LENGTH_SHORT).show();
                getPostsByVarzeshi();
                return true;
            case R.id.farhangi:
                Toast.makeText(this, "Farhangi", Toast.LENGTH_SHORT).show();
                getPostsByFarhanqi();
                return true;
            case R.id.elmi:
                Toast.makeText(this, "Elmi", Toast.LENGTH_SHORT).show();
                getPostsByElmi();
                return true;
            case R.id.tarikhi:
                Toast.makeText(this, "Tarihki", Toast.LENGTH_SHORT).show();
                getPostsByTarikhi();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getPostsByFarhanqi() {
        models.clear();
        mService.getpostsByFarhangi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<SendPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SendPostsModel> sendPostsModels) {
                        for (int i = 0 ; i <sendPostsModels.size();i++) {
                            postsAdapter = new PostsAdapter(ShowListPostActivity.this, models);
                            recycler_posts.setHasFixedSize(true);
                            recycler_posts.setLayoutManager(new LinearLayoutManager(ShowListPostActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            SendPostsModel model = new SendPostsModel();
                            model.setCaption(sendPostsModels.get(i).getCaption());
                            model.setId(sendPostsModels.get(i).getId());
                            model.setImg_url(sendPostsModels.get(i).getImg_url());
                            model.setPost_daste(sendPostsModels.get(i).getPost_daste());
                            Log.d("test", model.getCaption() +"  " + model.getPost_daste() + "   "+ model.getImg_url());

                            models.add(model);

                        }
                        postsAdapter.notifyDataSetChanged();
                        recycler_posts.setAdapter(postsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.getMessage().toString());

                    }
                });
    }

    public void GetPostsBySiasi() {
        models.clear();
        mService.getpostsBySiasi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<SendPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SendPostsModel> sendPostsModels) {
                        for (int i = 0 ; i <sendPostsModels.size();i++) {
                            postsAdapter = new PostsAdapter(ShowListPostActivity.this, models);
                            recycler_posts.setHasFixedSize(true);
                            recycler_posts.setLayoutManager(new LinearLayoutManager(ShowListPostActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            SendPostsModel model = new SendPostsModel();
                            model.setCaption(sendPostsModels.get(i).getCaption());
                            model.setId(sendPostsModels.get(i).getId());
                            model.setImg_url(sendPostsModels.get(i).getImg_url());
                            model.setPost_daste(sendPostsModels.get(i).getPost_daste());
                            Log.d("test", model.getCaption() +"  " + model.getPost_daste() + "   "+ model.getImg_url());

                            models.add(model);
                        }
                        postsAdapter.notifyDataSetChanged();
                        recycler_posts.setAdapter(postsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.getMessage().toString());

                    }
                });
    }

    public void getPostsByVarzeshi() {
        models.clear();

        mService.getpostsByVarzeshi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<SendPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SendPostsModel> sendPostsModels) {
                        for (int i = 0 ; i <sendPostsModels.size();i++) {
                            postsAdapter = new PostsAdapter(ShowListPostActivity.this, models);
                            recycler_posts.setHasFixedSize(true);
                            recycler_posts.setLayoutManager(new LinearLayoutManager(ShowListPostActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            SendPostsModel model = new SendPostsModel();
                            model.setCaption(sendPostsModels.get(i).getCaption());
                            model.setId(sendPostsModels.get(i).getId());
                            model.setImg_url(sendPostsModels.get(i).getImg_url());
                            model.setPost_daste(sendPostsModels.get(i).getPost_daste());
                            Log.d("test", model.getCaption() +"  " + model.getPost_daste() + "   "+ model.getImg_url());

                            models.add(model);
                        }
                        postsAdapter.notifyDataSetChanged();
                        recycler_posts.setAdapter(postsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.getMessage().toString());

                    }
                });
    }

    public void getPostsByElmi() {
        models.clear();

        mService.getpostsByElmi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<SendPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SendPostsModel> sendPostsModels) {
                        for (int i = 0 ; i <sendPostsModels.size();i++) {
                            postsAdapter = new PostsAdapter(ShowListPostActivity.this, models);
                            recycler_posts.setHasFixedSize(true);
                            recycler_posts.setLayoutManager(new LinearLayoutManager(ShowListPostActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            SendPostsModel model = new SendPostsModel();
                            model.setCaption(sendPostsModels.get(i).getCaption());
                            model.setId(sendPostsModels.get(i).getId());
                            model.setImg_url(sendPostsModels.get(i).getImg_url());
                            model.setPost_daste(sendPostsModels.get(i).getPost_daste());
                            Log.d("test", model.getCaption() +"  " + model.getPost_daste() + "   "+ model.getImg_url());

                            models.add(model);
                        }
                        postsAdapter.notifyDataSetChanged();
                        recycler_posts.setAdapter(postsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.getMessage().toString());

                    }
                });
    }

    public void getPostsByTarikhi() {
        models.clear();

        mService.getpostsByTarikhi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<SendPostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SendPostsModel> sendPostsModels) {
                        for (int i = 0 ; i <sendPostsModels.size();i++) {
                            postsAdapter = new PostsAdapter(ShowListPostActivity.this, models);
                            recycler_posts.setHasFixedSize(true);
                            recycler_posts.setLayoutManager(new LinearLayoutManager(ShowListPostActivity.this,
                                    LinearLayoutManager.VERTICAL, false));
                            SendPostsModel model = new SendPostsModel();
                            model.setCaption(sendPostsModels.get(i).getCaption());
                            model.setId(sendPostsModels.get(i).getId());
                            model.setImg_url(sendPostsModels.get(i).getImg_url());
                            model.setPost_daste(sendPostsModels.get(i).getPost_daste());
                            Log.d("test", model.getCaption() +"  " + model.getPost_daste() + "   "+ model.getImg_url());

                            models.add(model);
                        }
                        postsAdapter.notifyDataSetChanged();
                        recycler_posts.setAdapter(postsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.getMessage().toString());

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}

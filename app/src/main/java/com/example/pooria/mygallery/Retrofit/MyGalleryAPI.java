package com.example.pooria.mygallery.Retrofit;

import com.example.pooria.mygallery.Model.Main_Posts_Model;
import com.example.pooria.mygallery.Model.ReadMainPostsModel;
import com.example.pooria.mygallery.Model.SendPostsModel;
import com.example.pooria.mygallery.Model.User;
import com.example.pooria.mygallery.Model.User_Post_Comments;
import com.example.pooria.mygallery.Model.User_Post_Likes;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyGalleryAPI {


    @POST("User_Posts/User_Informations/register.php")
    Call<User> performRegistration(@Query("name") String Name,
                                   @Query("user_name") String UserName,
                                   @Query("user_password") String UserPassword);


    @GET("User_Posts/User_Informations/login.php")
    Call<User> performUserLogin(@Query("user_name") String UserName,
                                @Query("user_password") String UserPassword);




    @FormUrlEncoded
    @POST("MainGetDatePosts.php")
    Call<Main_Posts_Model> getMainPosts(@Field("img_url") String img_url,
                                        @Field("caption") String caption,
                                        @Field("dastresi") String dastresi,
                                        @Field("maximum") Integer maximum,
                                        @Field("minimum") Integer minimum,
                                        @Field("daste") String daste,
                                        @Field("hazine") Integer hazine,
                                        @Field("tarikh") String tarikh);



    @GET("ShowListOfMainPosts.php")
    Single<List<ReadMainPostsModel>> ShowMainPosts();


    @FormUrlEncoded
    @POST("User_Posts/SaveLikes.php")
    Call<User_Post_Likes> sendLike(@Field("post_id") Integer post_id,
                                   @Field("likes") Integer likes);


    @FormUrlEncoded
    @POST("User_Posts/SaveComments.php")
    Call<User_Post_Comments> sendComments(@Field("post_id") Integer post_id,
                                          @Field("comment") String comment);


    @FormUrlEncoded
    @POST("User_Posts/GetPosts.php")
    Call<SendPostsModel> sendPosts(@Field("img_url") String img_url,
                                   @Field("caption") String caption,
                                   @Field("post_id") Integer post_id,
                                   @Field("post_daste") String post_daste);


   @GET("User_Posts/ShowListOfPostsByFarhangi.php")
    Single<List<SendPostsModel>> getpostsByFarhangi();


    @GET("User_Posts/ShowListOfPostsByVarzeshi.php")
    Single<List<SendPostsModel>> getpostsByVarzeshi();

    @GET("User_Posts/ShowListOfPostsByTarikhi.php")
    Single<List<SendPostsModel>> getpostsByTarikhi();

    @GET("User_Posts/ShowListOfPostsByElmi.php")
    Single<List<SendPostsModel>> getpostsByElmi();

    @GET("User_Posts/ShowListOfPostsBySiasi.php")
    Single<List<SendPostsModel>> getpostsBySiasi();




}

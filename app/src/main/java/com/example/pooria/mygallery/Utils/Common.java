package com.example.pooria.mygallery.Utils;

import com.example.pooria.mygallery.Retrofit.MyGalleryAPI;
import com.example.pooria.mygallery.Retrofit.RetrofitClient;

public class Common {

    public static final String BASE_URL = "http://192.168.43.254/MyGallery/Main_Posts/";

    public static MyGalleryAPI getAPI() {
        return RetrofitClient.getClient(BASE_URL).create(MyGalleryAPI.class);
    }
}

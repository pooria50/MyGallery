package com.example.pooria.mygallery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerPersonModel {
    @SerializedName("MAX(likes)")
    @Expose
    private String mAXLikes;
    @SerializedName("post_id")
    @Expose
    private String postId;

    public String getMAXLikes() {
        return mAXLikes;
    }

    public void setMAXLikes(String mAXLikes) {
        this.mAXLikes = mAXLikes;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}

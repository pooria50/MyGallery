package com.example.pooria.mygallery.Model;

public class SendPostsModel {
    private String img_url,caption, post_daste;
    private Integer post_id,id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPost_daste() {
        return post_daste;
    }

    public void setPost_daste(String post_daste) {
        this.post_daste = post_daste;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

}

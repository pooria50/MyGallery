package com.example.pooria.mygallery.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    @SerializedName("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

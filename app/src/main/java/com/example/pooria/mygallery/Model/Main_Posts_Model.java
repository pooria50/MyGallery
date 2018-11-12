package com.example.pooria.mygallery.Model;

import android.widget.DatePicker;

import java.util.Date;

public class Main_Posts_Model {
    public String img_url,caption,dastresi,daste;
    public String date;
    private String tarikh;
    public Integer maximum, minimum,hazine;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHazine() {
        return hazine;
    }

    public void setHazine(Integer hazine) {
        this.hazine = hazine;
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

    public String getDastresi() {
        return dastresi;
    }

    public void setDastresi(String dastresi) {
        this.dastresi = dastresi;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public String getDaste() {
        return daste;
    }

    public void setDaste(String daste) {
        this.daste = daste;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }
}

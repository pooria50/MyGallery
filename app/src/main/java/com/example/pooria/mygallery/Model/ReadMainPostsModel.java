package com.example.pooria.mygallery.Model;

public class ReadMainPostsModel {
    private String img_url;

    private Integer id;

    private String daste;

    private String hazine;

    private String minimum;

    private String maximum;

    private String caption;

    private String tarikh;

    private String dastresi;

    public String getImg_url ()
    {
        return img_url;
    }

    public void setImg_url (String img_url)
    {
        this.img_url = img_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDaste ()
    {
        return daste;
    }

    public void setDaste (String daste)
    {
        this.daste = daste;
    }

    public String getHazine ()
    {
        return hazine;
    }

    public void setHazine (String hazine)
    {
        this.hazine = hazine;
    }

    public String getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (String minimum)
    {
        this.minimum = minimum;
    }

    public String getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (String maximum)
    {
        this.maximum = maximum;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public String getTarikh ()
    {
        return tarikh;
    }

    public void setTarikh (String tarikh)
    {
        this.tarikh = tarikh;
    }

    public String getDastresi ()
    {
        return dastresi;
    }

    public void setDastresi (String dastresi)
    {
        this.dastresi = dastresi;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [img_url = "+img_url+", id = "+id+", daste = "+daste+", hazine = "+hazine+", minimum = "+minimum+", maximum = "+maximum+", caption = "+caption+", tarikh = "+tarikh+", dastresi = "+dastresi+"]";
    }
}

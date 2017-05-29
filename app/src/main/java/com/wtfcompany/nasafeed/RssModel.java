package com.wtfcompany.nasafeed;

import android.graphics.Bitmap;

/**
 * Created by Ijin on 23.04.2017.
 */

public class RssModel {

    private String date;
    private String title;
    private String description;
    private Bitmap picture;
    private String imageUrl;

    public RssModel(){
        date = "";
        title = "";
        description = "";
        imageUrl = "";
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getPicture() {
        return picture;
    }


    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}

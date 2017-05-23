package com.wtfcompany.nasafeed;

import android.graphics.Bitmap;

/**
 * Created by Ijin on 23.04.2017.
 */

public class RssModel {

    public String date;
    public String title;
    public String description;
    public Bitmap picture;
    public String ImageUrl;

    public RssModel(){
        date = "";
        title = "";
        description = "";
        ImageUrl = "";

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

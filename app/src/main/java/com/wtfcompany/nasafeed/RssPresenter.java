package com.wtfcompany.nasafeed;

import android.content.Context;

/**
 * Created by Ijin on 24.05.2017.
 */

public class RssPresenter {
    private ImageOfTheDayView view;
    private Context ctx;

    public RssPresenter(ImageOfTheDayView view, Context ctx){
        this.ctx = ctx;
        this.view = view;
    }

    public void loadRss(){
        view.showProgress();
        new RssLoader(this).execute(URLS.ImageOfTheDay);
    }

    public void onLoadedRss(RssModel model){
        view.stopProgress();
        view.showData(model);
    }

    public void showProgress(){
        view.showProgress();
    }
    public void stopProgress(){
        view.stopProgress();
    }
}
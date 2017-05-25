package com.wtfcompany.nasafeed;

import android.content.Context;

/**
 * Created by Ijin on 24.05.2017.
 */

public class RssPresenter {
    private ImageOfTheDayView view;

    public RssPresenter(ImageOfTheDayView view){
        this.view = view;
    }

    public void loadRss(){
        new RssLoader(this).execute(URLS.ImageOfTheDay);
    }

    public void onLoadedRss(RssModel model){
        view.showData(model);
    }

    public void showProgress(){
        view.showProgress();
    }
    public void stopProgress(){
        view.stopProgress();
    }


}

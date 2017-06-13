package com.wtfcompany.nasafeed.presenter;

import com.wtfcompany.nasafeed.AsyncLoader;
import com.wtfcompany.nasafeed.view.ImageOfTheDayView;
import com.wtfcompany.nasafeed.URLS;
import com.wtfcompany.nasafeed.model.RSSItem;

/**
 * Created by Ijin on 24.05.2017.
 */

public class RssPresenter implements ImageOfTheDayPresenter{
    private ImageOfTheDayView view;

    public RssPresenter(ImageOfTheDayView view){
        this.view = view;
    }

    public void loadRss(){
        view.showProgress();
        new AsyncLoader(this).execute(URLS.ImageOfTheDay);
    }

    @Override
    public void onLoadedRss(RSSItem model){
        view.stopProgress();
        view.showData(model);
    }
}
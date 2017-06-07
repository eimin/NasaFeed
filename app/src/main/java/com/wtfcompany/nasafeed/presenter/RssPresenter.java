package com.wtfcompany.nasafeed.presenter;

import com.wtfcompany.nasafeed.RssLoader;
import com.wtfcompany.nasafeed.view.ImageOfTheDayView;
import com.wtfcompany.nasafeed.URLS;
import com.wtfcompany.nasafeed.model.ImageOfTheDayModel;

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
        new RssLoader(this).execute(URLS.ImageOfTheDay);
    }

    @Override
    public void onLoadedRss(ImageOfTheDayModel model){
        view.stopProgress();
        view.showData(model);
    }
}
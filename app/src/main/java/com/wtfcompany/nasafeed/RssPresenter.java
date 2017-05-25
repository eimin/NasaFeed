package com.wtfcompany.nasafeed;

import android.content.Context;

/**
 * Created by Ijin on 24.05.2017.
 */

public class RssPresenter {
    private Context view;

    public RssPresenter(Context view){
        this.view = view;
    }

    public void loadRss(){
        new RssLoader().execute(URLS.ImageOfTheDay);
    }











    private void parseRss() {

    }


}

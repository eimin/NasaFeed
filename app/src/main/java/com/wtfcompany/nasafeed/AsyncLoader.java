package com.wtfcompany.nasafeed;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.wtfcompany.nasafeed.model.RSSItem;
import com.wtfcompany.nasafeed.presenter.ImageOfTheDayPresenter;
import com.wtfcompany.nasafeed.presenter.RssPresenter;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ijin on 23.04.2017.
 */

public class AsyncLoader extends AsyncTask<String, Void, RSSItem> {
    private ImageOfTheDayPresenter presenter;

    public AsyncLoader(RssPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    protected RSSItem doInBackground(String...urlParams) {
        RSSLoader loader = new RSSLoader();
        RSSParser parser = new RSSParser();
        RSSItem imageOfTheDay = new RSSItem();
        String rss = "";
        Bitmap bitmap = null;

        String tag = "try";
        try {
            rss = loader.loadRSS(urlParams[0]);
            Log.d(tag, "RSS: " + rss);
        } catch (IOException e) {e.printStackTrace();}

        try {
            imageOfTheDay = parser.parse(rss);
        } catch (ParserConfigurationException  | IOException e) {
            e.printStackTrace();
        }

        try {
            bitmap = loader.loadImage(imageOfTheDay.getImageUrl());
        } catch (ExecutionException | InterruptedException e) {e.printStackTrace();}

        imageOfTheDay.setImage(bitmap);
        return imageOfTheDay;
    }


    @Override
    protected void onPostExecute  (RSSItem imageOfTheDay) {
        presenter.onLoadedRss(imageOfTheDay);
    }
}
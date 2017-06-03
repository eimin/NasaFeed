package com.wtfcompany.nasafeed;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.wtfcompany.nasafeed.model.ImageOfTheDayModel;
import com.wtfcompany.nasafeed.presenter.RssPresenter;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ijin on 23.04.2017.
 */

public class RssLoader extends AsyncTask<String, Void, ImageOfTheDayModel> {
    private RssPresenter presenter;

    public RssLoader(RssPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    protected ImageOfTheDayModel doInBackground(String...urlParams) {
        StringBuilder builder = new StringBuilder();
        String result = "";

        //load xml
        try {
            URL url = new URL(urlParams[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            result = builder.toString();
        } catch(Exception e) {e.printStackTrace();}

        //parse xml
        ImageOfTheDayParser parser = null;
        try {
            parser = new ImageOfTheDayParser();
            InputSource source = new InputSource(new StringReader(result));
            SAXParserFactory.newInstance().newSAXParser().parse(source, parser);
        } catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}

        //load image
        Bitmap bitmap = null;
        String url = parser.data.getImageUrl();
        try {
            Context context = CurrentContext.getInstance().getContext();
            bitmap = Glide.with(context).load(url).asBitmap().into(500,500).get();
        } catch (InterruptedException | ExecutionException e) {e.printStackTrace();}

        parser.data.setPicture(bitmap);
        return parser.data;
    }


    @Override
    protected void onPostExecute  (ImageOfTheDayModel model) {
        presenter.onLoadedRss(model);
    }
}
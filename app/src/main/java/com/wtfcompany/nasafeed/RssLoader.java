package com.wtfcompany.nasafeed;

import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ijin on 23.04.2017.
 */

public class RssLoader extends AsyncTask<String, Void, RssModel> {

    @Override
    protected RssModel doInBackground(String...urlParams) {
        MainActivity.Handler contentHandler = null;
        String result = "";
        try {
            URL url = new URL(urlParams[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            result = builder.toString();

            try {
                contentHandler = new MainActivity.Handler();
                SAXParserFactory.newInstance().newSAXParser().parse((new InputSource(new StringReader(result))), contentHandler);

                Log.d(TAG, "PostExecute. RSSModel. Title: " + contentHandler.data.getTitle());
                Log.d(TAG, "PostExecute. RSSModel. Date: " + contentHandler.data.getDate());
                Log.d(TAG, "PostExecute. RSSModel. Description: " + contentHandler.data.getDescription());
                Log.d(TAG, "PostExecute. RSSModel. ImageLink: " + contentHandler.imageUrl);

            } catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}

        } catch(Exception e) {e.printStackTrace();}


        return contentHandler.data;

    }

    @Override
    protected void onPreExecute() {
        //показать лоадер
    }

    @Override
    protected void onPostExecute  (RssModel model) {
        //спрятать лоадер

        Glide.with(MainActivity.this).load(model.ImageUrl).into(image);

    }
}

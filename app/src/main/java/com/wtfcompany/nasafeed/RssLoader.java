package com.wtfcompany.nasafeed;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
    private final static String TAG = "try";
    private RssPresenter presenter;

    public RssLoader(RssPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    protected RssModel doInBackground(String...urlParams) {
        ImageOfTheDayParser imageOfTheDayParser = null;
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
        try {
            imageOfTheDayParser = new ImageOfTheDayParser();
            SAXParserFactory.newInstance().newSAXParser().parse((new InputSource(new StringReader(result))), imageOfTheDayParser);
        } catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}

        //load image
        Bitmap bitmap = null;
        String url = imageOfTheDayParser.data.getImageUrl();
        try {
            InputStream stream = (InputStream) new URL(url).getContent();
            bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
        } catch (Exception e) {
            return null;
        }
        Log.d("try", "bitmap is null: " + (bitmap == null));

        imageOfTheDayParser.data.setPicture(bitmap);
        return imageOfTheDayParser.data;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute  (RssModel model) {
        presenter.onLoadedRss(model);
    }
}

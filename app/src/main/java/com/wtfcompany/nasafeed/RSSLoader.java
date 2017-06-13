package com.wtfcompany.nasafeed;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ijin on 11.06.2017.
 */

public class RSSLoader {

    public String loadRSS(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    public Bitmap loadImage(String url) throws ExecutionException, InterruptedException {
        Context context = CurrentContext.getInstance().getContext();
        return Glide.with(context).load(url).asBitmap().into(500,500).get();
    }

}

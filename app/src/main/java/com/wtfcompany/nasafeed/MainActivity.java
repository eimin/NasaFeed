package com.wtfcompany.nasafeed;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pub_date)
    TextView date;
    @BindView(R.id.image)
    ImageView image;

    private final static String TAG = "try";

    public class Handler extends DefaultHandler {

        private String currentElement;
        public RssModel data;
        public String imageUrl = "";
        private boolean foundItem = false;

        private final static String itemTag = "item";
        private final static String descriptionTag = "description";
        private final static String dateTag = "pubDate";
        private final static String imageLinkTag = "enclosure";
        private final static String titleTag = "title";


        public Handler() {
            super();
            data = new RssModel();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equals(itemTag)) {
                Log.d(TAG, "Start item. Element is: " + qName);
                foundItem = true;
            }

            if(qName.equals(imageLinkTag)){
                data.ImageUrl += attributes.getValue("url");
            }

            currentElement = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if(qName.equals(itemTag)){
                Log.d(TAG, "End item. Element is: " + qName);
                foundItem = false;
                throw new SAXException("Exit parsing");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            Log.d(TAG, "Characters. Value: " + value);
            if(foundItem){
                switch (currentElement){
                    case titleTag:
                        Log.d(TAG, "Found title: " + value);
                        data.title +=  value;
                        break;
                    case descriptionTag:
                        Log.d(TAG, "Found description: " + value);
                        data.description += value;
                        break;
                    case dateTag:
                        Log.d(TAG, "Found date: " + value);
                        data.date += value;
                        break;
                }
            }
        }
    }

    public class RssLoader extends AsyncTask<String, Void, RssModel> {

        @Override
        protected RssModel doInBackground(String...urlParams) {
            Handler contentHandler = null;
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
                    contentHandler = new Handler();
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
            description.setText(model.getDescription());
            date.setText(model.getDate());
            title.setText(model.getTitle());
            Glide.with(MainActivity.this).load(model.ImageUrl).into(image);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new RssLoader().execute(URLS.ImageOfTheDay);

    }
}

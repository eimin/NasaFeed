package com.wtfcompany.nasafeed;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Ijin on 24.05.2017.
 */

public class RssParser extends DefaultHandler {

    private String currentElement;
    public RssModel data;
    public String imageUrl = "";
    private boolean foundItem = false;

    private final static String itemTag = "item";
    private final static String descriptionTag = "description";
    private final static String dateTag = "pubDate";
    private final static String imageLinkTag = "enclosure";
    private final static String titleTag = "title";


    public RssParser() {
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
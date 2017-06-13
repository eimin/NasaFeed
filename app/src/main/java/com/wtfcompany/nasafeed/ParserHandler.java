package com.wtfcompany.nasafeed;

import android.util.Log;

import com.wtfcompany.nasafeed.model.RSSItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Ijin on 24.05.2017.
 */

public class ParserHandler extends DefaultHandler {

    private String currentElement;
    private RSSItem data;
    private boolean foundItem = false;

    private final static String itemTag = "item";
    private final static String descriptionTag = "description";
    private final static String dateTag = "pubDate";
    private final static String imageLinkTag = "enclosure";
    private final static String titleTag = "title";

    public ParserHandler() {
        super();
        data = new RSSItem();
    }

    public RSSItem getData() {
        return data;
    }

    public void setData(RSSItem data) {
        this.data = data;
    }
    private String tag = "try";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Log.d(tag, "startElement");

        if(qName.equals(itemTag)) {
            foundItem = true;
        }

        if(qName.equals(imageLinkTag)){
            String addImageUrl = data.getImageUrl() + attributes.getValue("url");
            data.setImageUrl(addImageUrl);
        }
        currentElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Log.d(tag, "endElement");
        if(qName.equals(itemTag)){
            foundItem = false;
            throw new SAXException("Exit parsing");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Log.d(tag, "characters");
        String value = new String(ch, start, length);
        if(foundItem){
            switch (currentElement){
                case titleTag:
                    String addTitle = data.getTitle() +  value;
                    data.setTitle(addTitle);
                    break;
                case descriptionTag:
                    String addDescription = data.getDescription() + value;
                    data.setDescription(addDescription);
                    break;
                case dateTag:
                    String addDate= data.getDate() + value;
                    data.setDate(addDate);
                    break;
            }
        }
    }
}
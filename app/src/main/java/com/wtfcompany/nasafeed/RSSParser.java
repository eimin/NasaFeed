package com.wtfcompany.nasafeed;

import android.util.Log;

import com.wtfcompany.nasafeed.model.RSSItem;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ijin on 11.06.2017.
 */

public class RSSParser {

    public RSSItem parse (String rss) throws ParserConfigurationException, IOException {
        ParserHandler handler = new ParserHandler();
        InputSource source = new InputSource(new StringReader(rss));

        try {
            SAXParserFactory.newInstance().newSAXParser().parse(source, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return handler.getData();
    }
}

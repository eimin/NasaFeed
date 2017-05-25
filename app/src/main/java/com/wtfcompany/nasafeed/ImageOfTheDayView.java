package com.wtfcompany.nasafeed;

/**
 * Created by Ijin on 25.05.2017.
 */

public interface ImageOfTheDayView {
     void showData(RssModel model);
     void showProgress();
     void stopProgress();
}

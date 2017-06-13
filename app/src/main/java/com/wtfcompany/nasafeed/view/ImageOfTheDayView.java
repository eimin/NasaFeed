package com.wtfcompany.nasafeed.view;

import com.wtfcompany.nasafeed.model.RSSItem;

/**
 * Created by Ijin on 25.05.2017.
 */

public interface ImageOfTheDayView {
     void showData(RSSItem model);
     void showProgress();
     void stopProgress();
}

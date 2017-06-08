package com.wtfcompany.nasafeed.view;

import com.wtfcompany.nasafeed.model.ImageOfTheDayModel;

/**
 * Created by Ijin on 25.05.2017.
 */

public interface ImageOfTheDayView {
     void showData(ImageOfTheDayModel model);
     void showProgress();
     void stopProgress();
}

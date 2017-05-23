package com.wtfcompany.nasafeed;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ijin on 24.01.2017.
 */

public class ResetDisplay {

    public void reset(AppCompatActivity activity, String _title, String _description, String _pubDate, Bitmap _image){
        TextView title = (TextView)activity.findViewById(R.id.title);
        TextView description = (TextView)activity.findViewById(R.id.description);
        TextView pubDate = (TextView)activity.findViewById(R.id.pub_date);
        ImageView image = (ImageView)activity.findViewById(R.id.image);

        title.setText(_title);
        description.setText(_description);
        pubDate.setText(_pubDate);
        image.setImageBitmap(_image);
    }
}

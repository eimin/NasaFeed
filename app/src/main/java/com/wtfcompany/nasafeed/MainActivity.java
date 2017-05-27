package com.wtfcompany.nasafeed;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ImageOfTheDayView{

    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pub_date)
    TextView date;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private final static String TAG = "try";

    @Override
    public void showData(RssModel model) {
        description.setText(model.getDescription());
        date.setText(model.getDate());
        title.setText(model.getTitle());
        //image.setImageBitmap(model.getPicture());

        Glide.with(this).load(model.getImageUrl()).into(image);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RssPresenter presenter = new RssPresenter(this, this);
        presenter.loadRss();
    }
}
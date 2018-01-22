package com.ibm.examples.yashsoni.applaunchdemo.activities;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.examples.yashsoni.applaunchdemo.commons.AppCommons;
import com.ibm.examples.yashsoni.applaunchdemo.models.NewsFeedModel;

public class NewsDetailActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NewsFeedModel feedModel;

    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvAuthor;
    private ImageView ivNewsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        feedModel = getIntent().getExtras().getParcelable(AppCommons.NEWS_FEED_DETAILS);
        initViews();
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appBar);
        toolbar = appBarLayout.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        tvTitle = findViewById(R.id.tv_news_detail_title);
        tvAuthor = findViewById(R.id.tv_news_detail_author);
        tvDesc = findViewById(R.id.tv_news_detail_desc);

        tvTitle.setText(feedModel.title);
        tvDesc.setText(feedModel.desc);
        tvAuthor.setText(feedModel.author);
    }
}

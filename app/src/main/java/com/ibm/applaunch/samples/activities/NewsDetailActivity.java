package com.ibm.applaunch.samples.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.applaunch.samples.commons.AppCommons;
import com.ibm.applaunch.samples.models.NewsFeedModel;
import com.ibm.mobile.applaunch.android.api.AppLaunch;
import com.ibm.mobile.applaunch.android.api.AppLaunchException;

public class NewsDetailActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NewsFeedModel feedModel;

    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvAuthor;
    private ImageView ivNewsImage;
    private ImageView ivShare;
    private LinearLayout llMediaControls;

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
        toolbar.setBackgroundColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        tvTitle = findViewById(R.id.tv_news_detail_title);
        ivNewsImage = findViewById(R.id.iv_news_detail_image);
        if(feedModel.imageUrl != null && !feedModel.imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(feedModel.imageUrl)
                    .into(ivNewsImage);
        }
        tvAuthor = findViewById(R.id.tv_news_detail_author);
        tvDesc = findViewById(R.id.tv_news_detail_desc);
        llMediaControls = findViewById(R.id.ll_media_controls);

        if (feedModel.isAudioAvailable) {
            tvDesc.setVisibility(View.GONE);
        } else {
            llMediaControls.setVisibility(View.GONE);
        }

        tvTitle.setText(feedModel.title);
        tvDesc.setText(feedModel.desc);
        String author = "by " +
                ((feedModel.author == null || feedModel.author.equalsIgnoreCase("null"))
                        ? "Anonymous"
                        : feedModel.author);
        tvAuthor.setText(author);

        ivShare = findViewById(R.id.iv_share_news);
        ivShare.setVisibility(View.GONE);

        try {
            boolean enableShareIcon = Boolean.valueOf(AppLaunch.getInstance().getPropertyOfFeature("_8cfi0fymn", "_bmggkv9qj"));
            if (enableShareIcon) {
                ivShare.setVisibility(View.VISIBLE);
                ivShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, feedModel.title);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, feedModel.desc);
                        startActivity(Intent.createChooser(sharingIntent, "Share this news with"));
                    }
                });
            }
        } catch (AppLaunchException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sign_out) {
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(AppCommons.LOGGED_IN_USER);
            editor.apply();

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

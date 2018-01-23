package com.ibm.examples.yashsoni.applaunchdemo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        tvTitle = findViewById(R.id.tv_news_detail_title);
        tvAuthor = findViewById(R.id.tv_news_detail_author);
        tvDesc = findViewById(R.id.tv_news_detail_desc);
        llMediaControls = findViewById(R.id.ll_media_controls);

        if(feedModel.isAudioAvailable){
            tvDesc.setVisibility(View.GONE);
        } else {
            llMediaControls.setVisibility(View.GONE);
        }

        tvTitle.setText(feedModel.title);
        tvDesc.setText(feedModel.desc);
        tvAuthor.setText(feedModel.author);
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
            editor.commit();

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

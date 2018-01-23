package com.ibm.examples.yashsoni.applaunchdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.examples.yashsoni.applaunchdemo.adapters.NewsFeedRecyclerViewAdapter;
import com.ibm.examples.yashsoni.applaunchdemo.commons.AppCommons;
import com.ibm.examples.yashsoni.applaunchdemo.interfaces.OnItemClickListener;
import com.ibm.examples.yashsoni.applaunchdemo.models.NewsFeedModel;
import com.ibm.mobile.applaunch.android.AppLaunchFailResponse;
import com.ibm.mobile.applaunch.android.AppLaunchResponse;
import com.ibm.mobile.applaunch.android.api.AppLaunchListener;

public class NewsFeedActivity extends AppCompatActivity implements AppLaunchListener {

    private static final String TAG = NewsFeedActivity.class.getSimpleName();
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        initViews();
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appBar);
        toolbar = appBarLayout.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        recyclerView = findViewById(R.id.rv_news_feed_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        NewsFeedRecyclerViewAdapter adapter = new NewsFeedRecyclerViewAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(NewsFeedModel feedModel) {
                Intent i;
                if(feedModel.isAudioAvailable){
                    if(isSubscribedUser()) {
                        i = new Intent(NewsFeedActivity.this, NewsDetailActivity.class);
                        i.putExtra(AppCommons.NEWS_FEED_DETAILS, feedModel);
                    } else {
                        i = new Intent(NewsFeedActivity.this, SubscriptionActivity.class);
                    }
                } else {
                    i = new Intent(NewsFeedActivity.this, NewsDetailActivity.class);
                    i.putExtra(AppCommons.NEWS_FEED_DETAILS, feedModel);
                }
                startActivity(i);
            }
        });
        adapter.setDataList(AppCommons.getDataList());
        recyclerView.setAdapter(adapter);
    }

    private boolean isSubscribedUser() {
        int index = 0;
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPref.getString(AppCommons.LOGGED_IN_USER, "").equalsIgnoreCase(AppCommons.users[1])){
            index = 1;
        }
        return AppCommons.userSubscription[index];
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

    @Override
    public void onSuccess(AppLaunchResponse appLaunchResponse) {
        Log.i(TAG, appLaunchResponse.toString());
    }

    @Override
    public void onFailure(AppLaunchFailResponse appLaunchFailResponse) {
        Log.i(TAG, appLaunchFailResponse.toString());
    }
}

package com.ibm.examples.yashsoni.applaunchdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
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
import android.view.View;
import android.widget.ProgressBar;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.examples.yashsoni.applaunchdemo.adapters.NewsFeedRecyclerViewAdapter;
import com.ibm.examples.yashsoni.applaunchdemo.commons.AppCommons;
import com.ibm.examples.yashsoni.applaunchdemo.commons.AppLaunchConstants;
import com.ibm.examples.yashsoni.applaunchdemo.commons.ThemeUtils;
import com.ibm.examples.yashsoni.applaunchdemo.interfaces.OnItemClickListener;
import com.ibm.examples.yashsoni.applaunchdemo.models.NewsFeedModel;
import com.ibm.mobile.applaunch.android.AppLaunchFailResponse;
import com.ibm.mobile.applaunch.android.AppLaunchResponse;
import com.ibm.mobile.applaunch.android.api.AppLaunch;
import com.ibm.mobile.applaunch.android.api.AppLaunchConfig;
import com.ibm.mobile.applaunch.android.api.AppLaunchListener;
import com.ibm.mobile.applaunch.android.api.AppLaunchUser;
import com.ibm.mobile.applaunch.android.api.ICRegion;
import com.ibm.mobile.applaunch.android.api.RefreshPolicy;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFeedActivity extends AppCompatActivity implements AppLaunchListener {

    private static final String TAG = NewsFeedActivity.class.getSimpleName();

    //    Views
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    //    Utils
    private SharedPreferences sharedPref;
    private String userId;
    private String newsApiResult = null;
    private AppLaunchListener listener = this;
    private AppLaunchUser appLaunchUser;
    private PublishSubject<Boolean> showLoader = PublishSubject.create();
    private PublishSubject<Boolean> newsFeedPublishSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        sharedPref = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        userId = sharedPref.getString(AppCommons.LOGGED_IN_USER, "");

        if(isNetworkAvailable()) {
            initAppLaunchSDK();

            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            showLoader
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            progressBar.setVisibility(View.GONE);
                            initViews();
                        }
                    });

            newsFeedPublishSubject.subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    try {
                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url(AppCommons.URL_FOR_NEWS_FEED)
                                .build();

                        Response response = null;
                        response = client.newCall(request).execute();
                        newsApiResult = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onComplete();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    showLoader.onComplete();
                }
            });
        } else {
            initViews();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initAppLaunchSDK() {
        // Initialize the SDK
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID) + "_" + userId;

        AppLaunchConfig appLaunchConfig = new AppLaunchConfig.Builder()
                .eventFlushInterval(10)
                .cacheExpiration(10)
                .fetchPolicy(RefreshPolicy.REFRESH_ON_EVERY_START)
                .deviceId(androidId)
                .build();
        appLaunchUser = new AppLaunchUser.Builder()
                .userId(userId)
                .custom(AppCommons.FIELD_SUBSCRIPTION, getSubscriptionStatus())
                .build();
        AppLaunch.getInstance().init(getApplication(), ICRegion.US_SOUTH, AppLaunchConstants.appGuid, AppLaunchConstants.clientSecret, appLaunchConfig, appLaunchUser, listener);
    }

    private String getSubscriptionStatus() {
        return String.valueOf(isSubscribedUser());
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appBar);
        toolbar = appBarLayout.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(ThemeUtils.getToolbarColor(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        recyclerView = findViewById(R.id.rv_news_feed_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        NewsFeedRecyclerViewAdapter adapter = new NewsFeedRecyclerViewAdapter(this, new OnItemClickListener() {
            @Override
            public void onItemClick(NewsFeedModel feedModel) {
                Intent i;
                if (feedModel.isAudioAvailable) {
                    if (isSubscribedUser()) {
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
        adapter.setDataList(AppCommons.getNewsFeed(newsApiResult));
        recyclerView.setAdapter(adapter);
    }

    private boolean isSubscribedUser() {
        int index = 0;
        if (userId.equalsIgnoreCase(AppCommons.users[1])) {
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
            editor.apply();

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
        ThemeUtils.getThemeFeature(this);
        newsFeedPublishSubject.onNext(true);
    }

    @Override
    public void onFailure(AppLaunchFailResponse appLaunchFailResponse) {
        Log.i(TAG, appLaunchFailResponse.toString());
        newsFeedPublishSubject.onNext(true);
    }
}

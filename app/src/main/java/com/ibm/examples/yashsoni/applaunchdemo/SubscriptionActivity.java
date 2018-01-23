package com.ibm.examples.yashsoni.applaunchdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ibm.mobile.applaunch.android.AppLaunchFailResponse;
import com.ibm.mobile.applaunch.android.AppLaunchResponse;
import com.ibm.mobile.applaunch.android.api.AppLaunchListener;

public class SubscriptionActivity extends AppCompatActivity implements AppLaunchListener {

    private static final String TAG = SubscriptionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        Toolbar toolbar = appBarLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hello");
    }

    @Override
    protected void onResume() {
        super.onResume();
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

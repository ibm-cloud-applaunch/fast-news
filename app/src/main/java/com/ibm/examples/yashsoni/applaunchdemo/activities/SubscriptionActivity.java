package com.ibm.examples.yashsoni.applaunchdemo.activities;

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

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.examples.yashsoni.applaunchdemo.commons.AppCommons;
import com.ibm.examples.yashsoni.applaunchdemo.commons.ThemeUtils;

public class SubscriptionActivity extends AppCompatActivity {

    private static final String TAG = SubscriptionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        Toolbar toolbar = appBarLayout.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(ThemeUtils.getToolbarColor(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        View view = findViewById(R.id.rl_subscription_root);
        view.setBackgroundColor(ThemeUtils.getLightBackgroundColor(this));
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

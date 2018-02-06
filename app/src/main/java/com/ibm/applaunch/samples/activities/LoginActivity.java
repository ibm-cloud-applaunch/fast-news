package com.ibm.applaunch.samples.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.applaunch.samples.commons.AppCommons;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserId, etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserId = findViewById(R.id.user_id);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_sign_in);

        sharedPref = this.getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        if (sharedPref.contains(AppCommons.LOGGED_IN_USER)) {
            Intent i = new Intent(LoginActivity.this, NewsFeedActivity.class);
            startActivity(i);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidUser()) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(AppCommons.LOGGED_IN_USER, etUserId.getText().toString());
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, NewsFeedActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();
                }
            }
        });
    }

    public boolean isValidUser() {
        String userId = etUserId.getText().toString().trim();
        return !userId.isEmpty() &&
                (userId.equalsIgnoreCase(AppCommons.users[0]) || userId.equalsIgnoreCase(AppCommons.users[1]));
    }

}
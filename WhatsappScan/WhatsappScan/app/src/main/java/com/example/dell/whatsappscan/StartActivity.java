package com.example.dell.whatsappscan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dell on 09/01/2018.
 */
public class StartActivity extends AppCompatActivity {

    public boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 5000;
    AsyncTask<Void, Void, Void> mRegisterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();

                if (!mIsBackButtonPressed) {

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started183), true);
                    if(!previouslyStarted)
                    {
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putBoolean(getString(R.string.pref_previously_started183), Boolean.TRUE);
                        edit.commit();
                        Intent i = new Intent(getApplicationContext(), FirstActivity.class);
                        startActivity(i);

                    }
                    else
                    {
                        Intent i = new Intent(getApplicationContext(), FirstActivity.class);
                        startActivity(i);
                    }

                }
            }

        }, SPLASH_DURATION);



    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
        System.exit(0);
    }



}



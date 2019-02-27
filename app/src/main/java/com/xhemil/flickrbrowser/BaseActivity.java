package com.xhemil.flickrbrowser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    public static final String FLICKR_QUERY = "FLICKR_QUERY";
    public static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    void activateToolbar(boolean enableHome){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null){
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null){
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }
        }

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}

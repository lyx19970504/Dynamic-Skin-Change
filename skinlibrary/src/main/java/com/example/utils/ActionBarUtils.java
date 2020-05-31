package com.example.utils;

import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ActionBarUtils {

    private static final String TAG = "ActionBarUtils";

    public static void setActionBarColor(AppCompatActivity activity, int themeColor) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(themeColor));
        }
    }
}

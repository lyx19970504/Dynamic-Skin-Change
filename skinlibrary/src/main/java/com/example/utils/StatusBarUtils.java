package com.example.utils;

import android.app.Activity;

public class StatusBarUtils {

    public static void setStatusBarColor(Activity activity, int skinColor) {
        activity.getWindow().setStatusBarColor(skinColor);
    }
}

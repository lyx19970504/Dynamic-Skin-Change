package com.example.utils;

import android.app.Activity;
import android.content.res.TypedArray;

public class StatusBarUtils {

    public static void setStatusBarColor(Activity activity, int skinColor) {
        activity.getWindow().setStatusBarColor(skinColor);
    }
}

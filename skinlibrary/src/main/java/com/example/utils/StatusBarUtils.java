package com.example.utils;

import android.app.Activity;
import android.content.res.TypedArray;

public class StatusBarUtils {

    public static void setStatusBarColor(Activity activity) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(0, new int[] {
                android.R.attr.statusBarColor
        });
        int color = a.getColor(0, 0);
        activity.getWindow().setStatusBarColor(color);
        a.recycle();
    }
}

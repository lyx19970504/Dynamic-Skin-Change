package com.example.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;

public class NavigationUtils {

    public static void setNavigationColor(Activity activity) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(0, new int[] {
                android.R.attr.statusBarColor
        });
        int color = a.getColor(0, 0);
        activity.getWindow().setNavigationBarColor(color);
        a.recycle();
    }
}

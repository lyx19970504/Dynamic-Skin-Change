package com.example.utils;

import android.app.Activity;

public class NavigationUtils {

    public static void setNavigationColor(Activity activity, int themeColor) {
        activity.getWindow().setNavigationBarColor(themeColor);
    }
}

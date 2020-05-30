package com.example.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;

public class NavigationUtils {

    public static void setNavigationColor(Activity activity, int themeColor) {
        activity.getWindow().setNavigationBarColor(themeColor);
    }
}

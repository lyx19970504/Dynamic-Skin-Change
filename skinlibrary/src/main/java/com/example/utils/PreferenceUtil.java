package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    private static final String PREFERENCE_NAME = "com.example.skin";
    public static final String NIGHT_MODE = "night_mode";

    public static boolean isNight(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(NIGHT_MODE, false);
    }

    public static void setNightMode(Context context, boolean isNight){
        SharedPreferences.Editor editor=
                context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(NIGHT_MODE, isNight);
        editor.apply();
    }
}

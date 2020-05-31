package com.example.dynamic_skin_change;

import android.app.Application;

import com.example.SkinManager;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}

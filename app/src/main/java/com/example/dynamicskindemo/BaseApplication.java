package com.example.dynamicskindemo;

import android.app.Application;

import com.example.SkinManager;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}

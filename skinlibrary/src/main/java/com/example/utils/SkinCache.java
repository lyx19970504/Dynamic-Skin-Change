package com.example.utils;

import android.content.res.Resources;

public class SkinCache {

    public Resources mSkinResource;
    public String mSkinPackageName;

    public SkinCache(Resources skinResource, String skinPackageName) {
        mSkinResource = skinResource;
        mSkinPackageName = skinPackageName;
    }
}

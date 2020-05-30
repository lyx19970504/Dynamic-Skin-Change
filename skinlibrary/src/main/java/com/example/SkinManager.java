package com.example;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.example.utils.SkinCache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SkinManager {

    private static final String TAG = "SkinManager";


    private static SkinManager sSkinManager;
    private Resources appResource;
    private Resources skinResource;
    private boolean isDefaultSkin;
    private String skinPackageName;
    private Application application;
    private static final String ADD_ASSETS_PATH = "addAssetPath";
    private Map<String, SkinCache> cacheSkin;

    public SkinManager(Application application) {
        this.application = application;
        appResource = application.getResources();
        cacheSkin = new HashMap<>();
    }

    public static void init(Application application) {
        if (sSkinManager == null) {
            synchronized (SkinManager.class) {
                if (sSkinManager == null) {
                    sSkinManager = new SkinManager(application);
                }
            }
        }
    }

    public static SkinManager getInstance() {
        if (sSkinManager != null) {
            return sSkinManager;
        }
        return null;
    }

    public void loadSkinResource(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            isDefaultSkin = true;
            return;
        }
        if (cacheSkin.containsKey(skinPath)) {
            isDefaultSkin = false;
            SkinCache skinCache = cacheSkin.get(skinPath);
            if (skinCache != null) {
                skinResource = skinCache.mSkinResource;
                skinPackageName = skinCache.mSkinPackageName;
            }
        }
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetsMethod = assetManager.getClass().getDeclaredMethod(ADD_ASSETS_PATH, String.class);
            addAssetsMethod.setAccessible(true);
            addAssetsMethod.invoke(assetManager, skinPath);

            skinResource = new Resources(assetManager, appResource.getDisplayMetrics(), appResource.getConfiguration());
            skinPackageName = application.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;

            isDefaultSkin = TextUtils.isEmpty(skinPackageName);
            if (!isDefaultSkin) {
                cacheSkin.put(skinPath, new SkinCache(skinResource, skinPackageName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            isDefaultSkin = true;
        }
    }

    private int tryGetSkinResourceId(int originResourceId) {
        if (isDefaultSkin) {
            return originResourceId;
        }
        /**
         * 因为app的资源文件名称与皮肤包中的文件名称相同，因此名称和id也会相同
         */
        Log.d(TAG, "originResourceId: " + originResourceId);
        String resourceName = appResource.getResourceEntryName(originResourceId);
        String resourceType = appResource.getResourceTypeName(originResourceId);

        int skinResourceId = skinResource.getIdentifier(resourceName, resourceType, skinPackageName);
        Log.d(TAG, "skinResourceId: " + skinResourceId);
        isDefaultSkin = skinResourceId == 0;
        return skinResourceId == 0 ? originResourceId : skinResourceId;
    }

    public ColorStateList getColorStateList(int resourceId) {
        int colorId = tryGetSkinResourceId(resourceId);
        return isDefaultSkin ? appResource.getColorStateList(colorId) : skinResource.getColorStateList(colorId);
    }

    public int getColor(int resourceId) {
        int colorId = tryGetSkinResourceId(resourceId);
        return isDefaultSkin ? appResource.getColor(colorId) : skinResource.getColor(colorId);
    }

    private Drawable getDrawable(int resourceId) {
        int drawableId = tryGetSkinResourceId(resourceId);
        return isDefaultSkin ? appResource.getDrawable(drawableId) : skinResource.getDrawable(drawableId);
    }

    public Object getBackgroundOrSrc(int resourceId) {
        String resourceType = appResource.getResourceTypeName(resourceId);
        switch (resourceType) {
            case "color":
                return getColor(resourceId);
            case "mipmap":
            case "drawable":
                return getDrawable(resourceId);
        }
        return null;
    }

    public String getString(int resourceId) {
        int ids = tryGetSkinResourceId(resourceId);
        return isDefaultSkin ? appResource.getString(ids) : skinResource.getString(ids);
    }

    public Typeface getTypeface(int originTypeId) {
        String path = getString(originTypeId);
        if (TextUtils.isEmpty(path)) return Typeface.DEFAULT;
        return isDefaultSkin ? Typeface.createFromAsset(appResource.getAssets(), path) :
                Typeface.createFromAsset(skinResource.getAssets(), path);
    }

    public boolean isDefaultSkin() {
        return isDefaultSkin;
    }
}

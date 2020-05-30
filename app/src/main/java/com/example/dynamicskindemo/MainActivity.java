package com.example.dynamicskindemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import com.example.SkinActivity;

import java.io.File;

public class MainActivity extends SkinActivity {

    private String skinPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //由于Activity本身实现了Factory2接口，因此在这直接设置实现自定义接口
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        skinPath = Environment.getExternalStorageDirectory() + File.separator + "myskin.skin";
    }

    //从资源文件中加载皮肤包
    public void changeSkinFromResource(View view) {
        if (!TextUtils.isEmpty(skinPath)) {
            skinChange(skinPath, R.color.skin_item_color);
        }
    }


    public boolean setIsChangeSkin() {
        return true;
    }


    public void changeSkinToDefault(View view) {
        skinDefault(null, R.color.colorPrimary);
    }
}

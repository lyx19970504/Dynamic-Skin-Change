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

    private String blueSkinPath;
    private String pineSkinPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //由于Activity本身实现了Factory2接口，因此在这直接设置实现自定义接口
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        blueSkinPath = Environment.getExternalStorageDirectory() + File.separator + "blue.skin";
        pineSkinPath = Environment.getExternalStorageDirectory() + File.separator + "pink.skin";
    }

    //从资源文件中加载皮肤包
    public void changeSkinToBlue(View view) {
        if (!TextUtils.isEmpty(blueSkinPath)) {
            skinChange(blueSkinPath, R.color.skin_item_color);
        }
    }

    public void changeSkinToPink(View view){
        if (!TextUtils.isEmpty(pineSkinPath)) {
            skinChange(pineSkinPath, R.color.skin_item_color);
        }
    }


    public boolean setIsChangeSkin() {
        return true;
    }


    public void changeSkinToDefault(View view) {
        skinDefault(null, R.color.colorPrimary);
    }
}

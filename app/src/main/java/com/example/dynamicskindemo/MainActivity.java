package com.example.dynamicskindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.core.CustomLayoutInflater;
import com.example.core.ViewMatch;
import com.example.utils.ActionBarUtils;
import com.example.utils.NavigationUtils;
import com.example.utils.PreferenceUtil;
import com.example.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //由于Activity本身实现了Factory2接口，因此在这直接设置实现自定义接口
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setDayNightMode(AppCompatDelegate.getDefaultNightMode());
        setContentView(R.layout.activity_main);

        boolean isNight = PreferenceUtil.isNight(this);
        if(isNight){
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void dayOrNight(View view) {
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (uiMode){
            case Configuration.UI_MODE_NIGHT_YES:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                PreferenceUtil.setNightMode(this, false);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                PreferenceUtil.setNightMode(this, true);
                break;
        }
    }

    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if(setIsChangeSkin()){
            CustomLayoutInflater mCustomLayoutInflater = new CustomLayoutInflater(context, name, attrs);
            return mCustomLayoutInflater.matchView();
        }
        return super.onCreateView(name, context, attrs);
    }

    private void setDayNightMode(int nightMode){

        getDelegate().setLocalNightMode(nightMode);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            StatusBarUtils.setStatusBarColor(this);
            NavigationUtils.setNavigationColor(this);
            ActionBarUtils.setActionBarColor(this);
        }
        View decorView = getWindow().getDecorView();
        applyForDayNightMode(decorView);
    }

    private void applyForDayNightMode(View view) {
        if(view instanceof ViewMatch){
            ViewMatch viewMatch = (ViewMatch) view;
            viewMatch.skinChange();
        }
        if(view instanceof ViewGroup){
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                applyForDayNightMode(parent.getChildAt(i));
            }
        }
    }
    
    public boolean setIsChangeSkin() {
        return true;
    }
}

package com.example;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import com.example.core.CustomLayoutInflater;
import com.example.core.ViewMatch;
import com.example.utils.ActionBarUtils;
import com.example.utils.NavigationUtils;
import com.example.utils.StatusBarUtils;

public class SkinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
    }

    protected void skinDefault(String skinPath, int skinColor) {
        skinChange(skinPath, skinColor);
    }

    protected void skinChange(String skinPath, int skinColor) {
        SkinManager.getInstance().loadSkinResource(skinPath);

        if (skinColor != 0) {
            int themeColor = SkinManager.getInstance().getColor(skinColor);
            StatusBarUtils.setStatusBarColor(this, themeColor);
            NavigationUtils.setNavigationColor(this, themeColor);
            ActionBarUtils.setActionBarColor(this, themeColor);
        }
        applyForDayNightMode(getWindow().getDecorView());
    }

    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (setIsChangeSkin()) {
            CustomLayoutInflater mCustomLayoutInflater = new CustomLayoutInflater(context, name, attrs);
            return mCustomLayoutInflater.matchView();
        }
        return super.onCreateView(name, context, attrs);
    }

    private void applyForDayNightMode(View view) {
        if (view instanceof ViewMatch) {
            ViewMatch viewMatch = (ViewMatch) view;
            viewMatch.skinChange();
        }
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                applyForDayNightMode(parent.getChildAt(i));
            }
        }
    }

    public boolean setIsChangeSkin() {
        return false;
    }
}

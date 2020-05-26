package com.example.utils;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.core.R;

public class ActionBarUtils {

    private static final String TAG = "ActionBarUtils";

    public static void setActionBarColor(AppCompatActivity activity) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(0, new int[] {
                android.R.attr.colorPrimary
        });
        int color = a.getColor(0, 0);
        a.recycle();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(color));

//            //设置actionBar字体颜色
            Spannable text = new SpannableString(actionBar.getTitle());
            text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.commonTextColor1)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(text);
        }
    }
}

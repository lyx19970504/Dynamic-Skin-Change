package com.example.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AppCompatViewInflater;

import com.example.view.SkinButton;
import com.example.view.SkinImageView;
import com.example.view.SkinLinearLayout;
import com.example.view.SkinRelativeLayout;
import com.example.view.SkinTextView;

public class CustomLayoutInflater extends AppCompatViewInflater {

    private String activityName;
    private AttributeSet activityAttrs;
    private Context mContext;

    public CustomLayoutInflater(Context context, String activityName, AttributeSet activityAttrs) {
        mContext = context;
        this.activityName = activityName;
        this.activityAttrs = activityAttrs;
    }

    public View matchView() {
        View view = null;
        switch (activityName) {
            case "LinearLayout":
                view = new SkinLinearLayout(mContext, activityAttrs);
                break;
            case "TextView":
                view = new SkinTextView(mContext, activityAttrs);
                break;
            case "Button":
                view = new SkinButton(mContext, activityAttrs);
                break;
            case "RelativeLayout":
                view = new SkinRelativeLayout(mContext, activityAttrs);
                break;
            case "ImageView":
                view = new SkinImageView(mContext, activityAttrs);
                break;
        }
        return view;
    }
}

package com.example.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.SkinManager;
import com.example.core.ViewMatch;
import com.example.skinlibrary.R;
import com.example.utils.StorageUtil;

public class SkinLinearLayout extends LinearLayout implements ViewMatch {

    private static final String TAG = "SkinLinearLayout";

    private SparseIntArray mSparseIntArray;
    private static final int[] resourceNames = R.styleable.SkinLinearLayout;

    public SkinLinearLayout(Context context) {
        this(context, null);
    }

    public SkinLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSparseIntArray = StorageUtil.getSparseIntArray(context, attrs, resourceNames, defStyleAttr);
    }


    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinLinearLayout_android_background];
        int backgroundResourceId = mSparseIntArray.get(backgroundKey);
        if (backgroundResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
                setBackgroundDrawable(drawable);
            } else {
                Object backgroundObject = SkinManager.getInstance().getBackgroundOrSrc(backgroundResourceId);
                if (backgroundObject instanceof Integer) {
                    setBackgroundResource((Integer) backgroundObject);
                } else if(backgroundObject instanceof Drawable){
                    Drawable drawable = (Drawable) backgroundObject;
                    setBackgroundDrawable(drawable);
                }
            }
        }
    }
}

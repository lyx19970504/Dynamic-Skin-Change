package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.core.R;
import com.example.core.ViewMatch;

public class SkinLinearLayout extends LinearLayout implements ViewMatch {

    private static final String TAG = "SkinLinearLayout";

    private static final int DEFAULT_VALUE = -1;
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
        mSparseIntArray = new SparseIntArray();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, resourceNames, defStyleAttr, 0);
        for (int i = 0; i < typedArray.length(); i++) {
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            mSparseIntArray.put(resourceNames[i], resourceId);
        }
        typedArray.recycle();
    }


    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinLinearLayout_android_background];
        int backgroundResourceId = mSparseIntArray.get(backgroundKey);
        if(backgroundResourceId > 0){
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
            setBackground(drawable);
        }
    }
}

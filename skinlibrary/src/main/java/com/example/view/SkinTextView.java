package com.example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.core.R;
import com.example.core.ViewMatch;

@SuppressLint("AppCompatCustomView")
public class SkinTextView extends TextView implements ViewMatch {

    private static final String TAG = "SkinTextView";
    private static final int DEFAULT_VALUE = -1;
    private SparseIntArray mSparseIntArray;
    private static final int[] resourceNames = R.styleable.SkinTextView;

    public SkinTextView(Context context) {
        super(context);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
        Log.d(TAG, "SkinTextView: ");

    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSparseIntArray = new SparseIntArray();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, resourceNames, 0, 0);
        for (int i = 0; i < typedArray.length(); i++) {
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            mSparseIntArray.put(resourceNames[i], resourceId);
        }
        typedArray.recycle();
    }


    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinTextView_android_background];
        int backgroundId = mSparseIntArray.get(backgroundKey);
        if(backgroundId > 0){
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundId);
            setBackground(drawable);
        }
        int textColorKey = R.styleable.SkinTextView[R.styleable.SkinTextView_android_textColor];
        int textColorId = mSparseIntArray.get(textColorKey);
        if(textColorId > 0){
            ColorStateList color = ContextCompat.getColorStateList(getContext(), textColorId);
            setTextColor(color);
        }
    }
}

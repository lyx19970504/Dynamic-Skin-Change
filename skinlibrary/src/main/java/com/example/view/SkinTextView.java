package com.example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.SkinManager;
import com.example.core.R;
import com.example.core.ViewMatch;
import com.example.utils.StorageUtil;

@SuppressLint("AppCompatCustomView")
public class SkinTextView extends TextView implements ViewMatch {

    private static final String TAG = "SkinTextView";
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
        mSparseIntArray = StorageUtil.getSparseIntArray(context, attrs, resourceNames, defStyleAttr);
    }


    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinTextView_android_background];
        int backgroundId = mSparseIntArray.get(backgroundKey);
        if (backgroundId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundId);
                setBackgroundDrawable(drawable);
            } else {
                Object drawableObject = SkinManager.getInstance().getBackgroundOrSrc(backgroundId);
                if (drawableObject instanceof Integer) {
                    setBackgroundResource((Integer) drawableObject);
                } else if (drawableObject instanceof Drawable) {
                    Drawable drawable = (Drawable) drawableObject;
                    setBackgroundDrawable(drawable);
                }
            }
        }
        int textColorKey = R.styleable.SkinTextView[R.styleable.SkinTextView_android_textColor];
        int textColorId = mSparseIntArray.get(textColorKey);
        if (textColorId > 0) {
            ColorStateList color;
            if (SkinManager.getInstance().isDefaultSkin()) {
                color = ContextCompat.getColorStateList(getContext(), textColorId);
            } else {
                color = SkinManager.getInstance().getColorStateList(textColorId);
            }
            setTextColor(color);
        }

        int fontTypeKey = R.styleable.SkinTextView[R.styleable.SkinTextView_custom_typeface];
        int fontResourceId = mSparseIntArray.get(fontTypeKey);
        Log.d(TAG, "fontResourceId: " + fontResourceId);
        if (fontResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                setTypeface(Typeface.DEFAULT);
            } else {
                setTypeface(SkinManager.getInstance().getTypeface(fontResourceId));
            }
        }
    }
}

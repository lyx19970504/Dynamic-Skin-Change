package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.example.SkinManager;
import com.example.core.R;
import com.example.core.ViewMatch;
import com.example.utils.StorageUtil;

public class SkinRelativeLayout extends RelativeLayout implements ViewMatch {

    private SparseIntArray mSparseIntArray;
    private static final int[] resourceNames = R.styleable.SkinRelativeLayout;

    public SkinRelativeLayout(Context context) {
        this(context, null);
    }

    public SkinRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSparseIntArray = StorageUtil.getSparseIntArray(context, attrs, resourceNames, defStyleAttr);
    }

    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinRelativeLayout_android_background];
        int backgroundResourceId = mSparseIntArray.get(backgroundKey);
        if (backgroundResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
                setBackground(drawable);
            } else {
                Object drawableObject = SkinManager.getInstance().getBackgroundOrSrc(backgroundResourceId);
                if (drawableObject instanceof Integer) {
                    setBackgroundColor((Integer) drawableObject);
                } else if (drawableObject instanceof Drawable) {
                    Drawable drawable = (Drawable) drawableObject;
                    setBackground(drawable);
                }
            }
        }
    }
}

package com.example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.SkinManager;
import com.example.core.R;
import com.example.core.ViewMatch;
import com.example.utils.StorageUtil;

@SuppressLint("AppCompatCustomView")
public class SkinImageView extends ImageView implements ViewMatch {

    private SparseIntArray mSparseIntArray;
    private static final int[] resourceNames = R.styleable.SkinImageView;

    public SkinImageView(Context context) {
        this(context, null);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSparseIntArray = StorageUtil.getSparseIntArray(context, attrs, resourceNames, defStyleAttr);
    }

    @Override
    public void skinChange() {
        int srcKey = resourceNames[R.styleable.SkinImageView_android_src];
        int srcId = mSparseIntArray.get(srcKey);
        if (srcId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), srcId);
                setImageDrawable(drawable);
            } else {
                Object srcObject = SkinManager.getInstance().getBackgroundOrSrc(srcId);
                if (srcObject instanceof Integer) {
                    setImageResource((Integer) srcObject);
                } else if (srcObject instanceof Drawable) {
                    Drawable drawable = (Drawable) srcObject;
                    setImageDrawable(drawable);
                }
            }
        }
    }
}

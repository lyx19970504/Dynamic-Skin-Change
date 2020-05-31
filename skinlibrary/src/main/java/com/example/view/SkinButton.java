package com.example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.SkinManager;
import com.example.core.ViewMatch;
import com.example.skinlibrary.R;
import com.example.utils.StorageUtil;

@SuppressLint("AppCompatCustomView")
public class SkinButton extends Button implements ViewMatch {

    private SparseIntArray mSparseIntArray;
    private static final int[] resourceNames = R.styleable.SkinButton;

    public SkinButton(Context context) {
        super(context);
    }

    public SkinButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public SkinButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSparseIntArray = StorageUtil.getSparseIntArray(context, attrs, resourceNames, defStyleAttr);

    }


    @Override
    public void skinChange() {
        int backgroundKey = resourceNames[R.styleable.SkinButton_android_background];

        int backgroundId = mSparseIntArray.get(backgroundKey);
        if (backgroundId > 0) {

            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundId);
                setBackgroundDrawable(drawable);
            }else{
                Object drawableObject = SkinManager.getInstance().getBackgroundOrSrc(backgroundId);
                if(drawableObject instanceof Integer){
                    setBackgroundColor((Integer) drawableObject);
                }else if(drawableObject instanceof Drawable){
                    Drawable drawable = (Drawable) drawableObject;
                    setBackgroundDrawable(drawable);
                }
            }
        }

        int textColorKey = R.styleable.SkinButton[R.styleable.SkinButton_android_textColor];
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

        int key = R.styleable.SkinButton[R.styleable.SkinButton_custom_typeface];
        int textTypefaceResourceId = mSparseIntArray.get(key);
        if (textTypefaceResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                setTypeface(Typeface.DEFAULT);
            } else {
                setTypeface(SkinManager.getInstance().getTypeface(textTypefaceResourceId));
            }
        }
    }
}

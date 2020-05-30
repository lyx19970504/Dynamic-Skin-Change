package com.example.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

public class StorageUtil {

    private static final int DEFAULT_VALUE = -1;

    public static SparseIntArray getSparseIntArray(Context context, AttributeSet attrs, int[] resourceNames,
                                                   int defStyleAttr) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, resourceNames, defStyleAttr, 0);
        for (int i = 0; i < typedArray.length(); i++) {
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            sparseIntArray.put(resourceNames[i], resourceId);
        }
        typedArray.recycle();
        return sparseIntArray;
    }
}

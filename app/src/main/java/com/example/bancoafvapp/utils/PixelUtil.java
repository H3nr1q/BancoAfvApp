package com.example.bancoafvapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class PixelUtil {
    private PixelUtil() {
    }
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round((dp * DisplayMetrics.DENSITY_DEFAULT) / displayMetrics.xdpi);
        //return px;
    }
    public static int pxToDp(Context context, int px) {
        int dp = Math.round(px / getPixelScaleFactor(context));
        return dp;
    }
    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}


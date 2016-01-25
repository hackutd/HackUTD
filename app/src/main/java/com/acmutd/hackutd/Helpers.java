package com.acmutd.hackutd;

import android.content.Context;

/**
 * Created by feefa on 1/23/2016.
 */
public class Helpers {

    public static String host = "http://40.84.186.164:4250";

    static float dpToPx(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    static float pxToDp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (px - 0.5f) / scale;
    }
}

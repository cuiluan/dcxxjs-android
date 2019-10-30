package com.dcxxjs.core.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;



/**
 * 功能描述：布局移动
 * 作者： 崔潞安 Administrator
 * 时间： 2017-09-03.
 */
public class ViewUtil {


    private static final String TAG = "ViewHelper";
    public static WindowManager wm;
    private static Context context;

    public static void setScaleX(View mMenu, float leftScale) {
        mMenu.setScaleX(leftScale);
    }

    public static void setScaleY(View mMenu, float leftScale) {
        mMenu.setScaleY(leftScale);
    }

    public static void setTranslationX(View mContent, float v) {
        mContent.setTranslationX(v);
    }

    public static void setPivotX(View mContent, int i) {
        mContent.setPivotX(i);
    }

    public static void setAlpha(View mMenu, float v) {
        mMenu.setAlpha(v);
    }

    public static void setPivotY(View mContent, int i) {
        mContent.setPivotY(i);
    }

    /**
     * 功能描述：ViewHelper---->获取屏幕尺寸
     * 作者：崔潞安
     * 时间：2017-09-10
     * 联系邮箱：38642363@qq.com
     *
     * @param
     * @return
     */
    public static DisplayMetrics getScreenSize(Context context) {
        if (null == wm) {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 功能描述：ViewHelper---->dp转px
     * 作者：崔潞安
     * 时间：2017-09-10
     * 联系邮箱：38642363@qq.com
     *
     * @param
     * @return
     */
    public static float dp2px(int dp, DisplayMetrics dm) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                dm);
    }

    /**
     * 像素转DP
     * @param context
     * @param px
     * @return
     */
    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    //此处是计算View的width以及height
    public static void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

}

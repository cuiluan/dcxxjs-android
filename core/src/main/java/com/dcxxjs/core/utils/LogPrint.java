package com.dcxxjs.core.utils;

import android.util.Log;

public class LogPrint {
    /**
     * 打印异常
     * @param e
     */
    public  static  void print(String TAG,Exception e)
    {
        Log.d(TAG, "============================出现异常==================================");
        Log.d(TAG, "异常消息:"+e.getLocalizedMessage());
        Log.d(TAG, "异常名称:"+e.getClass().getCanonicalName());
        e.printStackTrace();
        Log.d(TAG, "异常消息"+e.getMessage());
        Log.d(TAG, "============================异常结束==================================");
    }

}

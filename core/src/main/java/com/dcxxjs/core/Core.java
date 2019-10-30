package com.dcxxjs.core;

import android.app.Application;
import android.os.Build;
import android.provider.Settings;

import com.dcxxjs.core.utils.DigestUtils;
import com.google.gson.Gson;


/**
 * 作    者:崔潞安
 * 时    间：2019/10/24
 * 功能描述：核心模块
 * 联系方式：
 */
public class Core {

    private static Core core;
    //手机唯一识别码
    public  String uniqueid="";
    //private static Application application;
    //开启
    public  boolean isDebug=false;
    //签名公钥
    public   String PublicKey;

    public Gson gson=new Gson();

    /**
     * 初始化
     * @param app
     */
    public static void init(Application app)
    {
        core=new Core();
        //application=app;
        String androidID = Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID);
        core.uniqueid= DigestUtils.MD5(androidID + Build.SERIAL);
    }

    /**
     * 开启调试
     */
    public static  void openDebug()
    {
        core.isDebug=true;
    }

    /**
     * 设置公钥
     * @param publickey 签名公钥
     */
    public static  void setPublickey(String publickey)
    {
        core.PublicKey=publickey;
    }

    /**
     * 获取单例
     * @return
     * @throws Exception
     */
    public static Core getInstance() {
        if(null==core)
        {
            core=new Core();
            core.uniqueid= DigestUtils.MD5(Build.SERIAL);
        }
        return core;
    }

}

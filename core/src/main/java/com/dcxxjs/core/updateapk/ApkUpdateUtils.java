package com.dcxxjs.core.updateapk;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.vector.update_app.UpdateAppManager;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/24
 * 功能描述：更新APP
 * 使用方法ApkUpdateUtils.getInstance(url).getUpdateAppManager(activity).[设置参数].build().update();
 *   或者使用默认参数ApkUpdateUtils.getInstance(url).update(final Activity activity);
 **/
public class ApkUpdateUtils {

    private UpdateAppManager.Builder updateAppManagerBuilder;
    private static ApkUpdateUtils apkUpdateUtils;
    /**
     * 更新地址
     */
    private String url;
    private static final String TAG =ApkUpdateUtils.class.getName() ;

    public static ApkUpdateUtils getInstance(String url)
    {
        if (null==apkUpdateUtils)
        {
            apkUpdateUtils=new ApkUpdateUtils(url);
        }
        return  apkUpdateUtils;
    }
    private ApkUpdateUtils(String url)
    {
        this.url=url;
    }
    /**
     * 更新app
     * @param activity
     */
    public  UpdateAppManager.Builder getUpdateAppManagerBuilder(final Activity activity) {
        String path =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/dcxxjs/";
        updateAppManagerBuilder=new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(activity)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(url)
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(true)
                //不显示通知栏进度条
                .dismissNotificationProgress()
                //是否忽略版本
                //.showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                //.setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
                //.hideDialogOnDownloading()
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
                //.setTopPic(R.mipmap.ic_launcher)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
                //.setThemeColor(ColorUtil.getRandomColor())
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path);
        return updateAppManagerBuilder;
    }

    /**
     * 启动默认更新更新
     */
    public  void  update(final Activity activity)
    {
        String path =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/dcxxjs/";
        updateAppManagerBuilder=new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(activity)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(url)
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(true)
                //不显示通知栏进度条
                .dismissNotificationProgress()
                //是否忽略版本
                //.showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                //.setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
                //.hideDialogOnDownloading()
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
                //.setTopPic(R.mipmap.ic_launcher)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
                //.setThemeColor(ColorUtil.getRandomColor())
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path);
        updateAppManagerBuilder.build().update();
    }

}
package com.dcxxjs.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


import org.xutils.x;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/24
 * 功能描述：Application类
 **/

public abstract class BaseApplication extends MultiDexApplication {

    static {//使用static代码段可以防止内存泄漏

        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
                layout.setReboundDuration(1000);
                //layout.setReboundInterpolator(new DropBounceInterpolator());
                //layout.setFooterHeight(100);
                layout.setDisableContentWhenLoading(false);
                layout.setPrimaryColorsId(android.R.color.black, android.R.color.white);
            }
        });

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);
                return  new WaterDropHeader(context);
               // return new MaterialHeader(context).setColorSchemeResources(R.color.colorRed,R.color.colorGreen,R.color.colorBlue);
            }
        });
        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {

            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
                // return new MaterialHeader(context).setColorSchemeResources(R.color.colorRed,R.color.colorGreen,R.color.colorBlue);
            }
        });
    }
    public Boolean isDebug=true;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils
        x.Ext.init(this);
        Core.init(this);
        //初始化XUI
       if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            Core.openDebug();
        }
        //初始化ARouter
        ARouter.init(BaseApplication.this); // 尽可能早，推荐在Application中初始化
//        //融云初始化
//        RongIM.init(this,"pwe86ga5p4m76");
//        //融云推送
//        PushConfig config = new PushConfig.Builder()
//                .enableHWPush(true)
//                //.enableMiPush("小米 appId", "小米 appKey")
//                //.enableMeiZuPush("魅族 appId", "魅族 appKey")
//                .enableFCM(true)
//                .build();
//        RongPushClient.setPushConfig(config);
        //设置公钥
        Core.setPublickey(getPublicKey());
    }

    /**
     * 获取公钥
     * @return
     */
    protected abstract  String getPublicKey();

}

package com.dcxxjs.core.baseactivity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dcxxjs.core.Core;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Validator;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/30
 * 功能描述：基础的视图
 **/
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getName();
    public final  int requestPermissionsCode=200;

    //表单验证框架
    protected Validator validator=null;
    ViewDataBinding viewDataBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ARouter注解
        ARouter.getInstance().inject(this);
        //检查权限
        CheckPermissions(getPermissions());
        //加载布局文件的ID
        Log.d(TAG, "onCreate: 布局文件"+ getContentViewId(this));
        ViewDataBinding viewDataBinding= DataBindingUtil.setContentView(this, getContentViewId(this));
        //使用ButterKnife
        ButterKnife.bind(this);
        InitData();
    }
    /**
     * 从父类获取注解View
     */
    protected  BindContentView findContentView(Class<?> thisCls) {
        if (thisCls == null) {
            return null;
        }
        Log.d(TAG, "findContentView: "+thisCls.getCanonicalName());
        BindContentView contentView = thisCls.getAnnotation(BindContentView.class);
        if (contentView == null) {
            return findContentView(thisCls.getSuperclass());
        }
        return contentView;
    }
    public int getContentViewId(Activity activity)
    {
       return findContentView(activity.getClass()).value();
    }

    //获取验证
    public  Validator getValidator()
    {
        if(null==validator)
        {
            //验证框架的创建
            validator = new Validator(this);
        }
        return  validator;
    }

    /**
     *检查权限
     */
    protected void  CheckPermissions(String[] permissions)
    {

        List<String> noPermissions=new ArrayList<>();
        if(null==permissions)
        {
            return;
        }
        for (String permission:permissions)
        {
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                noPermissions.add(permission);
            }
        }
        if(noPermissions.size()>0)
        {
            String[] nop=new String[noPermissions.size()];
            for (int i=0;i<noPermissions.size();i++)
            {
                nop[i]=noPermissions.get(i);
            }
            ActivityCompat.requestPermissions(this,nop,requestPermissionsCode);
        }
    }
    /**
     * 获取权限列表
     * @return
     */
    protected String[] getPermissions()
    {
       return  null;
    }
    /**
     * 加载数据在OnCreate最后
     */
    protected abstract void InitData();
}

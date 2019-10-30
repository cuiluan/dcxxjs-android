package com.dcxxjs.core.baseactivity;
/**
 * 浸入式页面
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.mobsandgeeks.saripaar.Validator;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseImmersionActivity extends BaseActivity {

    ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBar();
    }

    /**
     * 设置
     */
    protected void setBar() {
        ViewGroup contentView = findViewById(android.R.id.content);
        View childAt = contentView.getChildAt(0);
        View first=((ViewGroup) childAt).getChildAt(0);
        if (childAt != null &&first.getFitsSystemWindows()) {
            ImmersionBar.with(this).reset().titleBar(first);
        } else {
           ImmersionBar.with(this).reset().init();
        }
    }
}

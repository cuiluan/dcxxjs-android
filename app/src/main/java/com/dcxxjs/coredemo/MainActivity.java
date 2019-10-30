package com.dcxxjs.coredemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dcxxjs.core.baseactivity.BaseActivity;
import com.dcxxjs.core.baseactivity.BindContentView;
import com.dcxxjs.core.networkinterface.SignBaseFrom;
import com.dcxxjs.core.networkinterface.SignBaseObserver;

import butterknife.BindView;
import butterknife.OnClick;

@BindContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    ViewDataBinding viewDataBinding;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void InitData() {
        Log.d(TAG, "InitData: " + button);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Log.d(TAG, "onViewClicked: 跳转到默认List界面");
        ARouter.getInstance().build(Path.deflist).navigation();
    }

    @OnClick(R.id.button2)
    public void onViewClicked2() {
        ARouter.getInstance().build(Path.deftab).navigation();
    }
}

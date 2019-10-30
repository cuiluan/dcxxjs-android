package com.dcxxjs.coredemo;

import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dcxxjs.core.baseFragment.BaseFragment;
import com.dcxxjs.core.baseactivity.BindContentView;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
@Route(path = Path.f1)
@BindContentView(R.layout.activity_main2)
public class Frament1 extends BaseFragment {
    @Override
    protected void InitData(ViewDataBinding viewDataBinding) {

    }
}

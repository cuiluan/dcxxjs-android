package com.dcxxjs.core.baseFragment;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dcxxjs.core.baseactivity.BindContentView;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能： 结果页面接口
 * 作者： 崔潞安
 * 时间： 2018-02-10.
 * 邮箱： 38642363@qq.com
 * 使用方法：
 */

public abstract class BaseFragment extends Fragment {

    private String TAG = BaseFragment.class.getSimpleName();
    private boolean injected=false;
    public ViewDataBinding viewDataBinding;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        viewDataBinding = DataBindingUtil.inflate(inflater,getContentViewId(this), container, false);
        ARouter.getInstance().inject(this);
        //使用ButterKnife
        unbinder=ButterKnife.bind(this,viewDataBinding.getRoot());
        InitData(viewDataBinding);
        return viewDataBinding.getRoot();
    }
    public int getContentViewId(Fragment fragment)
    {
        return findContentView(fragment.getClass()).value();
    }
    /**
     * 从父类获取注解View
     */
    public BindContentView findContentView(Class<?> thisCls) {
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
    //初始化
    protected abstract void InitData(ViewDataBinding viewDataBinding);
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        if (!injected) {
//            viewDataBinding=DataBindingUtil.inflate(view.,findContentView(this.getClass()).value(), view.getParent(), false);
//            ARouter.getInstance().inject(this);
//            InitData(viewDataBinding);
//        }
//        super.onViewCreated(view, savedInstanceState);
//    }
}

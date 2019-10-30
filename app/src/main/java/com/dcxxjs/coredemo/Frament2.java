package com.dcxxjs.coredemo;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dcxxjs.core.baseFragment.BaseListFragemnt;
import com.dcxxjs.core.baseadapter.DataBindAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
@Route(path = Path.f2)
public class Frament2 extends BaseListFragemnt {
    DataBindAdapter<Pers> adapter=new DataBindAdapter<Pers>();
    @Override
    protected void InitData(ViewDataBinding viewDataBinding) {
        super.InitData(viewDataBinding);
        for (int i=0;i<10;i++)
        {
            Pers p=new Pers();
            p.name="姓名"+i;
            p.age="年龄"+i;
            adapter.add(p);
        }
        list.setAdapter(adapter);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}

package com.dcxxjs.coredemo;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dcxxjs.core.baseactivity.BaseListActivity;
import com.dcxxjs.core.baseadapter.DataBindAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
@Route(path = Path.deflist)
public class ListDef extends BaseListActivity {
    DataBindAdapter<Pers> adapter=new DataBindAdapter<Pers>();
    @Override
    protected void InitData() {
        super.InitData();
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

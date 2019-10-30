package com.dcxxjs.core.baseactivity;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.dcxxjs.core.R;
import com.dcxxjs.core.R2;
import com.dcxxjs.core.divideritemdecoration.RecyclerViewItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
public abstract class BaseListActivity extends BaseActivity  implements OnRefreshListener {
    @BindView(R2.id.list)
    public RecyclerView list;
    @BindView(R2.id.listlayout)
    public SmartRefreshLayout listlayout;
    @BindView(R2.id.multiple_status_view)
    public MultipleStatusView multipleStatusView;



    @Override
    public int getContentViewId(Activity activity) {
        BindContentView contentView = findContentView(this.getClass());
        if (null == contentView) {
            return R.layout.dcxxjs_core_base_list_activity;
        } else {
            return contentView.value();
        }
    }

    @Override
    protected void InitData() {
        listlayout.setEnableLoadMore(false);
        listlayout.setEnableRefresh(true);
        listlayout.setOnRefreshListener(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new RecyclerViewItemDecoration(this, LinearLayoutManager.HORIZONTAL,2,R.color.design_default_color_primary));
    }
}

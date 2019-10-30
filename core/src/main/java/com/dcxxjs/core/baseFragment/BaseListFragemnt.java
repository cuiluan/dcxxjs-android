package com.dcxxjs.core.baseFragment;

import android.app.Activity;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.dcxxjs.core.R;
import com.dcxxjs.core.R2;
import com.dcxxjs.core.baseactivity.BaseListActivity;
import com.dcxxjs.core.baseactivity.BindContentView;
import com.dcxxjs.core.divideritemdecoration.RecyclerViewItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
public abstract class BaseListFragemnt extends BaseFragment implements OnRefreshListener {

    @BindView(R2.id.list)
    public RecyclerView list;
    @BindView(R2.id.listlayout)
    public SmartRefreshLayout listlayout;
    @BindView(R2.id.multiple_status_view)
    public MultipleStatusView multipleStatusView;

    @Override
    public int getContentViewId(Fragment fragment) {
        BindContentView contentView = findContentView(fragment.getClass());
        if (null == contentView) {
            return R.layout.dcxxjs_core_base_list_activity;
        } else {
            return contentView.value();
        }
    }

    @Override
    protected void InitData(ViewDataBinding viewDataBinding) {
        listlayout.setEnableLoadMore(false);
        listlayout.setEnableRefresh(true);
        listlayout.setOnRefreshListener(this);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.addItemDecoration(new RecyclerViewItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL,2,R.color.design_default_color_primary));
    }
}

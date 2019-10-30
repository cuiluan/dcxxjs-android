package com.dcxxjs.core.baseFragment;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.classic.common.MultipleStatusView;
import com.dcxxjs.core.R;
import com.dcxxjs.core.R2;
import com.dcxxjs.core.baseactivity.BindContentView;

import butterknife.BindView;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：多态性界面
 * 联系方式：
 */
public abstract class BaseMultipleStatusViewFrament extends BaseFragment {

    @BindView(R2.id.multiple_status_view)
    public MultipleStatusView multipleStatusView;
    @Override
    public int getContentViewId(Fragment activity) {
        BindContentView contentView = findContentView(this.getClass());
        if (null == contentView) {
            return R.layout.dcxxjs_core_base_multiple_status_view_activity;
        } else {
            return contentView.value();
        }
    }
}

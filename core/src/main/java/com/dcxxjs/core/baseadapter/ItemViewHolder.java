package com.dcxxjs.core.baseadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：子项绑定结构
 * 联系方式：
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    public ItemViewHolder(ViewDataBinding itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return this.binding;
    }
}

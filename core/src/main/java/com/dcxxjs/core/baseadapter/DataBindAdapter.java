package com.dcxxjs.core.baseadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：利用DataBind实现的Adapter
 * 联系方式：
 */
public class DataBindAdapter<E extends IBaseEntity> extends RecyclerView.Adapter<ItemViewHolder> {

    public DataBindAdapter()
    {
        mData=new ArrayList<>();
    }
    //最大数据量
    private int maxSize=50;
    //数据列表
    protected List<E> mData;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setData(List<E> data) {
        addAll(data);
        notifyDataSetChanged();
    }

    public List<E> getData() {
        return mData;
    }
    /**
     * 创建子视图
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType,parent, false);
        ItemViewHolder holder = new ItemViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.getBinding().setVariable(mData.get(position).getvariableId(),mData.get(position));
        holder.getBinding().executePendingBindings();
    }

    /**
     * 当列表滑出界面
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(ItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //释放资源
        int position = holder.getAdapterPosition();
        //越界检查
        if(position<0 || position>=mData.size()){
            return;
        }
        mData.get(position).releaseResource();
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0:mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemLayout();
    }

    /**
     * 添加数据
     * @param cell
     */
    public void add(E cell){
        mData.add(cell);
        int index = mData.indexOf(cell);
        notifyItemChanged(index);
    }

    public void add(int index,E cell){
        mData.add(index,cell);
        notifyItemChanged(index);
    }

    /**
     *移除一个
     * @param cell
     */
    public void remove(E cell){
        int indexOfCell = mData.indexOf(cell);
        remove(indexOfCell);
    }

    /**
     * 移除指定位置的
     * @param index
     */
    public void remove(int index){
        mData.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 移除一个
     * @param start
     * @param count
     */
    public void remove(int start,int count){
        if((start +count) > mData.size()){
            return;
        }
        int size = getItemCount();
        for(int i =start;i<size;i++){
            mData.remove(i);
        }
        notifyItemRangeRemoved(start,count);
    }

    /**
     * add a cell smartRefreshLayout
     * @param cells
     */
    public void addAll(List<E> cells){
        if(cells == null || cells.size() == 0){
            return;
        }
        mData.addAll(cells);
        notifyItemRangeChanged(mData.size()-cells.size(),mData.size());
    }

    /**
     * 添加数据
     * @param index
     * @param cells
     */
    public void addAll(int index,List<E> cells){
        if(cells == null || cells.size() == 0){
            return;
        }
        mData.addAll(index,cells);
        notifyItemRangeChanged(index,index+cells.size());
    }

    /**
     *  清空数据
     */
    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }
}

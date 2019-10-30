package com.dcxxjs.core.baseactivity;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;


/**
 * 主窗体适配器
 */
public class TabFramentAdapter extends FragmentPagerAdapter {

    private static final String TAG = TabFramentAdapter.class.getName() ;
    private Fragment[]  fragmentList;
    private List<TabDes> tabDes;
    public TabFramentAdapter(FragmentManager fragmentManager, Activity activity, List<TabDes> tabDes)
    {
        super(fragmentManager);
        fragmentList=new Fragment[tabDes.size()];
        this.tabDes=tabDes;
        getItem(0);
    }
    @Override
    public int getCount() {
        return fragmentList.length;
    }

    @Override
    public Fragment getItem(int position) {
        if(null==fragmentList[position])
        {
            fragmentList[position]=createItemView(position);
        }
        return fragmentList[position];
    }

    private Fragment createItemView(int position)
    {
        Log.d(TAG, "创建视图"+position);
      return  (Fragment) ARouter.getInstance().build(tabDes.get(position).getPath()).navigation();
    }
}

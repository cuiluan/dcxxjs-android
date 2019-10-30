package com.dcxxjs.coredemo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dcxxjs.core.baseactivity.BaseTabActivity;
import com.dcxxjs.core.baseactivity.TabDes;

import java.util.ArrayList;
import java.util.List;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
@Route(path=Path.deftab)
public class TabActivity extends BaseTabActivity {
    List<TabDes> list;
    @Override
    public List<TabDes> getTabDes() {
        if(null==list)
        {
            list=new ArrayList<>();
            TabDes itsm=new TabDes();
            itsm.setName("第一个");
            itsm.setPath(Path.f1);
            itsm.setUnSelect(R.drawable.root_menu_select_01);
            itsm.setSelect(R.drawable.root_menu_unselect_01);
            list.add(itsm);
            TabDes itsm2=new TabDes();
            itsm2.setName("第二个");
            itsm2.setPath(Path.f2);
            itsm2.setUnSelect(R.drawable.root_menu_select_02);
            itsm2.setSelect(R.drawable.root_menu_unselect_02);
            list.add(itsm2);
            TabDes itsm3=new TabDes();
            itsm3.setName("第三个");
            itsm3.setPath(Path.f3);
            itsm3.setUnSelect(R.drawable.root_menu_select_03);
            itsm3.setSelect(R.drawable.root_menu_unselect_03);
            list.add(itsm3);
        }
        return list;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    protected void InitData() {

    }
}

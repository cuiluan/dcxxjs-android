package com.dcxxjs.core.baseactivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dcxxjs.core.R;
import com.dcxxjs.core.R2;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：切换卡片的Activity
 * 联系方式：
 */
public abstract class BaseTabActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = BaseTabActivity.class.getName();
    @BindView(R2.id.mainfram)
    ViewPager mainfram;
    @BindView(R2.id.rootmenu)
    RadioGroup rootmenu;

    List<RadioButton> buttons;
    //切换卡位置
    TabLocation tabLocation = null;
    TabFramentAdapter adapter;
    ImmersionBar immersionBar;


    public TabLocation getTabLocation() {
        //默认在底部
        if (null == tabLocation) {
            tabLocation = TabLocation.Bootom;
        }
        return tabLocation;
    }

    public void setTabLocation(TabLocation tabLocation) {
        this.tabLocation = tabLocation;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TabFramentAdapter(getSupportFragmentManager(), this, getTabDes());
        mainfram.setAdapter(adapter);
        mainfram.setCurrentItem(0);
        mainfram.setOnPageChangeListener(this);
        setTag(getTabDes());
        setBar();
    }

    /**
     * 设置
     */
    protected void setBar() {
        switch (getTabLocation()) {
            case Left:
            case Rigth:
            case Top: {
                ViewGroup contentView = findViewById(android.R.id.content);
                View childAt = contentView.getChildAt(0);
                View first = ((ViewGroup) childAt).getChildAt(0);
                if (childAt != null && first.getFitsSystemWindows()) {
                    ImmersionBar.with(this).reset().titleBar(first);
                } else {
                    ImmersionBar.with(this).reset().init();
                }
                break;
            }
            case Bootom: {
                ImmersionBar.with(this).reset().titleBar(adapter.getItem(mainfram.getCurrentItem()).getView());
                break;
            }
        }

    }

    //设置布局文件
    @Override
    public int getContentViewId(Activity activity) {
        BindContentView contentView = findContentView(this.getClass());
        if (null == contentView) {
            switch (getTabLocation()) {
                case Top: {
                    return R.layout.dcxxjs_core_tab_top_activity;
                }
                case Left: {
                    return R.layout.dcxxjs_core_tab_left_activity;
                }
                case Rigth: {
                    return R.layout.dcxxjs_core_tab_rigth_activity;
                }
                default: {
                    return R.layout.dcxxjs_core_tab_bootom_activity;
                }
            }
        } else {
            return contentView.value();
        }
    }

    /**
     * 动态创建按钮
     */
    protected void setTag(final List<TabDes> list) {
        buttons = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT,1.0f);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setId(i);
            radioButton.setText(list.get(i).getName());
            radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
            radioButton.setTextAppearance(this, R.style.dcxxjs_core_rootMenuButton);
            radioButton.setButtonDrawable(null);
            final TabDes tabDes = list.get(i);
            //设置未选择
            Drawable drawable = getResources().getDrawable(tabDes.getUnSelect());
            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Drawable drawable = getResources().getDrawable(tabDes.getSelect());
                        buttonView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    } else {
                        Drawable drawable = getResources().getDrawable(tabDes.getUnSelect());
                        buttonView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    }
                }
            });
            radioButton.setChecked(false);
            buttons.add(radioButton);
            rootmenu.addView(radioButton);//将单选按钮添加到RadioGroup中
            rootmenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    mainfram.setCurrentItem(checkedId);
                    ImmersionBar.with(BaseTabActivity.this).reset().titleBar(adapter.getItem(checkedId).getView());
                }
            });
        }
        buttons.get(0).setChecked(true);

    }

    //列表
    public abstract List<TabDes> getTabDes();

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            buttons.get(mainfram.getCurrentItem()).setChecked(true);
        }
    }
}

enum TabLocation {
    Top, Bootom, Left, Rigth
}

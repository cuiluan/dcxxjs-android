package com.dcxxjs.coredemo;

import com.dcxxjs.core.baseadapter.IBaseEntity;

import static com.dcxxjs.coredemo.BR.pers;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
public class Pers extends IBaseEntity {

    public String name;
    public String age;

    @Override
    public int getItemLayout() {
        return R.layout.list_item;
    }

    @Override
    public int getvariableId() {
        return pers;
    }
}

package com.dcxxjs.core.baseadapter;

import androidx.annotation.LayoutRes;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/25
 * 功能描述：实体对象基础类型
 * 联系方式：
 */
public abstract class IBaseEntity {

  //选择哪个UI布局文件
  public  abstract  @LayoutRes int getItemLayout();
  //获取资源的ID值
  public  abstract int getvariableId();
    //释放资源
   void releaseResource()
   {

   }
}

package com.dcxxjs.core.networkinterface;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.dcxxjs.core.baseadapter.IBaseEntity;

/**
 * 2019年10月11日10:45:28
 * 简单的非列表数据解析器
 */
public abstract class BaseDataParsing<T extends IBaseEntity>  implements IDataParsing{

    public BaseDataParsing(Context context)
    {
        this.mContext=context;
    }

    protected Context mContext;

    public abstract void OnSuccess( final SignBaseResult result);

    public void onCodeError( final  SignBaseResult result) {
        if(mContext instanceof Activity)
        {
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,result.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

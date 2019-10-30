package com.dcxxjs.core.networkinterface;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.dcxxjs.core.baseadapter.DataBindAdapter;
import com.dcxxjs.core.baseadapter.IBaseEntity;

import java.util.List;

/**
 * 2019年10月11日10:45:28
 * 简单的数据解析器，只是弹出界面
 */
public abstract class BaseListDataParsing<T extends IBaseEntity>  implements IDataParsing{
     DataBindAdapter adapter;

    public BaseListDataParsing(Context context, DataBindAdapter adapter)
    {
        this.adapter=adapter;
        this.mContext=context;
    }

    protected Context mContext;

    public void OnSuccess( final SignBaseResult result) {
        List<T> list= DataParsing(result.getData());
        adapter.addAll(list);
    }
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
    /**
     * 数据转化
     * @param data
     */
    public abstract List<T> DataParsing(String data);

}

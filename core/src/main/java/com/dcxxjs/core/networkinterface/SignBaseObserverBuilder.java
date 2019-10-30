package com.dcxxjs.core.networkinterface;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;

import com.dcxxjs.core.baseadapter.DataBindAdapter;
import com.dcxxjs.core.baseadapter.IBaseEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：构建简单的SignBaseObserver
 * 联系方式：
 */
public class SignBaseObserverBuilder {
    static
    {
        gson=new Gson();
        //设置转化Adapter
    }
    public static  Gson gson;

    /**
     * 非列表数据访问
     * @param context 上下文
     * @param viewDataBinding  viewDataBinding对象
     * @param entivtytype 实体类型
     * @param iBaseLoding 进度接口
     * @param <T>
     * @return
     */
    public static<T extends  IBaseEntity> SignBaseObserver  BuildSignBaseObserver(Context context,final ViewDataBinding viewDataBinding, final Class<T> entivtytype, IBaseLoding iBaseLoding)
    {
        Builder builder=new Builder(context);
        builder.setiBaseLoding(iBaseLoding);
        builder.setiDataParsing(new BaseDataParsing<T>(context) {
            @Override
            public void OnSuccess(SignBaseResult result) {
                T iBaseEntity=gson.fromJson(result.getData(),entivtytype);
                viewDataBinding.setVariable(iBaseEntity.getvariableId(),iBaseEntity);
            }

            @Override
            public void Error() {
                if(mContext instanceof Activity)
                {
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"系统出现不可预知错误",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return builder.Build();
    }

    /**
     * 列表行的数据
     * @param context
     * @param adapter
     * @param entivtytype
     * @param iBaseLoding
     * @param <T>
     * @return
     */
    public static<T extends  IBaseEntity> SignBaseObserver  BuildSignBaseListObserver(Context context, final DataBindAdapter adapter, final Class<T> entivtytype, IBaseLoding iBaseLoding)
    {
        Builder builder=new Builder(context);
        builder.setiBaseLoding(iBaseLoding);
        builder.setiDataParsing(new BaseListDataParsing<T>(context,adapter) {
            @Override
            public void Error() {
                if(mContext instanceof Activity)
                {
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"系统出现不可预知错误",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public List DataParsing(String data) {
                Type type = new TypeToken<List<T>>() {
                }.getType();
                return gson.fromJson(data,type);
            }
        });
        return builder.Build();
    }
    public static<T extends  IBaseEntity> SignBaseObserver  BuildSignBaseObserver(Context context,final ViewDataBinding viewDataBinding, final Class<T> entivtytype)
    {
        return  BuildSignBaseObserver(context,viewDataBinding,entivtytype,null);
    }
    public static<T extends  IBaseEntity> SignBaseObserver  BuildSignBaseListObserver(Context context, final DataBindAdapter adapter, final Class<T> entivtytype)
    {
        return  BuildSignBaseListObserver(context,adapter,entivtytype,null);
    }

}
class Builder
{
    private   Context context;
    Builder(Context context)
    {
        this.context=context;
    }
    public IDataParsing getiDataParsing() {
        return iDataParsing;
    }

    public void setiDataParsing(IDataParsing iDataParsing) {
        this.iDataParsing = iDataParsing;
    }

    public IBaseLoding getiBaseLoding() {
        return iBaseLoding;
    }

    public void setiBaseLoding(IBaseLoding iBaseLoding) {
        this.iBaseLoding = iBaseLoding;
    }

    IDataParsing iDataParsing;
    IBaseLoding iBaseLoding;
    public  SignBaseObserver Build()
    {
        return  new SignBaseObserver(context,iDataParsing,iBaseLoding);
    }
}
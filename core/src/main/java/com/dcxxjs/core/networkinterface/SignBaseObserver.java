package com.dcxxjs.core.networkinterface;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dcxxjs.core.utils.CommRSA.CommRSASignatureUtils;
import com.dcxxjs.core.utils.CommRSA.CommRSAUtils;
import com.dcxxjs.core.utils.SHA256SiginUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rx.Observer;

public  class SignBaseObserver implements Observer<SignBaseResult> {

    private static final String TAG = "SignBaseObserver";
    //上下文
    protected Context mContext;
    //数据解析方法
    protected IDataParsing iDataParsing;
    //数据加载接口
    protected IBaseLoding  iBaseLoding;

    public SignBaseObserver(Context cxt) {
        this(cxt,null,null);
    }
    public SignBaseObserver(Context cxt,IDataParsing iDataParsing) {
        this(cxt,null,null);
    }

    public SignBaseObserver(Context cxt,IBaseLoding iBaseLoding) {
        this(cxt,null,iBaseLoding);
    }
    public SignBaseObserver(Context cxt,IDataParsing iDataParsing,IBaseLoding iBaseLoding) {
        this.mContext=cxt;
        //设置默认的
        this.iDataParsing=iDataParsing;
        if(null== this.iDataParsing)
        {
            this.iDataParsing=new IDataParsing() {
                @Override
                public void OnSuccess(final SignBaseResult result) {
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

                @Override
                public void onCodeError(final SignBaseResult result) {
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

                @Override
                public void Error() {

                }
            };
        }
        this.iBaseLoding=iBaseLoding;
    }
    //数据接收完成后
    @Override
    public void onCompleted() {
        //存在加载动画，关闭加载动画
        if(null!=iBaseLoding)
        {
            iBaseLoding.close();
        }
    }
    /**
     * 异常处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        iDataParsing.Error();
        if(null!=iBaseLoding)
        {
            iBaseLoding.close();
        }
        Log.i(TAG, "onError: " + e.getMessage());
        if (e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof SocketTimeoutException
                || e instanceof UnknownHostException) {
            onFailureByNetWork();
        } else {
            onFailure(e);
        }
    }

    @Override
    public void onNext(SignBaseResult t) {
        //验证签名
        String sign=t.getSign();
        t.setSign("");
        String content = SHA256SiginUtils.SignContent(t,"&");
        Log.d(TAG, "onNext: 签名字符串"+content);
        boolean checksign= CommRSASignatureUtils.doCheck(content,sign, CommRSAUtils.loadPublicKeyByFile(),"UTF-8");
        if(!checksign)
        {
            Log.e(TAG, "onNext: 签名异常");
            return ;
        }
        try {
            if (t.getErrorCode().trim().equals("0")) {
                iDataParsing.OnSuccess(t);
            } else {
                iDataParsing.onCodeError(t);
            }
        } catch (Exception e) {
            Log.d(TAG, "onNext->onSuccees: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 出现异常处理方式
     */
    protected void onFailure(Throwable e) {
        if(mContext instanceof Activity)
        {
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,"其他不可预知的错误",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     * 网络异常处理方式
     */
    protected void onFailureByNetWork() {
        if(mContext instanceof Activity)
        {
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,"网络连接错误，请检查网络",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

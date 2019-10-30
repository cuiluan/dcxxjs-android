package com.dcxxjs.coredemo;

import com.dcxxjs.core.networkinterface.ServiceAccess;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/25
 * 功能描述：
 * 联系方式：
 */
public class NetWorkDemo extends ServiceAccess<INetwork>  {
    //网络接口
    private  static INetwork iNetwork;

    private static NetWorkDemo netWorkDemo;
    /**
     * 返回基础路径
     *
     * @return
     */
    @Override
    protected String getBaseUrl() {
        return "";
    }

    public static INetwork getInstance() {
        if(null==netWorkDemo)
        {
            netWorkDemo=new NetWorkDemo();
            iNetwork=netWorkDemo.getServiceInterfaceClass(INetwork.class);
        }
        return iNetwork;
    }
}

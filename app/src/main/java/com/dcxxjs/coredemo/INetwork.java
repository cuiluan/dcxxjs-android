package com.dcxxjs.coredemo;

import com.dcxxjs.core.networkinterface.BaseObservable;
import com.dcxxjs.core.networkinterface.SignBaseFrom;
import com.dcxxjs.core.networkinterface.SignBaseResult;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/25
 * 功能描述：
 * 联系方式：
 */
public interface INetwork {

    @Headers({"ServerIP:logon_IP"})
    @POST("/LogonServer/Logongatway/LogonByPassWord.do")
    BaseObservable loginbypassword(@Body SignBaseFrom frombean);
}

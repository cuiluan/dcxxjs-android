package com.dcxxjs.core.networkinterface.interceptor;

import android.util.Log;
import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2019年10月11日10:56:28
 * 地址重定向
 */
public class RdirectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("ServerIP");
        Log.d("cui", "intercept: "+headerValues.size());
        if (null == headerValues || headerValues.size() <= 0) {
            return chain.proceed(request);
        }
        //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
        builder.removeHeader("ServerIP");
        //匹配获得新的BaseUrl
        //重新定义地址
        HttpUrl newBaseUrl =HttpUrl.parse(headerValues.get(0));
        /**
         * 重新定义地址错误
         */
        if(null==newBaseUrl)
        {
            return chain.proceed(request);
        }
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //重建新的HttpUrl，修改需要修改的url部分
        HttpUrl newFullUrl = oldHttpUrl
                .newBuilder()
                .scheme(newBaseUrl.scheme())
                .host(newBaseUrl.host())
                .port(newBaseUrl.port())
                .build();
        //重建这个request，通过builder.url(newFullUrl).build()；
        //然后返回一个response至此结束修改
        return chain.proceed(builder.url(newFullUrl).build());
    }

}

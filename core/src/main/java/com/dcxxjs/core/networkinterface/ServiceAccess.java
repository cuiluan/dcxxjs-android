package com.dcxxjs.core.networkinterface;

import android.util.Log;

import com.dcxxjs.core.networkinterface.interceptor.RdirectInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/25
 * 功能描述：网络访问父类，要求创建接口文件，继承ServiceInterface，更具服务实现ServiceAccess父类
 * @Headers({"ServerIP:192.168.9.0."})
 * @POST("Statis/save.do")
 * BaseObservable SimCountService(@Body Countfrombean ctfmbean)
 **/
public abstract class ServiceAccess<T>{

    protected   Retrofit retrofit;
    protected   OkHttpClient okHttpClient;//网络访问
    protected   Converter.Factory gsonConverterFactory = GsonConverterFactory.create();//json转化
    protected   CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    protected   Interceptor rdirect=new RdirectInterceptor();
    protected   Interceptor log=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //Chain 里包含了request和response
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            Log.d("cui", String.format("发送请求 %s on %s%n%s",request.url(),chain.connection(),request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //不能直接使用response.body（）.string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，
            // 我们需要创建出一个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.d("cui", String.format("接收响应：[%s] %n返回json:%s  %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2-t1) /1e6d,
                    response.headers()
            ));
            return response;
        }
    };
    /**
     * 初始化okhttp控件,并重新制定URL
     */
    private void init() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)//日志输出
                    .addInterceptor(rdirect)//根据Head重定向
                    .addInterceptor(log)//自定义网络输出
                    .build();
            //okHttpClient = new OkHttpClient.Builder().build();
             retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
        }
    }

    /**
     * 返回
     * @return
     */
    public  T getServiceInterfaceClass(Class<T> tClass)
    {
        init();
        return retrofit.create(tClass);
    }
    /**
     * 返回基础路径
     * @return
     */
    protected  abstract String getBaseUrl();

}

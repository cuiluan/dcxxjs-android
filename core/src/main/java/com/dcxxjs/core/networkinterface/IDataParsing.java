package com.dcxxjs.core.networkinterface;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/25
 * 功能描述：数据解析接口
 **/
public interface IDataParsing {
    //数据成功时的接口
    void OnSuccess(SignBaseResult result);
    //数据错误时的接口
    void onCodeError(SignBaseResult result);
    //错误接口
    void Error();

}

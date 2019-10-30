package com.dcxxjs.core.networkinterface;

/**
 * 2019年10月11日10:27:41
 * 所有后台数据返回结果
 *
 */
public class SignBaseResult {

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 错误验证码
     */
    private String errorCode;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 正确时返回的数据
     */
    private String data;

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

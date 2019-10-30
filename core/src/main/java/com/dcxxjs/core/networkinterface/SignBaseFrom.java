package com.dcxxjs.core.networkinterface;
/**
 * 2019年10月11日10:26:18
 * 网络表单接口
 * 所有网络表单都要集成该类
 * 拥有签名功能
 */

import com.dcxxjs.core.Core;

import java.util.Random;

public class SignBaseFrom {
    //Token
    private String token;
    //手机MIC
    private String mic;
    //随机数
    private String rand;
    //安全验证
    private String sign;
    private String kind;
    private int count;
    private int skip;


    public SignBaseFrom() {
        this.setMic(Core.getInstance().uniqueid);
        Random random = new Random();
        this.setRand(String.valueOf(random.nextDouble()));
    }

    public SignBaseFrom(String token) {
        this.setToken(token);
        this.setMic(Core.getInstance().uniqueid);
        Random random = new Random();
        this.setRand(String.valueOf(random.nextDouble()));
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 业务种类
     */
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 分页数量
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 跳过多少页
     */
    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}

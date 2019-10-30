package com.dcxxjs.core;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.google.gson.Gson;

import org.junit.Test;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：
 * 联系方式：
 */
public class testTest {

    @Test
    public void ss()
    {
        Gson gson=new Gson();
        xx c=new xx();
        c.setTt(new ObservableBoolean(true));
        c.setMm(new ObservableField<String>("SA"));
        System.out.println(gson.toJson(c));
//        String gg="{\"tt\":true,\"mm\":\"SA\"}";
//        c=gson.fromJson(gg,xx.class);
//        System.out.println(c.getMm().get());
    }
}
 class xx
{

   private  ObservableBoolean   tt;

   private ObservableField<String> mm;

    public ObservableBoolean getTt() {
        return tt;
    }

    public void setTt(ObservableBoolean tt) {
        this.tt = tt;
    }

    public ObservableField<String> getMm() {
        return mm;
    }

    public void setMm(ObservableField<String> mm) {
        this.mm = mm;
    }
}
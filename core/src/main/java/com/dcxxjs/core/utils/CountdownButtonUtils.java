package com.dcxxjs.core.utils;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.LogRecord;

/**
 * 作    者：崔潞安
 * 联系电话：18603434199
 * 联系邮箱：38642363@qq.com
 * 修改时间：2019/10/24
 * 功能描述：倒计时帮助类
 **/
public class CountdownButtonUtils {
    private Handler handler = new Handler();

    private TextView view;
    //刚开始的值
    private String text;
    //倒计时
    private int countdown=60;

    private int countdownvalue=60;
    //输出格式
    private  String format="%d秒";
    public CountdownButtonUtils(TextView view,int countdown,String format)
    {
        this.view=view;
        text=view.getText().toString();
        this.countdown=countdown;
        this.countdownvalue=countdown;
        this.format=format;
    }
    public CountdownButtonUtils(TextView view)
    {
        this.view=view;
        text=view.getText().toString();
    }
    private void setViewText()
    {
        view.setText(String.format(format,countdown));
    }
    //启动
    public void start()
    {
        view.setEnabled(false);
        handler.post(setViewText);
    }
    Runnable setViewText=new Runnable() {
        @Override
        public void run() {
            if(view.isEnabled())
            {
                return;
            }
            countdown--;
            view.setText(String.format(format,countdown));
            if(countdown<=0)
            {
                stop();
            }
            else
            {
                handler.postDelayed(setViewText,1000);
            }
        }
    };
    public void cancle()
    {
        stop();
    }

    private void stop()
    {
        view.setEnabled(true);
        view.setText(text);
        countdown=countdownvalue;
    }
}

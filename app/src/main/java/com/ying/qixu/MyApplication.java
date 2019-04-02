package com.ying.qixu;

import android.Manifest;
import android.app.Application;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class MyApplication extends Application {
    public static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //友盟
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"5c77c98c61f564b0b300093a");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        application = this;

        //电商SDK初始化
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e("bai","初始化失败,错误码=" + code + " / 错误消息=" + msg);
            }
        });

    }}


package com.ying.qixu;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.trade.biz.AlibcTradeBiz;
import com.alibaba.baichuan.trade.common.AlibcTradeCommon;
import com.alibaba.baichuan.trade.common.adapter.ut.AlibcUserTracker;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    public static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();

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


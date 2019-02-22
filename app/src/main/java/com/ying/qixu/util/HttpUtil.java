package com.ying.qixu.util;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void SendOkHttpRequest(String Url, Callback callback){
        Log.e("打印信息","今入HttpUtil");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Url).build();
        client.newCall(request).enqueue(callback);
    }
}

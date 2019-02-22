package com.ying.qixu.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.Bean.newsBean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    //private static List<newsBean.ListBean> ListsBeanDate = new ArrayList<>();
   public static newsBean handleListsResponse(String response){

        if(!response.equals("")){
            Gson gson = new Gson();
            newsBean newsLists = gson.fromJson(response,newsBean.class);
            Log.e("百度", String.valueOf(newsLists.getCode()));
            //
         //   Log.e("Json", "handleDataResponse: "+ListDate);


        }
        return  null;
       }
}

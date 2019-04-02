package com.ying.qixu.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.ying.qixu.Bean.DetailBean;
import com.ying.qixu.Bean.GridViewBean;
import com.ying.qixu.R;
import com.ying.qixu.adpter.DetailAdapter;
import com.ying.qixu.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

import static android.util.Log.e;


public class PlayerActivity extends AppCompatActivity {
    public List<GridViewBean> gridViewBeans = new ArrayList<>();
    private TextView tv_content;  //简介控件
    private String TAG ="PlayActivity";
    private String content;//简介内容
    //自定义recyclerveiw的适配器
    private DetailAdapter detailAdapter;
    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    public List<DetailBean.ListBean> DetailBeanDate = new ArrayList<DetailBean.ListBean>();
    private String url; //播放地址
    private String apiUrl = "http://m.qixu8.cn/api.php/provide/vod/?ac=detail&ids=";
     private String[] tem;
     private String Ress;
    private String name;
    private TextView TV_name;
   private String temp[];
    private  JzvdStd jzvdStd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        jzvdStd = (JzvdStd)findViewById(R.id.Player);
        tv_content = findViewById(R.id.Tv_content);
        TV_name =findViewById(R.id.TV_name);
        mCollectRecyclerView = findViewById(R.id.rv_ji);
        Intent intent =getIntent();
        int ids = intent.getIntExtra("ID",0);
        apiUrl = apiUrl+ids;

        HttpUtil.SendOkHttpRequest(apiUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e(TAG, "onFailure: 失败" );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Gson gson  = new Gson();
                DetailBean newsData =  gson.fromJson(responseText,DetailBean.class);
                DetailBeanDate  = newsData.getList();

                /*
                * getVod_play_url  ： 播放地址
                * getVod_name   ： 视频名字
                * */
                for ( DetailBean.ListBean ABC : DetailBeanDate){
                    // Log.d("TAG" , ABC.getVod_name());
                    name = ABC.getVod_name();
                    url =ABC.getVod_play_url();
                    content = ABC.getVod_content();

                }
             /*
             * 集数数据处理URL
             * 数据 ：第01集$https://www.qigaituan.com/20190107/sr4rpHrs/index.m3u8#第02集$https://www.qigaituan.com/20190206/Tn01QzEl/index.m3u8$$$第01集$https://youku.xlabzjx.com/20190205/T4gpJ2DS/index.m3u8#
             * 集数和链接之间的分割符 $
             * 集数与集数之间的分割符 #
             * 不同播放器分隔符 $$$
             * */

                tem = url.split("#");
                for (int i =0;i<tem.length;i++){
                    temp = tem[i].split("[$]");
                    GridViewBean  gridViewBean = new GridViewBean();
                    gridViewBean.setJishu(temp[0]);
                    gridViewBean.setLianjie(temp[1]);
                    gridViewBeans.add(gridViewBean);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       tv_content.setText("\t\t"+content);
                       TV_name.setText(name);
                       jzvdStd.setUp(temp[1],temp[0],Jzvd.SCREEN_WINDOW_NORMAL);
                       detailAdapter = new DetailAdapter(PlayerActivity.this, (ArrayList<GridViewBean>) gridViewBeans);
                       mCollectRecyclerView.setAdapter(detailAdapter);
                       mCollectRecyclerView.setLayoutManager(new GridLayoutManager(PlayerActivity.this,6));
                       detailAdapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
                           @Override
                           public void OnItemClick(View view, GridViewBean data) {
                               jzvdStd.setUp(data.getLianjie(),name+"_"+data.getJishu(),Jzvd.SCREEN_WINDOW_NORMAL);
                               jzvdStd.startButton.performClick();

                           }
                       });
                        }
                });

                }

        });


    }
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
}

}


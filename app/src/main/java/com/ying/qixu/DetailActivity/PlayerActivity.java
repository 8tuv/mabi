package com.ying.qixu.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ying.qixu.Bean.DetailBean;
import com.ying.qixu.Bean.GridViewBean;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.R;
import com.ying.qixu.adpter.DetailAdapter;
import com.ying.qixu.adpter.NewsRecyclerAdapter;
import com.ying.qixu.util.HttpUtil;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.PendingIntent.getActivity;
import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;


public class PlayerActivity extends AppCompatActivity {
    public List<GridViewBean> gridViewBeans = new ArrayList<>();
    private TextView tv_content;  //简介控件

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        JzvdStd jzvdStd = (JzvdStd)findViewById(R.id.Player);
        tv_content = findViewById(R.id.Tv_content);
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
                    String temp[] = tem[i].split("[$]");
                    GridViewBean  gridViewBean = new GridViewBean();
                    gridViewBean.setJishu(temp[0]);
                    gridViewBean.setLianjie(temp[1]);
                    gridViewBeans.add(gridViewBean);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       tv_content.setText("\t\t"+content);

                       //jzvdStd.setUp(tem[0],name,Jzvd.SCREEN_WINDOW_NORMAL);
                       detailAdapter = new DetailAdapter(PlayerActivity.this, (ArrayList<GridViewBean>) gridViewBeans);
                       mCollectRecyclerView.setAdapter(detailAdapter);
                       mCollectRecyclerView.setLayoutManager(new GridLayoutManager(PlayerActivity.this,5));
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

       /*
*/



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
}}


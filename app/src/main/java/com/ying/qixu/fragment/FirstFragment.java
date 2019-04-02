package com.ying.qixu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.DetailActivity.PlayerActivity;
import com.ying.qixu.R;
import com.ying.qixu.adpter.NewsRecyclerAdapter;
import com.ying.qixu.util.HttpUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;

public  class FirstFragment extends BaseFragment{

        //定义以newsBean实体类为对象的数据集合
        public List<newsBean.ListBean> ListsBeanDate = new ArrayList<newsBean.ListBean>();  //这是全局的数据 再添加一个局部的数据然后add一下就不会重复
        private String Url= "http://m.qixu8.cn/api.php/provide/vod/?ac=list&pg=";
        private int pg=1;  //页数
        private View view;//定义view用来设置fragment的layout

        public RecyclerView mCollectRecyclerView;//定义RecyclerView
        private NewsRecyclerAdapter newsRecyclerAdapter;          //自定义recyclerveiw的适配器
        private   Gson gson  = new Gson();  //json解析库
        private Banner banner;  //轮番图
        private ArrayList<String> list_path;
        private ArrayList<String> list_title;
        private  SmartRefreshLayout refreshLayout;  //下拉刷新，上拉加载
    //创建一个fragment
            public static FirstFragment newInstance() {
            com.ying.qixu.fragment.FirstFragment fragment = new FirstFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_base, container,false);
            banner =view.findViewById(R.id.banner);
            refreshLayout =view.findViewById(R.id.refresh);
refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
            //放图片地址的集合
            list_path = new ArrayList<>();
            //放标题的集合
            list_title = new ArrayList<>();

            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
            list_title.add("好好学习");
            list_title.add("天天向上");
            list_title.add("热爱劳动");
            list_title.add("不搞对象");
          //设置内置样式，共有六种可以点入方法内逐一体验使用。
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器，图片加载器在下方
            banner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            banner.setImages(list_path);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            banner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            banner.setBannerTitles(list_title);
            //设置轮播间隔时间
            banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            banner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Log.i("tag", "你点了第"+position+"张轮播图");
                        }
                    })
                    //必须最后调用的方法，启动轮播图。
                    .start();
refreshLayout.setOnRefreshListener(new OnRefreshListener() {
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        loadData();
        refreshLayout.finishRefresh();//传入false表示刷新失败

    }
});
refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getMoreData();
        refreshLayout.finishLoadMore();
    }
});

            return view;
        }

    @Override
    protected Object getContentLayout() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {
        HttpUtil.SendOkHttpRequest(Url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e(TAG, "onFailure: 失败" );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //okHttp 接受到的数据
                String responseText = response.body().string();
                getJsonDta(responseText);
            }
        });
    }
//    加载更多数据
    private void getMoreData(){
                ++pg;
                Log.e("UUUU",Url+pg);
                  HttpUtil.SendOkHttpRequest(Url+pg, new Callback() {

                      @Override
                      public void onFailure(Call call, IOException e) {

                      }

                      @Override
                      public void onResponse(Call call, Response response) throws IOException {
                          String responseText = response.body().string();
                          List<newsBean.ListBean> ListsData = new ArrayList<newsBean.ListBean>(); //局部数据

                          newsBean newsData =  gson.fromJson(responseText,newsBean.class);
                          ListsData = newsData.getList();
                          ListsBeanDate.addAll(ListsData);

                          //创建adapter
                          getActivity().runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  newsRecyclerAdapter.notifyDataSetChanged();
                              }
                          });

                      }
                  });
              }

//    json
    private void getJsonDta(String responseText){

        newsBean newsData =  gson.fromJson(responseText,newsBean.class);
        ListsBeanDate =newsData.getList();
        //获取RecyclerView
        mCollectRecyclerView= (RecyclerView) view.findViewById(R.id.rv_date);

        //创建adapter
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                    newsRecyclerAdapter = new NewsRecyclerAdapter(getActivity(), (ArrayList<newsBean.ListBean>) ListsBeanDate);
                    //给RecyclerView设置adapter
                    mCollectRecyclerView.setAdapter(newsRecyclerAdapter);


                mCollectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
                newsRecyclerAdapter.setOnItemClickListener(new NewsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, newsBean.ListBean data) {
                        //此处进行监听事件的业务处理
                        Intent intent =new Intent();
                        intent.setClass(getContext(),PlayerActivity.class);
                        intent.putExtra("ID",data.getVod_id());
                        startActivity(intent);
                    }
                });
            }
        });

    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

}

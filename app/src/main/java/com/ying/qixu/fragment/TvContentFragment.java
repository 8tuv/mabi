package com.ying.qixu.fragment;
/*
*    @author: qixu8
*    @time:2019.3.10
 *    这个fragment是除首页，剩下全部的
*
*     */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;
import static android.util.Log.getStackTraceString;

public class TvContentFragment extends BaseFragment {

    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    //自定义recyclerveiw的适配器
    private NewsRecyclerAdapter newsRecyclerAdapter;
    public List<newsBean.ListBean> ListsBeanDate = new ArrayList<newsBean.ListBean>();
    private String Url= "http://m.qixu8.cn/api.php/provide/vod/?ac=list&t=";
    private String  Path;
    private int pg = 1;
    private  Gson gson  = new Gson();
    private RecyclerView tvContent;
    private View view;//定义view用来设置fragment的layout
    private SmartRefreshLayout refreshLayout;

    @Override
    protected Object getContentLayout() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        String string = bundle.getString("name");
        Path =Url +string;
        HttpUtil.SendOkHttpRequest(Path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e(TAG, "onFailure: 失败" );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //okHttp 接受到的数据
                String responseText = response.body().string();
                getJson(responseText);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_content, container, false);
        tvContent =view.findViewById(R.id.rv_tab_date);
        refreshLayout =view.findViewById(R.id.other_smartRefresh);
       // 下拉刷新，上拉加载配置相关
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                getMoreDate();
            }
        });


        return view;
    }

    private void getMoreDate() {
        ++pg;
        Log.d("uu",Path +"&pg="+ pg);
        HttpUtil.SendOkHttpRequest(Path +"&pg="+ pg, new Callback() {

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

    private void getJson(String text){

    newsBean newsData =  gson.fromJson(text,newsBean.class);
    ListsBeanDate  = newsData.getList();
    //获取RecyclerView
    mCollectRecyclerView=(RecyclerView) view.findViewById(R.id.rv_tab_date);
    //创建adapter
    getActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
            newsRecyclerAdapter = new NewsRecyclerAdapter(getActivity(), (ArrayList<newsBean.ListBean>) ListsBeanDate);
            //给RecyclerView设置adapter
            mCollectRecyclerView.setAdapter(newsRecyclerAdapter);
            //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
            //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
            mCollectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

            //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
            newsRecyclerAdapter.setOnItemClickListener(new NewsRecyclerAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, newsBean.ListBean data) {
                    //此处进行监听事件的业务处理
                    Intent intent =new Intent();
                    intent.setClass(getContext(), PlayerActivity.class);
                    intent.putExtra("ID",data.getVod_id());
                    startActivity(intent);
                }
            });
        }
    });
}
}

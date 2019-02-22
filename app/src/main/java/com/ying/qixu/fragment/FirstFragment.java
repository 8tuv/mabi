package com.ying.qixu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.DetailActivity.PlayerActivity;
import com.ying.qixu.R;
import com.ying.qixu.adpter.NewsRecyclerAdapter;
import com.ying.qixu.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;
import static android.util.Log.i;

public  class FirstFragment extends BaseFragment{

        //定义以newsBean实体类为对象的数据集合
        public List<newsBean.ListBean> ListsBeanDate = new ArrayList<newsBean.ListBean>();
        private String Url= "http://m.qixu8.cn/api.php/provide/vod/?ac=list";
        private View view;//定义view用来设置fragment的layout
        public RecyclerView mCollectRecyclerView;//定义RecyclerView
        //自定义recyclerveiw的适配器
        private NewsRecyclerAdapter newsRecyclerAdapter;

        public static FirstFragment newInstance() {
            com.ying.qixu.fragment.FirstFragment fragment = new FirstFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_base, container,false);
            //对recycleview进行配置
            loadData();
            //模拟数据


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
                Gson gson  = new Gson();
                newsBean newsData =  gson.fromJson(responseText,newsBean.class);
                ListsBeanDate  = newsData.getList();
                //获取RecyclerView
                mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.rv_date);
                //创建adapter
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsRecyclerAdapter = new NewsRecyclerAdapter(getActivity(), (ArrayList<newsBean.ListBean>) ListsBeanDate);
                        //给RecyclerView设置adapter
                        mCollectRecyclerView.setAdapter(newsRecyclerAdapter);
                        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
                        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
                        mCollectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                        //设置item的分割线
                        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
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
        });


    }

}

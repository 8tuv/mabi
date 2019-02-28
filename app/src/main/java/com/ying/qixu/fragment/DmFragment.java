package com.ying.qixu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.ying.qixu.adpter.TvContentFragmentAdapter;
import com.ying.qixu.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;

public class DmFragment extends BaseFragment {
    private TabLayout tabLayout;

    private ViewPager view_pager;
    private View view;//定义view用来设置fragment的layout
    private TvContentFragmentAdapter adapter;
    private List<String> names;
    //mainactivity 调用的
    public static DmFragment newInstance() {
        DmFragment fragment = new DmFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_other_tab, container, false);
        tabLayout =view.findViewById(R.id.other_tab_layout);
        view_pager =view.findViewById(R.id.other_view_pager);
        //实际上是 通用的，不单单是TV栏目
        adapter = new TvContentFragmentAdapter(getChildFragmentManager());
        view_pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(view_pager);

        adapter.setList(names);
        return view;
    }

    @Override
    protected Object getContentLayout() {
        return null;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    protected void initData(Bundle savedInstanceState) {
        names = new ArrayList<>();
        names.add("国产动漫");
        names.add("日本动漫");
        names.add("单集动漫");  //id 20--23
        names.add("剧场版");
    }
    }

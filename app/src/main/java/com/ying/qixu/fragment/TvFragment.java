package com.ying.qixu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.qixu.R;
import com.ying.qixu.adpter.TvContentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TvFragment extends BaseFragment {

    private TabLayout tabLayout;

   private   ViewPager view_pager;
   private View view;//定义view用来设置fragment的layout
    private TvContentFragmentAdapter adapter;
    private List<String> names;

    public static TvFragment newInstance() {
        TvFragment fragment = new TvFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, container, false);
        tabLayout =view.findViewById(R.id.tab_layout);
        view_pager =view.findViewById(R.id.view_pager);

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
        names.add("国产剧");  //id 13
        names.add("欧美剧");  //id 16
    }
}

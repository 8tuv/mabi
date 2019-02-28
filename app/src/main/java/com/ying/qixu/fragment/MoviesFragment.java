package com.ying.qixu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.qixu.R;
import com.ying.qixu.adpter.TvContentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

    public class MoviesFragment extends BaseFragment {

        private TabLayout tabLayout;

        private ViewPager view_pager;
        private View view;//定义view用来设置fragment的layout
        private TvContentFragmentAdapter adapter;
        private List<String> names;
        //mainactivity 调用的
        public static MoviesFragment newInstance() {
            MoviesFragment fragment = new MoviesFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_other_tab, container, false);
            tabLayout =view.findViewById(R.id.other_tab_layout);
            view_pager =view.findViewById(R.id.other_view_pager);
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
            names.add("动作片");  //id 6~~12
            names.add("喜剧片");
            names.add("爱情片");
            names.add("科幻片");
            names.add("恐怖片");
            names.add("剧情片");
            names.add("战争片");
        }

    }

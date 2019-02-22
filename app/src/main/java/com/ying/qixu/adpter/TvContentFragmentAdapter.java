package com.ying.qixu.adpter;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



import com.ying.qixu.fragment.TvContentFragment;

import java.util.ArrayList;
import java.util.List;

public class TvContentFragmentAdapter extends FragmentPagerAdapter {
    private List<String> names =new ArrayList<>();

    /**
     * 数据列表
     *
     * @param datas
     */
    public void setList(List<String> datas) {
        this.names.clear();
        this.names.addAll(datas);
        notifyDataSetChanged();
    }
    public TvContentFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TvContentFragment fragment = new TvContentFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    public CharSequence getPageTitle(int position) {
        String plateName = names.get(position);
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}

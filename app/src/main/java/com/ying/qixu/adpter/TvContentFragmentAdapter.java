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
        Bundle bundle =new Bundle();
        if (names.get(position).endsWith("国产剧")){
            bundle.putString("name","13");
        }
        else if (names.get(position).endsWith("欧美剧")){
            bundle.putString("name","16");
        } else if (names.get(position).endsWith("动作片")){
            bundle.putString("name","6");
        }
        else if (names.get(position).endsWith("喜剧片")){
            bundle.putString("name","7");
        } else if (names.get(position).endsWith("爱情片")){
            bundle.putString("name","8");
        } else if (names.get(position).endsWith("科幻片")){
            bundle.putString("name","9");
        }else if (names.get(position).endsWith("恐怖片")){
            bundle.putString("name","10");
        }else if (names.get(position).endsWith("剧情片")){
            bundle.putString("name","11");
        }else if (names.get(position).endsWith("单集动漫")){
            bundle.putString("name","20");
        }else if (names.get(position).endsWith("国产动漫")){
            bundle.putString("name","21");
        }else if (names.get(position).endsWith("日本动漫")){
            bundle.putString("name","22");
        }else if (names.get(position).endsWith("剧场版")){
            bundle.putString("name","23");
        }
        fragment.setArguments(bundle);
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

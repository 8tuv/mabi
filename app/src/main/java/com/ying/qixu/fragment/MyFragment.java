package com.ying.qixu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MyFragment extends Fragment {
    public static MyFragment newInstance(String info) {
        Bundle bundle = new Bundle();
        bundle.putString("title",info);
        com.ying.qixu.fragment.MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}

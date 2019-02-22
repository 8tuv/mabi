package com.ying.qixu.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ying.qixu.R;

public abstract class BaseFragment extends Fragment {

    private View view;//定义view用来设置fragment的layout
    /**
     * View有没有加载过
     */
    protected boolean isViewInitiated=false;
    /**
     * 页面是否可见
     */
    protected boolean isVisibleToUser=false;
    /**
     * 是不是加载过
     */
    protected boolean isDataInitiated =false;
    private Activity mActivity;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base, container,false);
        return view;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isViewInitiated) {
            initTitle();
            bindView(view);
            initView(view);
            initNet();
        }
        isViewInitiated = true;
        loadData();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            isVisibleToUser =true;
            lazyLoad();
        }
        else {
            isVisibleToUser =false;
        }
    }
    @Override
    public void onDestroyView() {
        unbindView(view);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mActivity = null;
        view = null;
        super.onDestroy();
    }

    /**
     * 创建View
     */
    private View createContentView(ViewGroup parent) {
        Object layout = getContentLayout();
        View contentView = null;
        if (layout instanceof View) {
            contentView = (View) layout;
        } else if (layout instanceof Integer) {
            contentView = getLayoutInflater().inflate((Integer) layout, parent, false);
        }
        if (contentView == null) {
            new IllegalArgumentException("getContentLayout must View or LayoutId");
        }
        return contentView;
    }

    protected abstract Object getContentLayout();

    /**
     * 1. 初始化数据，包括上个页面传递过来的数据在这个方法做
     */
    protected abstract  void initData(Bundle savedInstanceState);

    /**
     * 3.1 如果要创建标题
     */
    protected void initTitle() {

    }

    /**
     * 3.2绑定View
     */
    protected void bindView(View contentView) {

    }

    /**
     * 4. 初始化View
     */
    protected void initView(View contentView) {

    }

    /**
     * 5. 初始化网络
     */
    protected void initNet() {

    }

    /**
     * 懒加载
     */
    protected abstract void loadData() ;


    /**
     * 6. 懒加载，Fragment可见的时候调用这个方法，而且只调用一次
     */
    protected void lazyLoad() {
        if (isViewInitiated &&isVisibleToUser){
            loadData();
            isViewInitiated =false;
            isVisibleToUser =false;
        }

    }

    /**
     * 解绑contentView
     */
    protected void unbindView(View contentView) {

    }


    /**
     * 打开Activity
     */
    public final void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    /**
     * 打开Activity
     */
    public final void startActivity(Class<?> clazz, @Nullable Bundle options) {
        Intent intent = new Intent(getAppActivity(), clazz);
        if (options != null) {
            intent.putExtras(options);
        }
        startActivity(intent);
    }

    /**
     * 获取当前的Activity
     */
    public final Activity getAppActivity() {
        return mActivity;
    }

    /**
     * 设置TextView
     */
    public void setText(TextView textView, CharSequence text) {
        if (textView != null && text != null) {
            textView.setText(text);
        }
    }

    /**
     * 闭关页面
     */
    public void finish() {
        if (mActivity != null) {
            mActivity.finish();
        }
    }

}

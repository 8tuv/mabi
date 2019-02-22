package com.ying.qixu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.DetailActivity.PlayerActivity;
import com.ying.qixu.adpter.NewsRecyclerAdapter;
import com.ying.qixu.adpter.SearchAdapter;
import com.ying.qixu.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;
import static android.util.Log.e;


public class SearchResultActivity extends AppCompatActivity {
    private String api="http://m.qixu8.cn/api.php/provide/vod/?ac=list&wd=";
    private  String apiUrl;
    public List<newsBean.ListBean> ListsBeanDate = new ArrayList<newsBean.ListBean>();
    public RecyclerView mRecyclerView;//定义RecyclerView
    private SearchAdapter searchAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Toolbar toolbar = findViewById(R.id.tb_search);
        setSupportActionBar(toolbar);
        Intent intent =getIntent();
        String WD = intent.getStringExtra("WD");
        apiUrl=api+WD;
        loadDatas(apiUrl);



    }

    private void loadDatas(String apiUrl) {
        HttpUtil.SendOkHttpRequest(apiUrl, new Callback() {
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
                mRecyclerView=(RecyclerView) findViewById(R.id.rv_search);
                //创建adapter
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchAdapter = new SearchAdapter(SearchResultActivity.this,  (ArrayList<newsBean.ListBean>) ListsBeanDate);
                        //给RecyclerView设置adapter
                        mRecyclerView.setAdapter(searchAdapter);
                        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
                        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this,LinearLayoutManager.VERTICAL,false));
                        //设置item的分割线
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(SearchResultActivity.this, DividerItemDecoration.VERTICAL));
                        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
                        searchAdapter.setOnItemClickListener(new NewsRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(View view, newsBean.ListBean data) {
                                //此处进行监听事件的业务处理
                                Intent intent =new Intent();
                                intent.setClass(SearchResultActivity.this, PlayerActivity.class);
                                intent.putExtra("ID",data.getVod_id());
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apiUrl=api+query;
                loadDatas(apiUrl);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });
        return true;
    }

}

package com.ying.qixu.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myViewHodler>  {
    private Context context;
    private ArrayList<newsBean.ListBean> newsBeanList;
    //创建构造函数
    public SearchAdapter(Context context, ArrayList<newsBean.ListBean> newsBeanList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.newsBeanList = newsBeanList;//实体类数据ArrayList
    }



    @NonNull
    @Override
    public SearchAdapter.myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建自定义布局

        View itemView = View.inflate(context, R.layout.rv_search,null);
        return new SearchAdapter.myViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHodler holder, int position) {
        newsBean.ListBean data = newsBeanList.get(position);
        if (newsBeanList.isEmpty()){
            holder.vod_name.setText("暂时没有结果");
            holder.vod_actor.setText("null");
            Glide.with(context).load(R.mipmap.null_data).into(holder.search_imageView);
        }

        holder.vod_name.setText("名称："+data.getVod_name());
        holder.vod_actor.setText("演员："+data.getVod_actor());
        Glide.with(context).load(data.getVod_pic()).into(holder.search_imageView);
    }


    @Override
    public int getItemCount() {
        return newsBeanList.size();
    }


    class myViewHodler extends RecyclerView.ViewHolder {
        private TextView vod_name;
        private TextView vod_actor;
        private ImageView search_imageView;
        public myViewHodler(View itemView) {
            super(itemView);
            vod_name = (TextView) itemView.findViewById(R.id.seach_vod_name);
            vod_actor =itemView.findViewById(R.id.seach_vod_actor);
            search_imageView =(ImageView) itemView.findViewById(R.id.search_imageView) ;

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, newsBeanList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }
    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, newsBean.ListBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private NewsRecyclerAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(NewsRecyclerAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

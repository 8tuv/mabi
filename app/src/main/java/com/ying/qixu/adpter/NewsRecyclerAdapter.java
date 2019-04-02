package com.ying.qixu.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.RequestBuilder;
import com.ying.qixu.Bean.newsBean;
import com.ying.qixu.R;

import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.myViewHodler> {

    private Context context;
    private ArrayList<newsBean.ListBean> newsBeanList;
    //创建构造函数
    public NewsRecyclerAdapter(Context context, ArrayList<newsBean.ListBean> newsBeanList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.newsBeanList = newsBeanList;//实体类数据ArrayList
    }



    @NonNull
    @Override
    public NewsRecyclerAdapter.myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建自定义布局

        View itemView = View.inflate(context,R.layout.rv_date,null);
        return new myViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerAdapter.myViewHodler holder, int position) {
        //根据位置position绑定数据
        newsBean.ListBean data = newsBeanList.get(position);
        holder.mItemName.setText(data.getVod_name());
        holder.vod_remarks.setText(data.getVod_remarks());

        Glide.with(context).load(data.getVod_pic()).error(R.mipmap.null_data).into(holder.mImageview);


    }

    @Override
    public int getItemCount() {
        return newsBeanList.size();
    }


    class myViewHodler extends RecyclerView.ViewHolder {
        private TextView mItemName;
        private TextView vod_remarks;
        private ImageView mImageview;
        public myViewHodler(View itemView) {
            super(itemView);
            mItemName = (TextView) itemView.findViewById(R.id.Tv_name);
            mImageview =(ImageView) itemView.findViewById(R.id.image_tu) ;
            vod_remarks =itemView.findViewById(R.id.vod_remarks);
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
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

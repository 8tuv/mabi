package com.ying.qixu.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ying.qixu.Bean.GridViewBean;
import com.ying.qixu.R;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.myViewHodler> {
    private Context context;
    private ArrayList<GridViewBean> data;
    //创建构造函数
    public DetailAdapter(Context context, ArrayList<GridViewBean> data) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.data = data;//实体类数据ArrayList
    }

    @NonNull
    @Override

    public DetailAdapter.myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.rv_ji,null);
        return new myViewHodler(view);
    }
    //根据位置position绑定数据
    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.myViewHodler holder, int position) {
            GridViewBean datas = data.get(position);
            holder.mjishu.setText(datas.getJishu());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHodler extends RecyclerView.ViewHolder {
        private TextView mjishu;
     /*   private JzvdStd jzvdStd;  //播放器
       private TextView tv_content; //简介*/
        public myViewHodler(View itemView) {
            super(itemView);
             mjishu = (TextView) itemView.findViewById(R.id.jishu);

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, data.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, GridViewBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private DetailAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

package com.qf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.entity.ContentNewsEntity;
import com.qf.sep12bantangexam.R;
import com.qf.util.DownUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/12.
 */
public class ListViewPagerAdapter extends BaseAdapter {

    private Context context;
    private List<ContentNewsEntity.DataBean.TopicBean> datas;
    //构造方法
    public ListViewPagerAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<ContentNewsEntity.DataBean.TopicBean> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder != null){
            holder = (ViewHolder) convertView.getTag();
        }else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news,null);
            holder.ivNewspic = (ImageView) convertView.findViewById(R.id.iv_newspic);
            holder.tvSubject = (TextView) convertView.findViewById(R.id.tv_subject);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.views = (TextView) convertView.findViewById(R.id.views);
            holder.likes = (TextView) convertView.findViewById(R.id.likes);
            convertView.setTag(holder);
        }
        holder.tvSubject.setText(datas.get(position).getTitle());
        holder.nickname.setText(datas.get(position).getUser().getNickname());
        holder.views.setText(datas.get(position).getViews());
        holder.likes.setText(datas.get(position).getLikes());
        new DownUtil().with(holder.ivNewspic).downBitmap(datas.get(position).getPic());


        return convertView;
    }

    class ViewHolder{
        ImageView ivNewspic;
        TextView tvSubject, nickname, views, likes;
    }
}

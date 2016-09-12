package com.qf.flag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qf.adapter.ListViewPagerAdapter;
import com.qf.entity.ContentNewsEntity;
import com.qf.sep12bantangexam.R;
import com.qf.util.DownUtil;

/**
 * Created by lenovo on 2016/9/12.
 */
public class FragmentNews extends Fragment implements DownUtil.OnDownListener {
    private ListView listView;
    private ListViewPagerAdapter adapter;

    public static FragmentNews getInstance(String url){
        FragmentNews fragmentNews = new FragmentNews();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragmentNews.setArguments(bundle);
        return fragmentNews;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_newslv,null);
        listView = (ListView) view.findViewById(R.id.lv);
        adapter = new ListViewPagerAdapter(getActivity());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        new DownUtil().setOnDownListener(this).downJSON(url);

    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, ContentNewsEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            ContentNewsEntity contentNewsEntity = (ContentNewsEntity) object;
            adapter.setDatas(contentNewsEntity.getData().getTopic());
        }

    }
}

package com.qf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qf.entity.HomeHeadEntity;
import com.qf.flag.FragmentNews;
import com.qf.util.Constant;

import java.util.List;

/**
 * Created by lenovo on 2016/9/12.
 */
public class FragNewsPagerAdapter extends FragmentPagerAdapter {
    private List<HomeHeadEntity.DataBean.CategoryElementBean> datas;
    public FragNewsPagerAdapter(FragmentManager fm, List<HomeHeadEntity.DataBean.CategoryElementBean> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        String url;
        switch (position) {
            case 0:
                //精选
                url = Constant.URL_HOME_TUIJIAN;
                break;
            case 1:
            case 2:
                //原创精选 一周热评
                url = String.format(Constant.URL_HOME_SECONED,datas.get(position).getExtend());
                break;
            default:
                //其他
                url = String.format(Constant.URL_HOME_THRID,datas.get(position).getExtend());
                break;
        }
        return FragmentNews.getInstance(url);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}

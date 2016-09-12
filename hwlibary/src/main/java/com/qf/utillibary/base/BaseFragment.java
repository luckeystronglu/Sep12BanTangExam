package com.qf.utillibary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ken on 2016/8/1.
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    /**
     * 该方法会紧随onCreateView被调用后调用
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadDatas();
    }

    /**
     * 查找页面布局ID所对应的控件对象，不用强制转换
     * @param resId
     * @param <T>
     * @return
     */
    protected <T> T findViewByIds(int resId,View view){
        return (T) view.findViewById(resId);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBundle(getArguments());
    }

    //获得bundle数据
    protected void getBundle(Bundle bundle) {

    }


    /**
     * 设置fragment需要展示的布局ID
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化
     * @param view
     */
    protected void init(View view){

    }

    /**
     * 加载数据
     */
    protected void loadDatas(){

    }

}

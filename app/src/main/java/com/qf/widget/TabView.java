package com.qf.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.entity.HomeHeadEntity;
import com.qf.sep12bantangexam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager搭配的Tab控件
 * Created by Ken on 2016/7/28.
 */
public class TabView extends HorizontalScrollView implements View.OnClickListener {

    //滑动小光标
    private View view;
    private LinearLayout.LayoutParams layoutParams;

    private ViewPager viewPager;

    //tab选项卡的线性布局
    private LinearLayout tabLinearLayout;

    private List<HomeHeadEntity.DataBean.CategoryElementBean> tabs;//选项卡内容
    private List<Integer> lengthList;//长度的集合

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        //当第三个参数为true时，表示布局中的所有控件直接加载到this中
        LayoutInflater.from(getContext()).inflate(R.layout.tab_guide_view, this, true);
        view = findViewById(R.id.view);
        layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        tabLinearLayout = (LinearLayout) findViewById(R.id.ll);
    }

    /**
     * 设置Tab选项卡的内容
     * @param tabs
     */
    public void setTabs(List<HomeHeadEntity.DataBean.CategoryElementBean> tabs){
        this.tabs = tabs;

        lengthList = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setText(tabs.get(i).getTitle());
            tv.setTextSize(14);
            tv.setTag(i);
            tv.setOnClickListener(this);
            tv.setPadding(30, 10, 30, 10);
            tv.setLayoutParams(layoutParams);

            //测量控件
            tv.measure(0, 0);
            lengthList.add(tv.getMeasuredWidth());
            tabLinearLayout.addView(tv);
        }
        for (HomeHeadEntity.DataBean.CategoryElementBean tab : tabs) {

        }




//        for (HomeHeadEntity.DataBean.CategoryElementBean tab : tabs) {
//            TextView tv = new TextView(getContext());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            tv.setText(tab.getTitle());
//            tv.setTextSize(14);
//
//            tv.setPadding(30, 10, 30, 10);
//            tv.setLayoutParams(layoutParams);
//
//            //测量控件
//            tv.measure(0, 0);
//            lengthList.add(tv.getMeasuredWidth());
//            tabLinearLayout.addView(tv);
//        }

        //把小光标的宽度设置为第一个TextView的宽度
        view.getLayoutParams().width = lengthList.get(0);
    }


    /**
     * 设置需要联动的ViewPager对象
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        if (viewPager != null) {
            this.viewPager = viewPager;
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //计算光标需要走的距离
                    int length = 0;
                    for (int i = 0; i < position; i++) {
                        length += lengthList.get(i);
                    }

                    layoutParams.leftMargin = (int) (length + lengthList.get(position) * positionOffset);


                    //光标的宽度
                    if (position != lengthList.size() - 1) {
                        layoutParams.width = (int) (lengthList.get(position) + (lengthList.get(position + 1) - lengthList.get(position)) * positionOffset);
                    } else {
                        layoutParams.width = lengthList.get(position);
                    }
                    view.setLayoutParams(layoutParams);

                    TabView.this.smoothScrollTo(layoutParams.leftMargin - 80, 0);//这个带动画
                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < tabLinearLayout.getChildCount(); i++) {
                        TextView tv = (TextView) tabLinearLayout.getChildAt(i);
                        if (position != i) {
                            tv.setTextColor(Color.BLACK);
                        } else {
                            tv.setTextColor(Color.BLUE);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        if(viewPager != null){
            viewPager.setCurrentItem(index);
        }
    }
}

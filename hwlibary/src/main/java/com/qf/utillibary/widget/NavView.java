package com.qf.utillibary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.utillibary.R;

/**
 * 导航小控件  就是那个...
 * Created by Ken on 2016/8/3.
 */
public class NavView extends FrameLayout{

    private LinearLayout ll_unchecked, ll_checked;
    private ImageView iv_checkec;
    private LinearLayout.LayoutParams layoutParams;

    //自定义属性
    private int checkedImage, unCheckedImage;

    public NavView(Context context) {
        this(context, null);
    }

    public NavView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        paresAttrs(attrs);
    }

    /**
     * 初始化方法
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_navview, this, true);
        ll_unchecked = (LinearLayout) findViewById(R.id.ll_unchecked);
        ll_checked = (LinearLayout) findViewById(R.id.ll_checked);
    }

    /**
     * 解析自定义属性
     * @param attrs
     */
    private void paresAttrs(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.NavView);
        checkedImage = typedArray.getResourceId(R.styleable.NavView_checked, 0);
        unCheckedImage = typedArray.getResourceId(R.styleable.NavView_unchecked, 0);
        typedArray.recycle();
    }

    /**
     * 设置ViewPager的页数
     */
    public void setCount(int count){
        ll_unchecked.removeAllViews();
        ll_checked.removeAllViews();

        if(count <= 0){
            return;
        }

        //添加未被选中的图片
        for(int i = 0; i < count; i++){
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(unCheckedImage);
            iv.setPadding(10, 0, 10, 0);
            ll_unchecked.addView(iv);
        }

        //添加被选中的图片
        iv_checkec = new ImageView(getContext());
        iv_checkec.setImageResource(checkedImage);
        iv_checkec.setPadding(10, 0, 10, 0);
        ll_checked.addView(iv_checkec);
        layoutParams = (LinearLayout.LayoutParams) iv_checkec.getLayoutParams();
    }

    /**
     * 设置需要联动的ViewPager对象
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager){
        if(viewPager != null){
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    layoutParams.leftMargin = (int) (iv_checkec.getMeasuredWidth() * (position + positionOffset));
                    iv_checkec.setLayoutParams(layoutParams);
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

}

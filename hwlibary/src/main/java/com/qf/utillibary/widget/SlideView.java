package com.qf.utillibary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.qf.utillibary.R;


/**
 * 侧边字母控件
 * Created by Ken on 2016/7/27.
 */
public class SlideView extends View{

    //需要绘制的内容
    private String[] strs = {"A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private Paint paint;
    private int mHeight;//每个文本所占的高度

    private int mindex = -1;//当前选中的字母下标

    /**
     * 接口：a();
     * 实现类：a(){ XXXXX }
     *
     *
     * 接口名 变量名 = new 实现类();
     * 变量名.a();
     *
     *
     * class SlideView{
     *
     *      OnSelectListener onselector;
     *      //public void setOnSelectListener(OnSelectListener onselector){
     *         // this.onselector = onselector;
     *     // }
     *
     *      //....
     *      onselector.selector();
     *
     *      //.....
     *      interface OnSelectListener{
     *          void selector();
     *      }
     * }
     *
     * class Activity{
     *     //.......
     *
     *     SlideView sv = findViewById();
     *     sv.onselector = new A();
     *
     *     class A implements OnSelectListener{
     *
     *     }
     * }

     *
     *
     * 定义接口变量
     */
    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public SlideView(Context context) {
        this(context, null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#88888888"));
        paint.setTextSize(getResources().getDimension(R.dimen.slide_size));

        mHeight = (int) (paint.descent() - paint.ascent());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i < strs.length; i++){
            if(mindex != -1 && mindex == i){
                paint.setColor(Color.parseColor("#0000ff"));
                canvas.drawText(strs[i], getWidth()/2 - paint.measureText(strs[i])/2, getPaddingTop() + mHeight * (i + 1), paint);
                paint.setColor(Color.parseColor("#88888888"));
            } else {
                canvas.drawText(strs[i], getWidth() / 2 - paint.measureText(strs[i]) / 2, getPaddingTop() + mHeight * (i + 1), paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureView(widthMeasureSpec, 0), measureView(heightMeasureSpec, 1));
    }

    /**
     * 测量控件的宽高
     * @param spec 空间值
     * @param type 测量的类型
     * @return
     */
    private int measureView(int spec, int type){
        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                if(type == 0){//测量宽度
                    size = (int) (paint.measureText(strs[0]) + getPaddingLeft() + getPaddingRight());
                } else {//测量高度
                    size = mHeight * strs.length + getPaddingTop() + getPaddingBottom();
                }
                break;
        }
        return size;
    }

    /**
     * 事件处理方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();//获取点击的y坐标
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                selectY(y);
                break;
            case MotionEvent.ACTION_UP:
                mindex = -1;
                invalidate();

                //表示抬手了
                if(onSelectListener != null){
                    onSelectListener.uptouch();
                }
                break;
        }
        return true;
    }

    private void selectY(int y){
        int index = y / mHeight;
        if(index < 0){
            index = 0;
        }
        if(index >= strs.length){
            index = strs.length - 1;
        }

        if(mindex == index){
            return;
        }

        //调用接口回调
        if(onSelectListener != null){
            onSelectListener.selector(strs[index]);
        }


        mindex = index;
        invalidate();

    }

    /**
     * 定义接口
     */
    public interface OnSelectListener{
        void selector(String s);
        void uptouch();
    }
}

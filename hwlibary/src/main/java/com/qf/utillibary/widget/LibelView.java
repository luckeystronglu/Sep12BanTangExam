package com.qf.utillibary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.qf.utillibary.R;


/**
 * 中间显示字母的View
 * Created by Ken on 2016/7/27.
 */
public class LibelView extends View{
    private Paint paint, paintText;

    private String text;

    public LibelView(Context context) {
        this(context, null);
    }

    public LibelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LibelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置需要显示的文本内容
     * @param text
     */
    public void setText(String text) {
        this.text = text;

        //将控件显示
        this.setVisibility(VISIBLE);

        invalidate();
    }

    /**
     * 初始化
     */
    private void init() {
        //默认是隐藏状态
        this.setVisibility(GONE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#8585e1"));

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(getResources().getDimension(R.dimen.slide_label));
        paintText.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2, getHeight()/2, getResources().getDimension(R.dimen.slide_circle), paint);
        if(text != null){
            canvas.drawText(text,
                    getWidth()/2 - paintText.measureText(text)/2,
                    getHeight()/2 + (paintText.descent() - paintText.ascent())/2 - paintText.descent(),
                    paintText);
        }
    }
}

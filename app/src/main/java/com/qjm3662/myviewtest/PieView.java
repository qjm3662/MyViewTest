package com.qjm3662.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by qjm3662 on 2016/12/7 0007.
 */

public class PieView extends View{
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //饼状图初始绘制角度
    private float mStartAngle = 0;
    //数据
    private ArrayList<PieData> mData;
    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint mPaint = new Paint();


    public PieView(Context context) {
        this(context, null);        //调用两个参数的构造
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);  //设置抗锯齿
    }

    /**
     * View的大小改变时调用该方法
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 绘制函数(每次屏幕刷新要求重新绘制时会调用该函数)
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null == mData)
            return;
        float currentStartAngle = mStartAngle;          //当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);      //将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2.0 * 0.8);//饼状图半径
        RectF rectF = new RectF(-r, -r, r, r);          //饼状图绘制区域
        for(int i = 0; i < mData.size(); i++){
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }

    }

//以下两个是对外的接口
    /**
     * 设置起始角度
     * @param mStartAngle
     */
    public void setStartAngle(int mStartAngle){
        this.mStartAngle = mStartAngle;
        //在更改了数据需要重绘界面时要调用invalidate()这个函数重新绘制。
        invalidate();   //刷新操作
    }

    /**
     * 设置数据
     * @param mData
     */
    public void setData(ArrayList<PieData> mData){
        this.mData = mData;
        initData(mData);
        invalidate();       //刷新
    }

    /**
     * 初始化数据
     * @param mData
     */
    private void initData(ArrayList<PieData> mData) {
        if(null == mData || mData.size() == 0){ //数据有问题，直接返回
            return;
        }
        float sumValue = 0;     //数据的总和
        for(int i = 0; i < mData.size(); i++){
            PieData pie = mData.get(i);
            sumValue += pie.getValue();     //计算数值和

            pie.setColor(mColors[i % mColors.length]);  //设置颜色
        }

        float sumAngle = 0;     //总角度
        float percentage;
        float angle;
        for(int i = 0; i < mData.size(); i++){
            PieData pie = mData.get(i);
            percentage = pie.getValue() / sumValue;
            angle = percentage * 360;

            pie.setPercentage(percentage);
            pie.setAngle(angle);

            sumAngle += angle;      //计算过程中可能会有精度损失，这样做保险一点
        }
    }
}

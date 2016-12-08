package com.qjm3662.myviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by qjm253 on 2016/12/8 0008.
 */

public class CheckView extends View{
    private static final int ANIM_NULL = 0;     //动画状态，没有
    private static final int ANIM_CHECK = 1;    //动画状态，开启
    private static final int ANIM_UNCHECK = 2;  //动画状态，结束

    private Context mContext;           //上下文
    private int mWidth, mHeight;
    private Handler mHandler;

    private Paint mPaint;
    private Bitmap okBitmap;

    private int animCurretnPage = -1;   //当前页码
    private int animMaxPage = 13;       //总页数
    private int animDuration = 500;     //动画持续时间
    private int animState = ANIM_NULL;  //动画状态

    private boolean isCheck = false;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContext();
    }

    private void initContext() {
        mContext = getContext();
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(animCurretnPage < animMaxPage && animCurretnPage >= 0){
                    invalidate();
                    if(animState == ANIM_NULL){
                        return;
                    }
                    if(animState == ANIM_CHECK){
                        animCurretnPage++;
                    }else if(animState == ANIM_UNCHECK){
                        animCurretnPage--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    System.out.println(animDuration +  "  " + animMaxPage);
                    Log.e("AAA", "Count=" + animCurretnPage);
                }else{
                    if(isCheck){
                        animCurretnPage = animMaxPage - 1;
                    }else{
                        animCurretnPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        //绘制圆形背景
        canvas.drawCircle(0, 0, 240, mPaint);

        //得出图像边长
        int slidLength = okBitmap.getHeight();

        //得到图像选区和实际绘制位置
        Rect src = new Rect(slidLength * animCurretnPage, 0, slidLength * (animCurretnPage + 1), slidLength);
        Rect dst = new Rect(-200, -200, 200, 200);

        //绘制
        canvas.drawBitmap(okBitmap, src, dst, null);
    }


    public void check(){
        if(animState != ANIM_NULL || isCheck)
            return;
        animState = ANIM_CHECK;
        animCurretnPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        System.out.println(animDuration +  "  " + animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck))
            return;
        animState = ANIM_UNCHECK;
        animCurretnPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }
}

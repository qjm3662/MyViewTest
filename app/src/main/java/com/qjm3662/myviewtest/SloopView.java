package com.qjm3662.myviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by qjm3662 on 2016/12/7 0007.
 */

public class SloopView extends View{

    private Paint mPaint = new Paint();  //创建一个画笔
    //宽高
    private int mWidth, mHeight;
    private boolean isFirstDraw = true;
    //Picture,可以理解为Canvas的录像机
    String text = "Robbin";
    private Picture mPicture = new Picture();

    private void recording(){
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    }



    //一般在直接New一个View的时候调用。
    public SloopView(Context context) {
        this(context, null);
    }

    //一般在layout文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在attrs中传递进来。
    public SloopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //调用录制
        recording();
        initPaint();
    }

    //有三个参数的构造函数中第三个参数是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style，且只有在明确调用的时候才会生效
    //注意：即使你在View中使用了Style这个属性也不会调用三个参数的构造函数，所调用的依旧是两个参数的构造函数。
    public SloopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量函数
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 用 MeasureSpec 的 getSize是获取数值， getMode是获取模式
     * 如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数。
     * UNSPECIFIED  默认值，父控件没有给子view任何限制，子View可以设置为任意大小。（wrap_content）
     * EXACTLY  表示父控件已经确切的指定了子View的大小。
     * AT_MOST  表示子View具体大小没有尺寸限制，但是存在上限，上限一般为父View大小。(match_content)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 确定View大小(onSizeChanged)==>这个函数在视图大小发生改变时调用。
     * @param w 宽
     * @param h 高
     * @param oldw  上一次宽度
     * @param oldh  上一次高度
     *
     * Q: 在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了，那么为什么还要再次确定View的大小呢？
        A: 这是因为View的大小不仅由View本身控制，而且受父控件的影响，所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数
     *
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    /**
     * 确定子View布局位置(onLayout)==>确定布局的函数是onLayout，它用于确定子View的位置，在自定义ViewGroup中会用到，他调用的是子View的layout函数。
     * @param changed
     * @param left      View左侧距父View左侧的距离==>	getLeft();
     * @param top       View顶部距父View顶部的距离==> getTop();
     * @param right     View右侧距父View左侧的距离==>  	getRight();
     * @param bottom    View底部距父View顶部的距离==>   getBottom();
     *
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    /**
     * 绘制内容(onDraw)==>onDraw是实际绘制的部分，也就是我们真正关心的部分，使用的是Canvas绘图。
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
        //画点
//        canvas.drawCircle(500, 500, 100, mPaint);
//        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
//                500,500,
//                500,600,
//                500,700
//        },mPaint);
        //画线
//        canvas.drawLine(300,300,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
//        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
//                100,200,200,200,
//                100,300,200,300
//        },mPaint);


        //画矩形
        // 第一种
//        canvas.drawRect(100,100,800,400,mPaint);
//        // 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect,mPaint);
//两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的
//        // 第三种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRect(rectF,mPaint);



        //绘制圆角矩形
//        // 第一种
//        RectF rectF = new RectF(100,100,800,400);（一块矩形范围）
//        canvas.drawRoundRect(rectF,30,30,mPaint);
//        // 第二种（API >= 21）
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);

//        // 矩形
//        RectF rectF = new RectF(100,100,800,400);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        //实际上在rx为宽度的一半，ry为高度的一半时，刚好是一个椭圆，通过上面我们分析的原理推算一下就能得到，
//        //而当rx大于宽度的一半，ry大于高度的一半时，实际上是无法计算出圆弧的，所以drawRoundRect对大于该数值的参数进行了限制(修正)，
//        //凡是大于一半的参数均按照一半来处理。
//        canvas.drawRoundRect(rectF,700,400,mPaint);


        //绘制椭圆
//        // 第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawOval(rectF,mPaint);

//        //绘制圆弧
//        RectF rectF = new RectF(100,100,800,800);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        //第三个参数表示是否使用中心点
//        //可以发现使用了中心点之后绘制出来类似于一个扇形，
//        //而不使用中心点则是圆弧起始点和结束点之间的连线加上圆弧围成的图形。
//        //这样中心点这个参数的作用就很明显了，不必多说想必大家试一下就明白了
//        canvas.drawArc(rectF,0,270,true,mPaint);


        //测试Paint的使用
//        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(40);     //为了实验效果明显，特地设置描边宽度非常大
//        // 描边
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(200,200,100,paint);
//        // 填充
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(200,500,100,paint);
//        // 描边加填充
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawCircle(200, 800, 100, paint);



        // 在坐标原点绘制一个黑色圆形
//        mPaint.setColor(Color.BLACK);
//        mPaint.setAntiAlias(true);
//        canvas.translate(200,200);      //位移，相对当前位置，而不是（0，0）点
//        canvas.drawCircle(0,0,100,mPaint);
//        // 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);


//        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
//        RectF rectF = new RectF(0, -400, 400, 0);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);     //绘制黑色矩形区域
//        canvas.scale(-0.5f, -0.5f, 200, 0);   //画布缩放
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);

//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
//        RectF recF = new RectF(-400, -400, 400, 400);
//        for(int i = 0; i <= 20; i++){
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawRect(recF, mPaint);
//        }


//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rectF = new RectF(0, -400, 400, 0);
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
//        canvas.drawRect(rectF, mPaint);
//        canvas.rotate(20);
//        canvas.rotate(180, 200, 0);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);


//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
//        canvas.drawCircle(0, 0, 400, mPaint);
//        canvas.drawCircle(0, 0, 380, mPaint);
//        for(int i = 0; i < 360; i += 10){
//            canvas.drawLine(0, 380, 0, 400, mPaint);
//            canvas.rotate(10);
//        }

//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
//        RectF rectF = new RectF(0, 0, 200, 200);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);
//        canvas.skew(0, 1);
//        canvas.skew(1, 0);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);

//        mPicture.draw(canvas);
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 500));
        //包装成为Drawable
//        PictureDrawable drawable = new PictureDrawable(mPicture);
//        //设置绘制区域
//        drawable.setBounds(0, 0, 250, mPicture.getHeight());
//        //绘制
//        drawable.draw(canvas);


//        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.img);
////        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
////        canvas.drawBitmap(bitmap, 200, 500, new Paint());
//        canvas.translate(mWidth / 2, mHeight / 2);
//        Rect rect = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        Rect dst = new Rect(0, 0, 400, 400);
//        canvas.drawBitmap(bitmap, rect, dst, null);

//        mPaint.setTextSize(50);
//        canvas.drawText(text, 200, 500, mPaint);
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        path.lineTo(200, 200);
        path.setLastPoint(200, 100);
        path.lineTo(0, 200);
        //close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做
        path.close();
        canvas.drawPath(path, mPaint);
    }
}

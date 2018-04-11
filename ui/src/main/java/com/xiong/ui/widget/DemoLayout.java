package com.xiong.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: xiong
 * @time: 2017/12/20
 * @说明: 圆点绕矩形
 */

public class DemoLayout extends View {

    private Paint mRedPaint;
    private Paint mBluePaint;
    private Rect mRect;
    private Path mPath;
    private int mPhase = 0;
    private int mPhase25 = 25;

    public DemoLayout(Context context) {
        this(context, null);
    }

    public DemoLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initRect();
        initPath();
    }

    private void initPaint() {
        // 抗锯齿
        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 空心
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setColor(Color.RED);
        // 抗锯齿
        mBluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 空心
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setColor(Color.BLUE);
    }

    private void initRect() {
        mRect = new Rect();
    }

    private void initPath() {
        mPath = new Path();
        mPath.addCircle(0, 0, 5, Path.Direction.CCW);
    }

    @SuppressLint({"NewApi", "DrawAllocation"})
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 虚线
        mRect.set(0, 0, getWidth(), getHeight());
//        PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 10);
//        mPaint.setPathEffect(pathEffect);
        mRedPaint.setPathEffect(new PathDashPathEffect(mPath, 50, mPhase,
                PathDashPathEffect.Style.ROTATE));
        canvas.drawRect(mRect, mRedPaint);
        mBluePaint.setPathEffect(new PathDashPathEffect(mPath, 50, mPhase25,
                PathDashPathEffect.Style.ROTATE));
        canvas.drawRect(mRect, mBluePaint);
        mPhase++;
        mPhase25++;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

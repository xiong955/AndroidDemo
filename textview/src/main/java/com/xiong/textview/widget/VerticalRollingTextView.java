package com.xiong.textview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author: xiong
 * @time: 2017/11/13
 * @说明: 竖向滚动的TextView
 */

public class VerticalRollingTextView extends View {

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 翻转动画
     */
    private InternalAnimation mAnimation = new InternalAnimation();

    /**
     * 文字距离
     */
    private final float mTextTopToAscentOffsetY;

    /**
     * 控件X,Y
     */
    private float mOffsetY;
    private float mOffsetX;

    /**
     * 动画执行时间
     */
    private int mDuration = 1000;

    /**
     * 要翻滚的Y距离
     */
    private float mCurrentOffsetY;


    /**
     * 矩形画布
     */
    Rect bounds = new Rect();

    /**
     * 是否执行动画
     */
    private boolean isRun = false;

    private String default_text = "?";

    private String text = "?";

    public void setText(String text) {
        this.text = text;
    }

    public VerticalRollingTextView(Context context) {
        this(context, null);
    }

    public VerticalRollingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalRollingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 颜色
        mPaint.setColor(Color.WHITE);
        // 大小
        mPaint.setTextSize(26);

        // 字体度量
        Paint.FontMetricsInt metricsInt = mPaint.getFontMetricsInt();
        mTextTopToAscentOffsetY = metricsInt.ascent - metricsInt.top;

        mAnimation.setDuration(mDuration);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint.getTextBounds(default_text, 0, default_text.length(), bounds);
        mOffsetY = (getHeight() + bounds.height()) * 0.5f;
        mOffsetX = getWidth() * 0.5f;
        mCurrentOffsetY = mOffsetY;
        mAnimation.updateValue(mOffsetY, -2 * mTextTopToAscentOffsetY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float a = mPaint.measureText("?");
        float b = mPaint.measureText(text);
        canvas.drawText(default_text, mOffsetX - a/2, mCurrentOffsetY, mPaint);
        canvas.drawText(text, mOffsetX - b/2, mCurrentOffsetY + mOffsetY + 2*mTextTopToAscentOffsetY, mPaint);
    }

    class InternalAnimation extends Animation {

        float startValue;
        float endValue;

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            mCurrentOffsetY = evaluate(interpolatedTime, startValue, endValue);
            postInvalidate();
        }

        public void updateValue(float startValue, float endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        float evaluate(float fraction, float startValue, float endValue) {
            return startValue + fraction * (endValue - startValue);
        }

    }

    /**
     * 开始转动,界面可见的时候调用
     */
    public void run() {
        if(!isRun){
            mAnimation.updateValue(mCurrentOffsetY, -2 * mTextTopToAscentOffsetY);
            startAnimation(mAnimation);
            isRun = true;
        }
    }

    /**
     * 重置控件
     */
    public void reset() {
        mCurrentOffsetY = mOffsetY;
        isRun = false;
        invalidate();
    }
}

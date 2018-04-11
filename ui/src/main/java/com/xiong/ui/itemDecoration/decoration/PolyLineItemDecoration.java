package com.xiong.ui.itemDecoration.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.xiong.ui.itemDecoration.adapter.GRcyViewAdapter;

/**
 * @author: xiong
 * @time: 2018/04/11
 * @说明:
 */
public class PolyLineItemDecoration extends RecyclerView.ItemDecoration {

    private float mDividerHeight;

    // 画笔
    private Paint mPaint;

    public PolyLineItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 起点Rv
        RecyclerView startBall = null;
        // 重点Rv
        RecyclerView endBall = null;
        // 起点坐标
        float startX = 0;
        float startY = 0;
        // 终点左边
        float endX = 0;
        float endY = 0;

        // 获取子布局
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            // 第一个ItemView
            if (index == 0) {
                // 子布局判断
                if (view instanceof LinearLayout) {
                    for (int j = 0; j < ((LinearLayout) view).getChildCount(); j++) {
                        // 取到内层Rv
                        if (((LinearLayout) view).getChildAt(j) instanceof RecyclerView) {
                            // 获取选中位置的坐标
                            startBall = (RecyclerView) ((LinearLayout) view).getChildAt(j);
                            GRcyViewAdapter startAdapter = (GRcyViewAdapter) startBall.getAdapter();
                            View startDot = startBall.getChildAt(startAdapter.getCheck());
                            startX = startDot.getWidth() / 2 + startDot.getX();
                            startY = startDot.getHeight() / 2 + view.getY();
                            break;
                        }
                    }
                }
                continue;
            }
            // 子布局判断,取到内层Rv,在获取选中位置的坐标
            if (view instanceof LinearLayout) {
                for (int j = 0; j < ((LinearLayout) view).getChildCount(); j++) {
                    // 取到内层Rv
                    if (((LinearLayout) view).getChildAt(j) instanceof RecyclerView) {
                        // 获取选中位置的坐标
                        endBall = (RecyclerView) ((LinearLayout) view).getChildAt(j);
                        GRcyViewAdapter endAdapter = (GRcyViewAdapter) endBall.getAdapter();
                        View endDot = endBall.getChildAt(endAdapter.getCheck());
                        endX = endDot.getWidth() / 2 + endDot.getX();
                        endY = endDot.getHeight() / 2 + view.getY();
                        break;
                    }
                }
            }
            // 如果都为空,跳出本次,防止绘制错误
            if (startBall == null && endBall == null) {
                continue;
            }
            // 画线
            c.drawLine(startX, startY, endX, endY, mPaint);
            // 将终点设置为下一次的起点
            startX = endX;
            startY = endY;
            startBall = endBall;


            // 无关的东西
            float dividerTop = view.getTop() - mDividerHeight;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

//        //第一个ItemView不需要在上面绘制分割线
//        if (parent.getChildAdapterPosition(view) != 0) {
//            //这里直接硬编码为1px
//            outRect.top = 1;
//            mDividerHeight = 1;
//        }
    }
}

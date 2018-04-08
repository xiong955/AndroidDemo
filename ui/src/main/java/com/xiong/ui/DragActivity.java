package com.xiong.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author: xiong
 * @time: 2018/03/15
 * @说明: 拖动控件
 */

public class DragActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button mButton;
    private ViewGroup mViewGroup;
    private int xDelta;
    private int yDelta;
    public static final String TAG = "Drag";

    private int mButtonWidth;
    private int mButtonHeight;
    private int mLayoutWidth;
    private int mLayoutHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        mViewGroup = findViewById(R.id.root);
        mButton = findViewById(R.id.id_text);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mButton.setLayoutParams(layoutParams);
        mButton.setOnTouchListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mButtonWidth = mButton.getWidth();
        mButtonHeight = mButton.getHeight();
        mLayoutWidth = mViewGroup.getWidth();
        mLayoutHeight = mViewGroup.getHeight();
        Log.d(TAG, "onWindowFocusChanged: width = " + mButtonWidth + "   height = " + mButtonHeight);
        Log.d(TAG, "onWindowFocusChanged: width = " + mButtonWidth + "   height = " + mLayoutHeight);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        Log.d(TAG, "onTouch: x= " + x + "y=" + y);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                xDelta = x - params.leftMargin;
                yDelta = y - params.topMargin;
                Log.d(TAG, "ACTION_DOWN: xDelta= " + xDelta + "yDelta=" + yDelta);
                break;

            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                int xDistance = x - xDelta;
                int yDistance = y - yDelta;
                Log.d(TAG, "ACTION_MOVE: xDistance= " + xDistance + "yDistance=" + yDistance);

                // 靠右边
                if (mLayoutWidth - xDistance < mButtonWidth) {
                    xDistance = mLayoutWidth - mButtonWidth;
                }
                if (mLayoutHeight - yDistance < mButtonHeight) {
                    yDistance = mLayoutHeight - mButtonHeight;
                }

                // 靠左边
                if (xDistance < 0) {
                    xDistance = 0;
                }
                if (yDistance < 0) {
                    yDistance = 0;
                }
                layoutParams.leftMargin = xDistance;
                layoutParams.topMargin = yDistance;
                view.setLayoutParams(layoutParams);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        mViewGroup.invalidate();
        return true;
    }
}
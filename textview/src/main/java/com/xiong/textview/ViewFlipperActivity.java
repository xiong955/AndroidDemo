package com.xiong.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiong
 * @time: 2018/02/26
 * @说明:
 */

public class ViewFlipperActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper;

    private List<String> contentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);

        initViews();
        initData();
    }

    private void initViews() {
        mViewFlipper = findViewById(R.id.flipper);
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            contentList.add("我是测试数据" + i);
        }

        for (int i = 0; i < contentList.size(); i++) {
            View view = View.inflate(this, R.layout.textview, null);
            TextView textView = view.findViewById(R.id.tv_text);
            textView.setText(contentList.get(i));
            mViewFlipper.addView(view);
        }
        mViewFlipper.startFlipping();
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_left_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_left_out));
    }
}

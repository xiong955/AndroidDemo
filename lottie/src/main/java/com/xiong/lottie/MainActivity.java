package com.xiong.lottie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LottieAnimationView mAnimationV;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mAnimationV = findViewById(R.id.animation_view);
        mAnimationV.setImageAssetsFolder("images/");
        mAnimationV.setAnimation("mid_prize.json");

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mAnimationV.playAnimation();
                break;
        }
    }
}


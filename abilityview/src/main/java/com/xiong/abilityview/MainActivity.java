package com.xiong.abilityview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiong.abilityview.bean.AbilityBean;
import com.xiong.abilityview.widget.AbilityView;

/**
 * @author xiong
 * @date 2017/11/10
 */
public class MainActivity extends AppCompatActivity {

    private AbilityView mAbilityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbilityBean abilityBean = new AbilityBean(70,100,70,80,60,50,90);
        mAbilityView = findViewById(R.id.view);
        mAbilityView.setData(abilityBean);
    }
}

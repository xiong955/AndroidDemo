package com.xiong.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiong.textview.widget.VerticalRollingTextView;

public class MainActivity extends AppCompatActivity {

    private VerticalRollingTextView tv;
    private Button btn1;
    private Button btn2;

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Hello World!");
                tv.run();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.reset();
            }
        });




        tv1 = findViewById(R.id.tv1);
        ImageSpan span = new ImageSpan(this, R.mipmap.ic_launcher);//加载图片的资源
        SpannableString spanStr = new SpannableString("icon");//不想要这个字符串可以试试空字符串
        spanStr.setSpan(span, 0, spanStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(spanStr);
        tv1.append("asdasdasdasdasdsadasdasdasdasdasdasdasdasdasdasdasdsadasdasd");
    }
}

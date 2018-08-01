package com.xiong.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/**
 * @author: xiong
 * @time: 2018/03/15
 * @说明: 8.0推送测试
 */
public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        findViewById(R.id.xiong).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                Notification.Builder builder = new Notification.Builder(DemoActivity.this);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelID = "1";
                    String channelName = "推送";
                    NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                    assert manager != null;
                    manager.createNotificationChannel(channel);
                    builder.setChannelId(channelID);
                }

                builder.setContentText("111");//主内容.
                builder.setContentTitle("111");//标题
                builder.setSmallIcon(R.mipmap.ic_launcher);//通知栏图标
                builder.setTicker("111");//通知栏标题
                builder.setWhen(System.currentTimeMillis());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    builder.setShowWhen(true);
                }
                builder.setAutoCancel(true);
                builder.build();

                assert manager != null;
                manager.notify(1,builder.build());
            }
        });
    }
}

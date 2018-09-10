package com.xiong.databinding.vm;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xiong.databinding.R;
import com.xiong.databinding.adapter.MyRecyclerViewAdapter;
import com.xiong.databinding.databinding.ActivityMainBinding;
import com.xiong.databinding.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiong
 * @time: 2018/08/14
 * @说明:
 */

public class MainActivity extends Activity {

    private List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initModel();
    }

    private void initModel() {
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initData();

        //设置RecyclerView
        mainBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this,users);
        mainBinding.recyclerView.setAdapter(adapter);
    }

    private void initData() {
        users = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            User user = new User();
            user.name.set("111"+i);
            user.age.set("111"+i);
            user.url.set("111"+i);
            users.add(user);
        }
    }
}
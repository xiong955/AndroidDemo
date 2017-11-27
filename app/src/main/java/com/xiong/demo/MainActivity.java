package com.xiong.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainAdapter.OnItemClickListener {

    private RecyclerView rv;
    private MainAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initRecycle();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mData.add(i + "");
        }
    }

    private void initView() {
        Button btn1 = findViewById(R.id.add);
        Button btn2 = findViewById(R.id.remove);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        rv = findViewById(R.id.rv);
    }

    private void initRecycle() {
        mAdapter = new MainAdapter(mData, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.remove:
                mAdapter.removeData(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, position + "+Line", Toast.LENGTH_LONG).show();
    }
}

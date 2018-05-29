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

import com.xiong.demo.widget.ScrollImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainAdapter.OnItemClickListener {

    private RecyclerView rv;
    private MainAdapter mAdapter;
    private List<MainBean> mData = new ArrayList<>();

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initRecycle();
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            MainBean bean = new MainBean();
            bean.setTitle(i+"");
            bean.setDrawable(R.drawable.a3);
            mData.add(bean);
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
        mAdapter.setOnItemClickListener(this);
        rv.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    ScrollImageView scrollImageView = view.findViewById(R.id.iv_content);
                    if (scrollImageView.getVisibility() == View.VISIBLE) {
                        scrollImageView.setDx(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                mAdapter.addData(1);
//                mAdapter.notifyDataSetChanged();
                break;
            case R.id.remove:
                mAdapter.removeData(1);
                mAdapter.notifyDataSetChanged();
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

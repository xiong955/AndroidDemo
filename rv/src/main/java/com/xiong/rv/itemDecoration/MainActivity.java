package com.xiong.rv.itemDecoration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.xiong.rv.R;
import com.xiong.rv.itemDecoration.adapter.MRcyViewAdapter;
import com.xiong.rv.itemDecoration.decoration.PolyLineItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiong
 * @time: 2018/03/15
 * @说明: itemDecoration
 */
public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private RecyclerView mRecyclerView;
    private MRcyViewAdapter mAdapter;
    private PolyLineItemDecoration polyLineItemDecoration;
    private PolyLineItemDecoration polyLineItemDecoration1;

    private List<String> mData;
    private boolean isOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initData();
        initAdapter();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    mRecyclerView.removeItemDecoration(polyLineItemDecoration);
                    mRecyclerView.removeItemDecoration(polyLineItemDecoration1);
                    isOpen = false;
                } else {
                    mRecyclerView.addItemDecoration(polyLineItemDecoration);
                    mRecyclerView.addItemDecoration(polyLineItemDecoration1);
                    isOpen = true;
                }
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv);
        mButton = findViewById(R.id.button);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add(i + "");
        }
    }

    private void initAdapter() {
        mAdapter = new MRcyViewAdapter(mRecyclerView, mData);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        polyLineItemDecoration = new PolyLineItemDecoration(Color.RED);
        polyLineItemDecoration1 = new PolyLineItemDecoration(Color.BLUE);
        mRecyclerView.addItemDecoration(polyLineItemDecoration);
        mRecyclerView.addItemDecoration(polyLineItemDecoration1);
    }
}

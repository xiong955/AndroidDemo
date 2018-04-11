package com.xiong.ui.itemDecoration.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiong.ui.R;

import java.util.List;

/**
 * @author: xiong
 * @time: 2018/04/11
 * @说明: 适配器
 */
public class MRcyViewAdapter extends Adapter<MRcyViewAdapter.RecyclerHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<String> dataList;
    private GRcyViewAdapter mAdapter;

    public MRcyViewAdapter(RecyclerView recyclerView, List<String> dataList) {
        this.mContext = recyclerView.getContext();
        this.mRecyclerView = recyclerView;
        this.dataList = dataList;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        mAdapter = new GRcyViewAdapter(mRecyclerView, dataList);
        holder.recyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 10);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.item_rv);
        }
    }
}
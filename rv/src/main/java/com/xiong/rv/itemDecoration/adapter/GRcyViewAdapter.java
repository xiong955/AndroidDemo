package com.xiong.rv.itemDecoration.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiong.rv.R;

import java.util.List;
import java.util.Random;

/**
 * @author: xiong
 * @time: 2018/04/11
 * @说明: 适配器
 */
public class GRcyViewAdapter extends Adapter<GRcyViewAdapter.RecyclerHolder> {

    private Context mContext;
    private List<String> dataList;

    public GRcyViewAdapter(RecyclerView recyclerView, List<String> dataList) {
        this.mContext = recyclerView.getContext();
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tv, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 获取选中
     *
     * @return
     */
    public int getCheck() {
        return new Random().nextInt(9);
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
        }
    }
}
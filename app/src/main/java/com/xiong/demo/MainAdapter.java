package com.xiong.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiong.demo.widget.ScrollCircleImageView;

import java.util.List;

/**
 * @author: xiong
 * @time: 2017/11/21
 * @说明:
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<MainBean> mData;
    private MainActivity mActivity;

    public MainAdapter(List<MainBean> mData, MainActivity mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mActivity).inflate(R.layout.item_main, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mData.get(position).getTitle());
        holder.iv.setImageResource(mData.get(position).getDrawable());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
        if (position > 0 && position % 10 == 0) {
            holder.tv.setVisibility(View.GONE);
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.tv.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(int position) {
        MainBean bean = new MainBean();
        bean.setTitle("Insert One");
        bean.setDrawable(R.drawable.a2);
        mData.add(position, bean);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ScrollCircleImageView iv;

        MyViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv_content);
            iv = view.findViewById(R.id.iv_content);
        }
    }

}

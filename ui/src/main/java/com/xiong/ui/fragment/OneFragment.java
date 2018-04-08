package com.xiong.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xiong.ui.R;

@SuppressLint("ValidFragment")
public class OneFragment extends Fragment {

    private String tx;

    public OneFragment(String tx) {
        // Required empty public constructor
        this.tx = tx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initView(view);
        return view;
    }

    private void initView(final View view) {
        ((TextView) view.findViewById(R.id.one)).setText(tx);
        view.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "点击了" + tx, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

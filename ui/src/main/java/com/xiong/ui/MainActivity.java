package com.xiong.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiong.ui.fragment.OneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initViewPager();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);

        mList.add("头条");
        mList.add("热点");
        mList.add("头条");
        mList.add("热点");
        mList.add("头条");
        mList.add("热点");
    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        for (String str : mList) {
            fragments.add(new OneFragment(str));
        }
        // 创建ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        // TabLayout指示器添加文本
        for (int i = 0; i < mList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
            mTabLayout.getTabAt(i).setText(mList.get(i));
        }
        //设置可以滑动
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(ArrayList<Fragment> fragments) {
            mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = mFragmentList.get(position);

            return fragment;
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
    }
}

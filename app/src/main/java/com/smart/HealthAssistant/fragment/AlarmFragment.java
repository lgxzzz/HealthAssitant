package com.smart.HealthAssistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.HealthAssistant.R;
import com.smart.HealthAssistant.adpater.AlarmPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class AlarmFragment extends Fragment{

    private ViewPager mVp;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private TabLayout mTb;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_alarm, container, false);
        initView(view);

        return view;
    }

    public static AlarmFragment getInstance() {
        return new AlarmFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initTitile();
        initFragment();
        initData();
        //设置适配器
        mVp.setAdapter(new AlarmPagerAdapter(getChildFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        mTb.setupWithViewPager(mVp);
    }

    private void initTitile() {
        mTitleList = new ArrayList<>();
        mTitleList.add("报警");
        mTitleList.add("短信");
        mTitleList.add("WIFI");
        //设置tablayout模式
        mTb.setTabMode(TabLayout.MODE_FIXED);
        //tablayout获取集合中的名称
        mTb.addTab(mTb.newTab().setText(mTitleList.get(0)));
        mTb.addTab(mTb.newTab().setText(mTitleList.get(1)));
        mTb.addTab(mTb.newTab().setText(mTitleList.get(2)));
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(SettingPhoneFragment.getInstance());
        mFragmentList.add(SettingMsgFragment.getInstance());
        mFragmentList.add(SettingWifiFragment.getInstance());
    }


    public void initView(View view){
        mTb = (TabLayout) view.findViewById(R.id.tab_layout);
        mVp = (ViewPager) view.findViewById(R.id.mVp);

    };

    public void initData() {

    }




}

package com.smart.HealthAssistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.smart.HealthAssistant.R;
import com.smart.HealthAssistant.adpater.MsgInfoAdapter;
import com.smart.HealthAssistant.bean.MsgInfo;
import com.smart.HealthAssistant.data.DBManger;
import com.smart.HealthAssistant.data.DefaultDataFacotry;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{

    List<MsgInfo> msgInfoList = new ArrayList<>();

    ListView mMsgListview;

    MsgInfoAdapter mAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        return view;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mMsgListview = view.findViewById(R.id.home_listview);
    };

    public void initData() {
        msgInfoList = DBManger.getInstance(getContext()).mDefaultDataFacotry.mMsgInfoList;
        mAdapter = new MsgInfoAdapter(getContext(),msgInfoList);
        mMsgListview.setAdapter(mAdapter);

    }




}

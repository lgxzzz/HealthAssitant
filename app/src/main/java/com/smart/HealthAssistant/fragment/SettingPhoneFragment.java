package com.smart.HealthAssistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smart.HealthAssistant.R;
import com.smart.HealthAssistant.view.SharedPreferenceUtil;


public class SettingPhoneFragment extends Fragment{

    EditText mFirstEd;
    EditText mSecEd;
    EditText mTempEd;
    Button mFirstBtn;
    Button mTempBtn;
    Button mSecBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_setting_phone, container, false);
        initView(view);

        return view;
    }

    public static SettingPhoneFragment getInstance() {
        return new SettingPhoneFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mFirstEd = view.findViewById(R.id.first_tel_ed);
        mSecEd = view.findViewById(R.id.sec_tel_ed);
        mFirstBtn = view.findViewById(R.id.first_tel_btn);
        mSecBtn = view.findViewById(R.id.sec_tel_btn);
        mTempBtn = view.findViewById(R.id.alarm_temp_btn);
        mTempEd = view.findViewById(R.id.alarm_temp_ed);
    };

    public void initData() {
        String first_tel = SharedPreferenceUtil.getFirstTel(getContext());
        String sec_tel = SharedPreferenceUtil.getSecondTel(getContext());
        String temp = SharedPreferenceUtil.getAlarmTemp(getContext());
        if (first_tel!=null){
            mFirstEd.setText(first_tel);
        }
        if (sec_tel!=null){
            mSecEd.setText(sec_tel);
        }
        if (temp!=null){
            mTempEd.setText(temp);
        }
        mFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mFirstEd.getEditableText().toString();
                if (tel.length()>0){
                    SharedPreferenceUtil.setFirstTel(tel,getContext());
                    Toast.makeText(getContext(),"保存号码成功！",Toast.LENGTH_LONG).show();
                }

            }
        });
        mSecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mSecEd.getEditableText().toString();
                if (tel.length()>0){
                    SharedPreferenceUtil.setSecondTel(tel,getContext());
                    Toast.makeText(getContext(),"保存号码成功！",Toast.LENGTH_LONG).show();
                }

            }
        });
        mTempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mTempEd.getEditableText().toString();
                if (tel.length()>0){
                    SharedPreferenceUtil.setAlarmTemp(tel,getContext());
                    Toast.makeText(getContext(),"保存报警温度成功！",Toast.LENGTH_LONG).show();
                }

            }
        });
    }




}

package com.smart.HealthAssistant.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {
    private static final String PREFERENCE_NAME_BASIC_CONFIG = "basic_config";
    private static final String PREFERENCE_ALARM_TEMP = "alarm_temp";
    private static final String PREFERENCE_FIRST_TEL = "first_tel";
    private static final String PREFERENCE_SECOND_TEL = "second_tel";

    //保存报警温度
    public static void setAlarmTemp(String temp,Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_ALARM_TEMP, temp);
        editor.commit();
    }

    //获取第一个手机号
    public static String getAlarmTemp(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_ALARM_TEMP, null);
    }

    //保存第一个手机号
    public static void setFirstTel(String tel,Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_FIRST_TEL, tel);
        editor.commit();
    }

    //获取第一个手机号
    public static String getFirstTel(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_FIRST_TEL, null);
    }

    //保存第二个手机号
    public static void setSecondTel(String tel,Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_SECOND_TEL, tel);
        editor.commit();
    }

    //获取第二个手机号
    public static String getSecondTel(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME_BASIC_CONFIG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_SECOND_TEL, null);
    }

}

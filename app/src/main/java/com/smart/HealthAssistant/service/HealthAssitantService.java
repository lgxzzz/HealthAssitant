package com.smart.HealthAssistant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.smart.HealthAssistant.view.AlarmMgr;


public class HealthAssitantService extends Service {

    AlarmMgr mAlarmMgr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAlarmMgr = new AlarmMgr(this);
        mAlarmMgr.startMonitor();
    }
}

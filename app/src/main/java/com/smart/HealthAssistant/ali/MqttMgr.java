package com.smart.HealthAssistant.ali;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.log.IDGenerater;
import com.smart.HealthAssistant.bean.Temp;
import com.smart.HealthAssistant.constant.Constant;
import com.smart.HealthAssistant.data.DBManger;

public class MqttMgr  {
    private static final String TAG = "MqttMgr";

    Context mContext;

    public MqttMgr(Context mContext){
        this.mContext = mContext;
    }

    public void parseMsg(String msg){
        try{
            //保存🈯到本地数据库，通知界面刷新
            if (msg.contains("温度")){
                String[] params = msg.split(":");
                Temp temp = new Temp();
                temp.setValue(params[1]);
                int value = Integer.parseInt(params[1]);
                long time = System.currentTimeMillis();
                temp.setTime(time+"");
                DBManger.getInstance(mContext).insertTemp(temp);

                notifyTempState(value,time);


            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    //通知后台服务里面的监听器和前台页面刷新
    public void notifyTempState(int temp,long time){
        if (mContext!=null){
            Intent intent = new Intent();
            intent.setAction(Constant.HEALTH_ASSISANT_NOTIFY_STATE);
            intent.putExtra("temp",temp);
            intent.putExtra("timestamp",time);
            mContext.sendBroadcast(intent);

            Intent intent1 = new Intent();
            intent1.setAction(Constant.HEALTH_ASSISANT_ALI_MSG);
            intent1.putExtra("temp",temp);
            intent1.putExtra("timestamp",time);
            mContext.sendBroadcast(intent1);
        }

    }
}

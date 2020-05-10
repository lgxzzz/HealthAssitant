package com.smart.HealthAssistant.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import com.smart.HealthAssistant.constant.Constant;


/***
 * 报警触发器
 *
 * */
public class AlarmMgr {
    Context mContext;

    //是否通知
    private boolean isNotify =false;

    //温度计数
    private int mTempCount = 0;

    //上次收到温度时间
    private long mLastReciveTime = 0;
    //上次拨号时间
    private long mLastMakePhoneTime = 0;

    //取值的时间间隔
    private int mTempTimeOut = 1000*60;

    Handler mHandler = new Handler();

    public AlarmMgr(Context mContext){
        this.mContext = mContext;
        registerStateNotifiyBroadcast();
    }

    //开始监听
    public void startMonitor(){
        isNotify = true;
    }

    //结束监听
    public void stopMonitor(){
        isNotify = false;
    }

    //接收状态通知广播
    public void registerStateNotifiyBroadcast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.HEALTH_ASSISANT_NOTIFY_STATE);
        mContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Constant.HEALTH_ASSISANT_NOTIFY_STATE)){
                    int temp = intent.getIntExtra("temp",-1);
                    long timestamp = intent.getLongExtra("timestamp",-1);
                    if (temp != -1){
                        checkTempIsExceedNormal(temp,timestamp);
                    }
                }
            }
        },filter);
    }

    /***
     * 检测体温是否超过正常水平
     * 触发条件：
     * 1.温度大于37.5时，且前后两次的时间间隔不超过1分钟（展示效果，实际使用可调整），触发计数一次
     * 2.计数次数大于5次
     *
     * 计数避免采集误差
     * */
    public void checkTempIsExceedNormal(int temp,long timestamp){
        String tempStr = SharedPreferenceUtil.getAlarmTemp(mContext);
        int tempVallue = 38;
        if (tempStr!=null){
            tempVallue = Integer.parseInt(tempStr);
        }

        if (temp>tempVallue&&isAdjacentTime(timestamp)){
            mLastReciveTime = timestamp;
            if (mTempCount==1){
                //通知拨号
                notifyAlarmPhone(timestamp);
                //计数重置
                mTempCount = 0;
            }else {
                mTempCount++;
            }
        }
    }

    /***
     * 预警拨号
     * 触发条件：
     * 1.已经设置了预警号码
     * 2避免连续拨号，每次拨号间隔1分钟
     * */
    public void notifyAlarmPhone(long timestamp){
        final String tel = SharedPreferenceUtil.getFirstTel(mContext);
        if (tel!=null&&isMakeCallBefore(timestamp)){
            mLastMakePhoneTime = timestamp;
            Toast.makeText(mContext,"温度过高，准备拨号！",Toast.LENGTH_LONG).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+tel));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);

                }
            },2000);
        }


    }

    //是否相邻时间
    public boolean isAdjacentTime(long time){
        if (mLastReciveTime == 0){
            return true;
        }
        if (time - mLastReciveTime<mTempTimeOut){
            return true;
        }
        return false;
    }

    //是否之前已经拨号
    public boolean isMakeCallBefore(long time){
        if (mLastMakePhoneTime == 0){
            return true;
        }
        if (time - mLastMakePhoneTime<mTempTimeOut){
            return true;
        }
        return false;
    }
}

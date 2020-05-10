package com.smart.HealthAssistant.util;

import android.widget.TextView;

import com.smart.HealthAssistant.bean.Temp;
import com.smart.HealthAssistant.view.ECGView;
import com.smart.HealthAssistant.view.TempView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ECGUtil {
    public static List<Temp> mTemps = new ArrayList<>();
    private Timer timer;
    private TimerTask timerTask;
    /**
     * 模拟ECG数据源
     */
    public void showECGData(final ECGView view){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int value = (int)(new Random().nextFloat()*(50)+180);
                view.showLine(value);//取得是-20到20间的浮点数
                if (listner!=null){
                    listner.onEcgChange(value);
                }
            }
        };
        //500表示调用schedule方法后等待500ms后调用run方法，50表示以后调用run方法的时间间隔
        timer.schedule(timerTask,500,100);
    }

    /**
     * 模拟体温数据源
     */
    public void showTempData(final TempView view){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int value = new Random().nextInt(6)+35;
                view.showLine(value);//取得是35-41间的浮点数
                if (listner!=null){
                    Temp temp =new Temp();
                    temp.setValue(value+"");
                    temp.setTime(getDateTime());
                    mTemps.add(temp);
                    listner.onTempChange(value);
                }
            }
        };
        //500表示调用schedule方法后等待500ms后调用run方法，50表示以后调用run方法的时间间隔
        timer.schedule(timerTask,500,1000);
    }

    /**
     * 停止绘制波形
     */
    public void stop(){
        if(timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if(null != timerTask) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    IDataChangeListner listner;

    public IDataChangeListner getListner() {
        return listner;
    }

    public void setListner(IDataChangeListner listner) {
        this.listner = listner;
    }

    public interface IDataChangeListner{
        public void onTempChange(int value);
        public void onEcgChange(float value);
    }

    public String getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}



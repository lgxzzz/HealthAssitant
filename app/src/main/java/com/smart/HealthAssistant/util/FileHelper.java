package com.smart.HealthAssistant.util;

import android.content.Context;

import com.smart.HealthAssistant.bean.Temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FileHelper {
    private Context mContext;

    public FileHelper(Context mContext) {
        this.mContext = mContext;
    }

    public static void saveFile(){
        String name = "/sdcard/"+"temp.txt";
        File file = new File(name);
        if (file.exists()){
            file.delete();
        }
        List<Temp> tempList = ECGUtil.mTemps;
        for (int i=0;i<tempList.size();i++){
            save(tempList.get(i),name);
        }
    }

    /**
     * 定义文件保存的方法，写入到文件中，所以是输出流
     */
    public static void save(Temp temp,String name) {
        String content = "时间：" + temp.getTime() + "，温度：" + temp.getValue();
        try {

            FileOutputStream outStream = new FileOutputStream(name,true);
            OutputStreamWriter writer = new OutputStreamWriter(outStream,"UTF-8");
            writer.write(content);
            writer.write("\r\n");
            writer.flush();
            writer.close();//记得关闭

            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
    public static String getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

}



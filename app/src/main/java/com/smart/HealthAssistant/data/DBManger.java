package com.smart.HealthAssistant.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smart.HealthAssistant.bean.Temp;
import com.smart.HealthAssistant.bean.User;
import com.smart.HealthAssistant.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBManger {
    private Context mContext;
    private SQLiteDbHelper mDBHelper;
    public User mUser;
    public DefaultDataFacotry mDefaultDataFacotry;
    public static  DBManger instance;

    public static DBManger getInstance(Context mContext){
        if (instance == null){
            instance = new DBManger(mContext);
        }
        return instance;
    };

    public DBManger(Context mContext){
        this.mContext = mContext;
        mDBHelper = new SQLiteDbHelper(mContext);
        mDefaultDataFacotry = new DefaultDataFacotry();
        //第一次进入 初始化100个温度数据
        if (SharedPreferenceUtil.getFirstTimeUse(mContext)){
            long time = System.currentTimeMillis();
            for (int i=0;i<100;i++){
                int value = new Random().nextInt(6)+35;
                Temp temp = new Temp();
                temp.setTime(time+"");
                temp.setValue(value+"");
                time = time+6000;
                insertTemp(temp);
            }
            SharedPreferenceUtil.setFirstTimeUse(false,mContext);
        }
    }


    //用户登陆
    public void login(String name,String password,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where UserName =? and Password=?",new String[]{name,password});
            if (cursor.moveToFirst()){
                String UserId = cursor.getString(cursor.getColumnIndex("UserId"));
                String UserName = cursor.getString(cursor.getColumnIndex("UserName"));

                mUser = new User();
                mUser.setUserId(UserId);
                mUser.setUserName(UserName);
                listener.onSuccess();
            }else{
                listener.onError("未查询到该用户");
            }
            db.close();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listener.onError("未查询到该用户");
    }

    //修改用户信息
    public void updateUser(User user,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",user.getUserName());
            values.put("Password",user.getPassword());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int code = db.update(SQLiteDbHelper.TAB_USER,values,"UserId =?",new String[]{user.getUserId()+""});
            listener.onSuccess();
        }catch (Exception e){

        }
    }

    //注册用户
    public void registerUser(User user,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",user.getUserName());
            values.put("Password",user.getPassword());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
            listener.onSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    //保存温度
    public void insertTemp(Temp temp){
        try{
            ContentValues values = new ContentValues();
            values.put("value",temp.getValue()+"");
            values.put("date",temp.getTime()+"");
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_TEMP,null,values);
            Log.e("lgx","");
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    //获取所有温度
    public List<Temp> getAllTemps(){
        List<Temp> temps = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_TEMP,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String value = cursor.getString(cursor.getColumnIndex("value"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                Temp temp =  new Temp();
                temp.setTime(date);
                temp.setValue(value);

                temps.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temps;
    };


    public interface IListener{
        public void onSuccess();
        public void onError(String error);
    };


}

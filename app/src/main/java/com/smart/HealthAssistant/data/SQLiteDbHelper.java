package com.smart.HealthAssistant.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smart.HealthAssistant.util.SharedPreferenceUtil;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "HealthAssistant.db";
    //数据库版本号
    public static int DB_VERSION = 9;
    //用户表
    public static final String TAB_USER = "UserInfo";
    //温度表
    public static final String TAB_TEMP = "TempInfo";

    Context context;
    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
        createTableTemp(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        SharedPreferenceUtil.setFirstTimeUse(true,context);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_TEMP);
        onCreate(db);
    }

    //创建用户表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(UserId varchar(60) primary key, " +
                "UserName varchar(60), " +
                "Password varchar(60))");
    }

    //创建温度表
    public void createTableTemp(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_TEMP +
                "(Tempid integer primary key autoincrement, " +
                "value varchar(60), " +           //温度值
                "date varchar(60))");             //时间
    }


}

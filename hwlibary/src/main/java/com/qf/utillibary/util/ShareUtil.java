package com.qf.utillibary.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 共享参数的工具类
 * Created by Ken on 2016/8/1.
 */
public class ShareUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void putString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public static void putInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key){
        return sharedPreferences.getInt(key, -1);
    }
}

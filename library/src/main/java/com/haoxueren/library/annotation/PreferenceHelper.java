package com.haoxueren.library.annotation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.haoxueren.library.ContextHelper;

/**
 * Helper for SharedPreferences
 * Created by Haoxueren on 2017/12/24.
 */
public class PreferenceHelper {

    /** 获取默认的SharedPreferences对象 */
    public static SharedPreferences getDefaultPreference() {
        Context context = ContextHelper.getContext();
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    /** 保存String类型的值到SharedPreferences */
    public static void putString(String key, String value) {
        PreferenceHelper.getDefaultPreference()
                .edit().putString(key, value).apply();
    }

    /** 保存boolean类型的值到SharedPreferences */
    public static void putBoolean(String key, boolean value) {
        PreferenceHelper.getDefaultPreference()
                .edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceHelper.getDefaultPreference().getBoolean(key, defaultValue);
    }

    /** defaultValue = false */
    public static boolean getBoolean(String key) {
        return PreferenceHelper.getDefaultPreference().getBoolean(key, false);
    }


    /** 保存int类型的值到SharedPreferences */
    public static void putInt(String key, int value) {
        PreferenceHelper.getDefaultPreference()
                .edit().putInt(key, value).apply();
    }
}

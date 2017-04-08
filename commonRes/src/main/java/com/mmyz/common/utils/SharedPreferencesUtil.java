package com.mmyz.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * ==============================================
 * <p>
 * 类名：SharedPreferencesUtil
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/4/8
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class SharedPreferencesUtil {
    public static final String SP_NAME = "SharedPreferences";
    private static SharedPreferences sp;

    /**
     * 缓存字符串数据
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(key, value).apply();
    }

    /**
     * 根据键获取字符串型的值
     *
     * @param context 上下文
     * @param key     键
     * @return value 值
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        return sp.getString(key, defValue);
    }
}

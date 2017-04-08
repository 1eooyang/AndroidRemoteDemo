package com.mmyz.router.operator;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * ==============================================
 * <p>
 * 类名：BaseIntentOperator
 * &nbsp 返回Intent操作类
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/27
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public abstract class BaseIntentOperator<T> implements IOperator<T,Intent> {
    private HashMap<String,Class<T>> mIntentContainer;
    public BaseIntentOperator(){
        mIntentContainer = new LinkedHashMap<>();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String uri, Class<T> clazz) {
        if (mIntentContainer != null){
            mIntentContainer.put(uri,clazz);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Intent invoke(Context context, String uri) {
        Class<T> clazz = null;
        if (check(uri)){
            clazz = mIntentContainer.get(uri);
        }
        if (clazz == null){
            throwException(uri);
        }
        return new Intent(context,clazz);
    }

    public abstract void throwException(String uri);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String uri) {
        return mIntentContainer != null && mIntentContainer.keySet().contains(uri);
    }
}

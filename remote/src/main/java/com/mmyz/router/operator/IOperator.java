package com.mmyz.router.operator;

import android.content.Context;

/**
 * ==============================================
 * <p>
 * 类名：
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/27
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public interface IOperator<T,K> {
    /**
     * 添加路由路线
     * @param uri 路由地址
     * @param clazz 路由类型
     */
    void put(String uri,Class<T> clazz);

    /**
     * 执行路由路线
     * @param context Context
     * @param uri 路由地址
     * @return {@link BaseIntentOperator#invoke(Context, String)}
     */
    K invoke(Context context,String uri);

    /**
     * 检查当前路由路线 是否存在
     * @param uri 路由地址
     * @return
     */
    boolean check(String uri);
}

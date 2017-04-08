package com.mmyz.router.callback;

/**
 * ==============================================
 * <p>
 * 类名：IInvokeCallback
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/4/8
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public interface IInvokeCallback<T> {
    T invokeCallback(T t);
}

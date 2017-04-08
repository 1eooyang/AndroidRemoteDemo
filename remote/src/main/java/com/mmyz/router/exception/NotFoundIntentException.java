package com.mmyz.router.exception;

/**
 * ==============================================
 * <p>
 * 类名：
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/4/8
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class NotFoundIntentException extends RuntimeException {
    public NotFoundIntentException(){
        super(("当前跳转Intent为null，请检查！"));
    }
}

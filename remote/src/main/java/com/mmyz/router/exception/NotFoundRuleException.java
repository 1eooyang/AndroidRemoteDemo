package com.mmyz.router.exception;

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

public class NotFoundRuleException extends RuntimeException {
    public NotFoundRuleException(String uri){
        super(String.format("%s 未能匹配到当前路由地址路由规则 %s, 请检查当前路由地址是否有问题?", "UNKNOWN", uri));
    }
    public NotFoundRuleException(String name, String uri) {
        super(String.format("%s 未能匹配到当前路由地址 %s, 请检查当前路由地址是否存有问题?", name, uri));
    }
}

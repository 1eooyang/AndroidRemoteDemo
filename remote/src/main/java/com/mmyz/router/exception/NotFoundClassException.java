package com.mmyz.router.exception;

/**
 * ==============================================
 * <p>
 * 类名：NotFoundClassException
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/28
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class NotFoundClassException extends ClassNotFoundException {
    public NotFoundClassException(){
        super(("\r\n未能匹配到路由地址中页面类 , 请检查当前路由地址是否有问题或者待路由页面是否存在?"));
    }
    public NotFoundClassException(String classPath,String uri){
        super(("\r\n未能发现地址中页面类 , 请检查当前路由地址是否有问题?\r\nURI:"+uri+"\r\nClassPath:"+classPath));
    }
}

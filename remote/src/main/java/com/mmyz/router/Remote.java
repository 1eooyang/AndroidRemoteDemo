package com.mmyz.router;

import android.content.Context;
import android.content.Intent;

import com.mmyz.router.callback.BaseInvokeCallback;
import com.mmyz.router.callback.IInvokeCallback;
import com.mmyz.router.exception.NotFoundClassException;
import com.mmyz.router.exception.NotFoundIntentException;
import com.mmyz.router.exception.NotFoundRuleException;
import com.mmyz.router.operator.IOperator;

import java.util.regex.Pattern;

/**
 * ==============================================
 * <p>
 * 类名：Remote
 * &nbsp 具体远程调用类
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/28
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class Remote {

    private final static String PATTERN = "";

    /**
     * 添加自定义路由操作
     * @param protocol 路由协议
     * @param operator 路由操作类
     * @return
     */
    public static RemoteOperatorManager putCoustomOprator(String protocol, IOperator operator){
        return RemoteOperatorManager.get().putCustomOperator(protocol,operator);
    }

    /**
     * 根据默认规则自动解析Uri
     * @param uri 路由地址 Ac
     */
    public static RemoteOperatorManager putRemoteUriDefaultPattern(String uri) throws NotFoundClassException {
        // (activity://com.mmyz.account.LoginActivity)
        Pattern pattern = Pattern.compile("[/]+");
        String[] infos = pattern.split(uri);
        String protocol = infos[0];
        String page = infos[1];
        System.out.println("==============="+page);
        try {
            putRemoteUri(uri,Class.forName(page));
        } catch (ClassNotFoundException e) {
            throw new NotFoundClassException(page, uri);
        }
        return RemoteOperatorManager.get();
    }


    /**
     * 添加路由地址
     * @param uri 路由地址
     * @return
     */
    public static<T> RemoteOperatorManager putRemoteUri(String uri,Class<T> clazz){
        return RemoteOperatorManager.get().putRemoteUri(uri,clazz);
    }

    /**
     * 启用路由地址
     * @param ctx Context
     * @param uri 路由地址
     * @return
     */
    public static <V> V invoke(Context ctx, String uri){
        if (checkUri(uri)){
            IOperator<?, V> operator = RemoteOperatorManager.get().getOperator(uri);
            return operator.invoke(ctx,uri);
        }else {
            throw new NotFoundRuleException(uri);
        }
    }

    /**
     * Activity路由跳转
     * @param context Context
     * @param uri 路由地址
     * @param invokeCallback  调用回调，处理Intent传值
     */
    public static void startActivity(Context context,String uri,BaseInvokeCallback<Intent> invokeCallback){
        Intent intent = invoke(context, uri);
        intent = invokeCallback.invokeCallback(intent);
        if (intent != null){
            context.startActivity(intent);
        }else {
            throw new NotFoundIntentException();
        }
    }



    /**
     * 路由地址检查
     * @param uri 路由地址
     * @return
     */
    public static boolean checkUri(String uri){
        return RemoteOperatorManager.get().checkOperatorForURI(uri);
    }

}

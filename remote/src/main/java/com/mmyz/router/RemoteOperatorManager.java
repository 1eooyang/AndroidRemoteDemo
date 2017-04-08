package com.mmyz.router;

import android.content.Context;
import android.text.TextUtils;

import com.mmyz.router.exception.NotFoundRuleException;
import com.mmyz.router.exception.NotFountRemotePathException;
import com.mmyz.router.operator.ActivityIntentOperator;
import com.mmyz.router.operator.IOperator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * ==============================================
 * <p>
 * 类名：RemoteOperatorManager
 * &nbsp 路由操作管理 单例模式 single instance
 * &nbsp 可通过{@link RemoteOperatorManager#get()}获取对象
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/27
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class RemoteOperatorManager {

    private static RemoteOperatorManager mRemoteManager;
    //路由操作管理池
    private HashMap<String,IOperator> mOperatorPool;
    private RemoteOperatorManager(){
        mOperatorPool = new LinkedHashMap<>();
        putDefaultOperator();
    }

    //初始化默认路由操作
    private void putDefaultOperator() {
        if (mOperatorPool != null){
            mOperatorPool.put(ActivityIntentOperator.PROTOCOL,new ActivityIntentOperator());
        }
    }

    /**
     * 获取RemoteOperatorManager
     * @return RemoteOperatorManager
     */
    public static RemoteOperatorManager get(){
        if (mRemoteManager == null){
            synchronized (RemoteOperatorManager.class){
                mRemoteManager = new RemoteOperatorManager();
            }
        }
        return mRemoteManager;
    }

    /**
     * 添加自定义 路由操作
     * @param protocol 路由协议 {@link ActivityIntentOperator#PROTOCOL}
     * @param operator 具体操作类
     */
    public RemoteOperatorManager putCustomOperator(String protocol,IOperator operator){
        if (mOperatorPool != null){
            mOperatorPool.put(protocol,operator);
        }
        return mRemoteManager;
    }


    /**
     * 检查当前路由操作 是否存在
     * @param  uri 路由地址
     * @return false 不存在 true 存在
     */
    public boolean checkOperatorForURI(String uri){
        if (!TextUtils.isEmpty(uri)){
            IOperator<?, ?> operator = getOperator(uri);
            if (operator == null){
                throw new NotFoundRuleException(uri);
            }
            return true;
        }else {
            throw new NotFountRemotePathException();
        }
    }

    public boolean checkOpratorForProtocol(String protocol){
        return mOperatorPool != null && mOperatorPool.keySet().contains(protocol);
    }

    /**
     * 根据Uri获取路由操作类
     * @param uri 路由地址
     */
    public  <T,V> IOperator<T,V> getOperator(String uri){
        IOperator<T,V> operator = null;
        if (mOperatorPool != null){
            Set<String> protocols = mOperatorPool.keySet();
            for (String protocol :
                    protocols) {
                if (uri.startsWith(protocol)){
                    operator = mOperatorPool.get(protocol);
                    break;
                }
            }
        }
        return operator;
    }

    public <T> RemoteOperatorManager putRemoteUri(String uri, Class<T> clazz) {
        if (checkOperatorForURI(uri)){
            IOperator<T, ?> operator = getOperator(uri);
            operator.put(uri,clazz);
        }
        return mRemoteManager;
    }
}

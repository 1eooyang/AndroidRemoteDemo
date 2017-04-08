package com.mmyz.androidremotemodule.application;

import android.app.Application;

import com.mmyz.Modules;
import com.mmyz.RemoteRegister;
import com.mmyz.common.IRemoteModuleConfig;

/**
 * ==============================================
 * <p>
 * 类名：RemoteApp
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/28
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */
@Modules({
          IRemoteModuleConfig.ACCOUNT_MODULE,
          IRemoteModuleConfig.PRODUCT_MODULE,
          IRemoteModuleConfig.ORDER_MODULE})
public class RemoteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRemote();
    }

    private void initRemote() {
        RemoteRegister.register();
//        try {
//            Remote.putRemoteUriDefaultPattern(ActivityIntentOperator.PROTOCOL+ LoginActivity.REMOTE_URL);
//        } catch (NotFoundClassException e) {
//            Log.e("=========",e.getMessage());
//            e.printStackTrace();
//        }
    }
}

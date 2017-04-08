package com.mmyz.androidremotemodule.application;

import android.app.Application;
import android.content.Intent;

import com.mmyz.Modules;
import com.mmyz.RemoteRegister;
import com.mmyz.account.LoginActivity;
import com.mmyz.common.IRemoteModuleConfig;
import com.mmyz.common.IRemoteUrlConfig;
import com.mmyz.router.Remote;
import com.mmyz.router.operator.ActivityIntentOperator;

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
//        Remote.putRemoteUri(ActivityIntentOperator.PROTOCOL+ IRemoteUrlConfig.LOGIN_REMOTE_URL, LoginActivity.class);
        RemoteRegister.register();
//        try {
//            Remote.putRemoteUriDefaultPattern(ActivityIntentOperator.PROTOCOL+ LoginActivity.REMOTE_URL);
//        } catch (NotFoundClassException e) {
//            Log.e("=========",e.getMessage());
//            e.printStackTrace();
//        }
    }
}

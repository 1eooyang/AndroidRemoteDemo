package com.mmyz.router.operator;

import android.support.v7.app.AppCompatActivity;

import com.mmyz.router.exception.NotFoundRuleException;

/**
 * ==============================================
 * <p>
 * 类名：ActivityIntentOperator
 * &nbsp 针对Activity路由操作
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/28
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */

public class ActivityIntentOperator extends BaseIntentOperator<AppCompatActivity> {
    public static final String PROTOCOL = "activity://";

    @Override
    public void throwException(String uri) {
        throw new NotFoundRuleException(ActivityIntentOperator.class.getCanonicalName(),uri);
    }
}

package com.mmyz.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mmyz.Module;
import com.mmyz.StaticRemote;
import com.mmyz.common.IRemoteModuleConfig;
import com.mmyz.common.IRemoteUrlConfig;
import com.mmyz.common.ISharedPreferencesConfig;
import com.mmyz.common.utils.SharedPreferencesUtil;
import com.mmyz.router.Remote;
import com.mmyz.router.callback.BaseInvokeCallback;
import com.mmyz.router.operator.ActivityIntentOperator;

/**
 * ==============================================
 * <p>
 * 类名：登录页面
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/27
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */
@Module(IRemoteModuleConfig.ACCOUNT_MODULE)
@StaticRemote(ActivityIntentOperator.PROTOCOL+ IRemoteUrlConfig.LOGIN_REMOTE_URL)
public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    private EditText etUserName;
    private EditText etPassword;
    private String passwordStr;
    private String usernameStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()){
                    String saveUsername = SharedPreferencesUtil.getString(LoginActivity.this,
                            usernameStr+ISharedPreferencesConfig.USER_NAME, "");
                    if (TextUtils.isEmpty(saveUsername)){
                        showToast("还未注册");
                    }else {
                        String savePassword = SharedPreferencesUtil.getString(LoginActivity.this,
                                usernameStr + ISharedPreferencesConfig.USER_NAME, "");
                        if (passwordStr.equals(savePassword)){
                            showToast("登录成功");
                            SharedPreferencesUtil.saveString(
                                    LoginActivity.this,
                                    ISharedPreferencesConfig.IS_LOGIN,
                                    "loginSuccess");
                            Remote.startActivity(
                                    LoginActivity.this,
                                    ActivityIntentOperator.PROTOCOL+IRemoteUrlConfig.PRODUCT_REMOTE_URL,
                                    new BaseInvokeCallback<Intent>());
                            finish();
                        }else {
                            showToast("密码错误");
                        }
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Remote.startActivity(
                        LoginActivity.this,
                        ActivityIntentOperator.PROTOCOL+IRemoteUrlConfig.REGISTER_REMOTE_URL,
                        new BaseInvokeCallback<Intent>());
                finish();
            }
        });

    }

    private boolean checkInput() {
        usernameStr = etUserName.getText().toString();
        passwordStr = etPassword.getText().toString();
        if (TextUtils.isEmpty(usernameStr)){
            showToast("请输入用户名");
            return false;
        }

        if (TextUtils.isEmpty(passwordStr)){
            showToast("请输入密码");
            return false;
        }
        return true;
    }


    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}

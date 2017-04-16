package com.mmyz.account;

import android.content.Intent;
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
import com.mmyz.common.RemoteModuleConfig;
import com.mmyz.common.RemoteUrlConfig;
import com.mmyz.common.SharedPreferencesConfig;
import com.mmyz.common.utils.SharedPreferencesUtil;
import com.mmyz.router.Remote;
import com.mmyz.router.callback.BaseInvokeCallback;
import com.mmyz.router.operator.ActivityIntentOperator;

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
@Module(RemoteModuleConfig.ACCOUNT_MODULE)
@StaticRemote(ActivityIntentOperator.PROTOCOL+ RemoteUrlConfig.REGISTER_REMOTE_URL)
public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    private EditText etUserName;
    private EditText etPassword;
    private String usernameStr;
    private String passwordStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()){
                    SharedPreferencesUtil.saveString(
                            RegisterActivity.this,
                            usernameStr+ SharedPreferencesConfig.USER_NAME,
                            usernameStr);
                    SharedPreferencesUtil.saveString(
                            RegisterActivity.this,
                            passwordStr+ SharedPreferencesConfig.PASS_WORD,
                            passwordStr);
                    SharedPreferencesUtil.saveString(
                            RegisterActivity.this,
                            SharedPreferencesConfig.IS_LOGIN,
                            "loginSuccess");
                    showToast("注册成功");
                    Remote.startActivity(
                            RegisterActivity.this,
                            ActivityIntentOperator.PROTOCOL+ RemoteUrlConfig.PRODUCT_REMOTE_URL,
                            new BaseInvokeCallback<Intent>());
                    finish();
                }
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

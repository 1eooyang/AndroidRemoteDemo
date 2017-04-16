package com.mmyz.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
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
 * 类名：ProductActivity
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/4/8
 * <p>
 * 邮箱：ms_liu163@163.com
 * <p>
 * ==============================================
 */
@Module(RemoteModuleConfig.PRODUCT_MODULE)
@StaticRemote(ActivityIntentOperator.PROTOCOL+ RemoteUrlConfig.PRODUCT_REMOTE_URL)
public class ProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        final TextView productTitle = (TextView) findViewById(R.id.tv_productTitle);
        final TextView productPrice = (TextView) findViewById(R.id.tv_productPrice);

        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogin()) {
                    Remote.startActivity(ProductActivity.this,
                            ActivityIntentOperator.PROTOCOL + RemoteUrlConfig.ORDER_REMOTE_URL
                            , new BaseInvokeCallback<Intent>() {
                                @Override
                                public Intent invokeCallback(Intent intent) {
                                    intent.putExtra("PRODUCT_TITLE", productTitle.getText().toString());
                                    intent.putExtra("PRODUCT_PRICE", productPrice.getText().toString());
                                    return super.invokeCallback(intent);
                                }
                            }
                    );
                }else {
                    showToast("请先登录");
                    Remote.startActivity(ProductActivity.this,
                            ActivityIntentOperator.PROTOCOL + RemoteUrlConfig.LOGIN_REMOTE_URL
                            , new BaseInvokeCallback<Intent>()
                    );
                    finish();
                }
            }
        });
    }

    private boolean checkLogin() {
        String isLogin = SharedPreferencesUtil.getString(ProductActivity.this, SharedPreferencesConfig.IS_LOGIN, "");
        return !TextUtils.isEmpty(isLogin);
    }


    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}

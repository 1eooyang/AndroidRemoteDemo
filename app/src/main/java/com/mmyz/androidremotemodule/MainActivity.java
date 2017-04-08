package com.mmyz.androidremotemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mmyz.IRemoteConfig;
import com.mmyz.Module;
import com.mmyz.StaticRemote;
import com.mmyz.account.LoginActivity;
import com.mmyz.common.IRemoteModuleConfig;
import com.mmyz.common.IRemoteUrlConfig;
import com.mmyz.router.Remote;
import com.mmyz.router.callback.BaseInvokeCallback;
import com.mmyz.router.operator.ActivityIntentOperator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Module(IRemoteModuleConfig.APP_MODULE)
@StaticRemote(ActivityIntentOperator.PROTOCOL+IRemoteUrlConfig.MAIN_REMOTE_URL)
public class MainActivity extends AppCompatActivity {

    Button btnAccount;
    Button btnProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAccount = (Button) findViewById(R.id.btn_account);
        btnProduct = (Button) findViewById(R.id.btn_product);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent invoke = Remote.invoke(MainActivity.this, ActivityIntentOperator.PROTOCOL + IRemoteUrlConfig.LOGIN_REMOTE_URL);
//                startActivity(invoke);
                Remote.startActivity(
                        MainActivity.this,
                        ActivityIntentOperator.PROTOCOL + IRemoteUrlConfig.LOGIN_REMOTE_URL,
                        new BaseInvokeCallback<Intent>());
            }
        });



        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Remote.startActivity(
                        MainActivity.this,
                        ActivityIntentOperator.PROTOCOL + IRemoteUrlConfig.PRODUCT_REMOTE_URL,
                        new BaseInvokeCallback<Intent>());
            }
        });
    }

}

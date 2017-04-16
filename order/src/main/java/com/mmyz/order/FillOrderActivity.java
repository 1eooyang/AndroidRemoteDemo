package com.mmyz.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mmyz.Module;
import com.mmyz.StaticRemote;
import com.mmyz.common.RemoteModuleConfig;
import com.mmyz.common.RemoteUrlConfig;
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

@Module(RemoteModuleConfig.ORDER_MODULE)
@StaticRemote(ActivityIntentOperator.PROTOCOL+ RemoteUrlConfig.ORDER_REMOTE_URL)
public class FillOrderActivity extends AppCompatActivity {

    private TextView tvProductTitle;
    private TextView tvProductPrice;
    private TextView tvTotalPrice;
    private TextView etProductNumber;
    private String productPrice;
    private String productTitle;
    private Button btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_order);

        tvProductTitle = (TextView) findViewById(R.id.tv_productTitle);
        tvProductPrice = (TextView) findViewById(R.id.tv_productPrice);
        tvTotalPrice = (TextView) findViewById(R.id.tv_totalPrice);
        etProductNumber = (TextView) findViewById(R.id.et_productNumber);
        btnOrder = (Button) findViewById(R.id.btn_order);

        productTitle = getIntent().getStringExtra("PRODUCT_TITLE");
        productPrice = getIntent().getStringExtra("PRODUCT_PRICE");

        tvProductTitle.setText(productTitle);
        tvProductPrice.setText(productPrice);

        etProductNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String numberStr = s.toString();
                if (!TextUtils.isEmpty(numberStr)){
                    Integer numberInt = Integer.valueOf(numberStr);
                    Integer priceInt = Integer.valueOf(productPrice);
                    tvTotalPrice.setText("总价："+(numberInt * priceInt));
                }else {
                    tvTotalPrice.setText("总价：");
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("下单成功");
                Remote.startActivity(
                        FillOrderActivity.this,
                        ActivityIntentOperator.PROTOCOL+ RemoteUrlConfig.MAIN_REMOTE_URL,
                        new BaseInvokeCallback<Intent>()
                );
                finish();
            }
        });
    }


    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}

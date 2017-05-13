package com.ybslux.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.ybslux.android.R;
import com.ybslux.android.alipay.AlipayUtil;
import com.ybslux.android.alipay.OnAlipayPayInterface;
import com.ybslux.android.model.WeixinConfig;

public class PayActivity extends Activity implements View.OnClickListener, OnAlipayPayInterface, IWXAPIEventHandler {
    private TextView weixin_tv;
    private TextView zhifubao_tv;
    private static String mSalesNo = null, mSalesId;// 订单号
    private static String mMoney = null;// 价格
    private static WeixinConfig wxModel=null;
    private AlipayUtil onAlipayPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        weixin_tv = (TextView) findViewById(R.id.weixin_tv);
        zhifubao_tv = (TextView) findViewById(R.id.zhifubao_tv);
        weixin_tv.setOnClickListener(this);
        zhifubao_tv.setOnClickListener(this);

        onAlipayPay = new AlipayUtil(this);
        onAlipayPay.setOnAlipayPayInterface(this);
    }

    private void showWxMsg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请先安装微信");// 设置内容
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();// 获取dialog
        dialog.show();// 显示对话框
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin_tv:
                final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, wxModel.getAppid());
                msgApi.registerApp(wxModel.getAppid());
                if (!(msgApi.isWXAppInstalled() && msgApi.isWXAppSupportAPI())) {
                    showWxMsg();
                    return;
                }
                PayReq req = new PayReq();
                req.appId = wxModel.getAppid();
                req.partnerId = wxModel.getMch_id();
                req.prepayId = wxModel.getPrepay_id();
                req.packageValue = "Sign=WXPay";
                req.nonceStr = wxModel.getNonce_str();
                req.timeStamp = wxModel.getTimestamp();
                req.sign = wxModel.getSign();

                msgApi.sendReq(req);
                break;
            case R.id.zhifubao_tv:
                onAlipayPay.pay(mSalesNo, mSalesNo, mMoney);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAlipayPay(int payresult) {
        switch (payresult) {
            case AlipayUtil.PAY_SUCCESS:
                paySuccess();
                break;
            case AlipayUtil.PAY_FAIL:
                break;
            case AlipayUtil.PAY_DEALING:
                break;
        }
    }

    private void paySuccess() {
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
}

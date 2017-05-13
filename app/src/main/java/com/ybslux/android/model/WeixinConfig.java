package com.ybslux.android.model;/**
 * Created by Administrator on 2017/1/19.
 */

/**
 * @author frank.fun@qq.com
 * @version V1.0
 * @Title: weixinConfig
 * @Package com.hc.fcsc.model
 * @Description:
 * @date 2017/1/19 10:53
 */
public class WeixinConfig {
    private String appid;// "",
    private String mch_id;// "",
    private String prepay_id;// "",
    private String nonce_str;// "",
    private String timestamp;// "1481121968",
    private String sign;// "67F82B130BEFB12176F451153772C4E2"

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

package com.ht.alipay.config;

import com.ht.properties.PropertyUtil;


/**
 * 支付宝支付所需的必要参数
 * 理想情况下只需配置此处
 */
public class ALiPayConfig {
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = PropertyUtil.partner;
    //appid
    public static String appid = PropertyUtil.appid;
    //商户支付宝账号
    public static String seller_email= PropertyUtil.seller_email;
    //商户真实姓名
    public static String account_name = PropertyUtil.account_name;
    // 商户的私钥RSA
    public static String private_key = PropertyUtil.private_key;
    
    //支付宝的公钥  RSA
    //public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZWrNPCbklT6cX2RtIn5eqC+0Q7SPCKJNMfwtl3UzYZ8lCn9TvAboAJv1k7CstFDBlKU++m6cWDnarSedTvT/vHTZfgt0hX+oAXVYpNmoOsMC7DsZsMogj946Sq9ReeP79ByvDITkInq7IOY9uH5qPAnx+b73JUPQT4kTLcjMvnQIDAQAB";
    
    public static String ali_public_key = PropertyUtil.ali_public_key;
    //public static String ali_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi40N9LX9Gmb/6DQiRvdgrYEsHdHAtqNsypg+dfVAjVLdonZ9AXIpJjFp1JRCi/NsJu16yOllWtZiXM7QMAwiFq7YxVkKbu5wLbl4dBzXefDonRykHRxozlIGga6C6s0jCwmGmijAWhYUmfFHU7gnx/oeHKv4gMzIy/P7g64G5xeumacCWcK//2r82yZNFoRvgh7mI4nXsM+0HKashTpuMLL/O1/trcjC/0wv71Nuj4O9jXnSrkZdhLC7belaqQ0fS3IJ+Vr9/HhgfkpxiYP1ln46KGu/xiqt8eJRnJDH5Ak+X2TuNpXPw8qtD9YivxHHooRka566ttQ0Ji4H8kmNYwIDAQAB";
    
    //签名方式 (支付回调签名方式)
    public static String pay_sign_type = "RSA";
    
    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
     * 这里需要测试的话，需要用外网测试。https://www.ngrok.cc/   这里有免费和付费的，实际上，免费用一下就可以了。
     */
    public static String notify_url = PropertyUtil.baseUrl+"/ALiPay/AfterPayNotify";
    
    //商品的标题/交易标题/订单标题/订单关键字等。
    public static String subject = "";
    
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    
    //接口名称		固定为：alipay.trade.app.pay
    public static String method = "alipay.trade.app.pay";
  
    //调用的接口版本，固定为：1.0
    public static String version = "1.0";
    
    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    public static String product_code = "QUICK_MSECURITY_PAY";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    
    public static String refuUrl = "https://openapi.alipay.com/gateway.do";

}

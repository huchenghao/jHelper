package com.ht.pay.wx.util;


import com.ht.properties.PropertyUtil;
import com.ht.properties.WXPayPropertyUtil;



public class WeixinConfigUtils {
	
	
	public static String appid = WXPayPropertyUtil.weixin_appid;
	public static String mch_id = WXPayPropertyUtil.weixin_mch_id;
	public static String notify_url = PropertyUtil.baseUrl+"/wxpayment/callback";
	
	
}

package com.ht.wx;

import org.springframework.beans.factory.annotation.Value;

import com.ht.properties.PropertyUtil;



public class WeixinConfigUtils {
	
	
	public static String appid = "wxebb2da2afa29a5bb";
	public static String mch_id = "1452880302";
	public static String notify_url = PropertyUtil.baseUrl+"/wxpayment/callback";
	
	
}

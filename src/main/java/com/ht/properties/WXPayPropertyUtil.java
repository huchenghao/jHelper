package com.ht.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WXPayPropertyUtil {
	
	/**
	 * 放退款证书的路径
	 * 	如："/opt/app/doushicert/apiclient_cert.p12"
	 */
	public static String apiclient_cert_file_url;
	
	@Value("${weixin.apiclient_cert_file_url}")
	public static void setApiclient_cert_file_url(String apiclient_cert_file_url) {
		WXPayPropertyUtil.apiclient_cert_file_url = apiclient_cert_file_url;
	}
	/**
	 * weixin_appid
	 */
	public static String weixin_appid;
	
	@Value("${weixin.weixin_appid}")
	public static void setWeixin_appid(String weixin_appid) {
		WXPayPropertyUtil.weixin_appid = weixin_appid;
	}

	/**
	 * weixin_mch_id
	 */
	public static String weixin_mch_id;
	
	@Value("${weixin.weixin_appid}")
	public static void setWeixin_mch_id(String weixin_mch_id) {
		WXPayPropertyUtil.weixin_mch_id = weixin_mch_id;
	}
}

package com.ht.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PropertyUtil {
	/**
	 * 应用唯一标识
	 */
	public static String appid;   

	@Value("${alipay.appid}") 
	public void setAppid(String appid) {
		PropertyUtil.appid = appid;
	}
	/**
	 * 商户号
	 */
	public static String mch_id;   
	
	@Value("${alipay.mch_id}") 
	public void setMch_id(String mch_id) {
		PropertyUtil.mch_id = mch_id;
	}
	/**
	 * 合作身份者ID，以2088开头由16位纯数字组成的字符串
	 */
	public static String partner;
	
	@Value("${ALiPayConfig.partner}")
	public static void setPartner(String partner) {
		PropertyUtil.partner = partner;
	}
	/**
	 * /商户支付宝账号
	 */
	public static String seller_email;
	
	@Value("${ALiPayConfig.seller_email}")
	public static void setSeller_email(String seller_email) {
		PropertyUtil.seller_email = seller_email;
	}
	/**
	 * 商户真实姓名
	 */
	public static String account_name;
	
	@Value("${ALiPayConfig.account_name}")
	public static void setAccount_name(String account_name) {
		PropertyUtil.account_name = account_name;
	}
	/**
	 * 商户的私钥RSA
	 */
	public static String private_key;

	@Value("${ALiPayConfig.private_key}")
	public static void setPrivate_key(String private_key) {
		PropertyUtil.private_key = private_key;
	}
	/**
	 * 支付宝的公钥  RSA
	 */
	public static String ali_public_key;
	
	@Value("${ALiPayConfig.ali_public_key}")
	public static void setAli_public_key(String ali_public_key) {
		PropertyUtil.ali_public_key = ali_public_key;
	}
	/**
	 * 放退款证书的路径
	 * 	如："/opt/app/doushicert/apiclient_cert.p12"
	 */
	public static String apiclient_cert_file_url;
	
	@Value("${weixin.apiclient_cert_file_url}")
	public static void setApiclient_cert_file_url(String apiclient_cert_file_url) {
		PropertyUtil.apiclient_cert_file_url = apiclient_cert_file_url;
	}
	/**
	 * rongyunappkey
	 */
	public static String rongyun_appKey;   
	
	@Value("${rongyun.appKey}") 
	public void setRongyun_appKey(String rongyun_appKey) {
		PropertyUtil.rongyun_appKey = rongyun_appKey;
	}
	/**
	 * rongyunappsecret
	 */
	public static String rongyun_appSecret;
	
	@Value("${rongyun.appSecret}") 
	public  void setRongyun_appSecret(String rongyun_appSecret) {
		PropertyUtil.rongyun_appSecret = rongyun_appSecret;
	}
	
	public static String baseUrl;
	
	@Value("${base.url}") 
	public  void setBaseUrl(String baseUrl) {
		PropertyUtil.baseUrl = baseUrl;
	}
	
	public static String picHttp;

	@Value("${base.picUrl}") 
	public  void setPicHttp(String picHttp) {
		PropertyUtil.picHttp = picHttp;
	}
	
	public static boolean jpush_ios_mode;
	
	@Value("${jpush.ios.mode}") 
	public  void setJpush_ios_mode(boolean jpush_ios_mode) {
		PropertyUtil.jpush_ios_mode = jpush_ios_mode;
	}
	
	public static String spring_profiles_active;
	
	@Value("${spring.profiles.active}") 
	public  void setSpring_profiles_active(String spring_profiles_active) {
		PropertyUtil.spring_profiles_active = spring_profiles_active;
	}
}

package com.ht.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayPropertyUtil {

	/**
	 * 应用唯一标识
	 */
	public static String appid;   

	@Value("${alipay.appid}") 
	public void setAppid(String appid) {
		AlipayPropertyUtil.appid = appid;
	}
	/**
	 * 商户号
	 */
	public static String mch_id;   
	
	@Value("${alipay.mch_id}") 
	public void setMch_id(String mch_id) {
		AlipayPropertyUtil.mch_id = mch_id;
	}
	/**
	 * 合作身份者ID，以2088开头由16位纯数字组成的字符串
	 */
	public static String partner;
	
	@Value("${alipay.partner}")
	public static void setPartner(String partner) {
		AlipayPropertyUtil.partner = partner;
	}
	/**
	 * /商户支付宝账号
	 */
	public static String seller_email;
	
	@Value("${alipay.seller_email}")
	public static void setSeller_email(String seller_email) {
		AlipayPropertyUtil.seller_email = seller_email;
	}
	/**
	 * 商户真实姓名
	 */
	public static String account_name;
	
	@Value("${alipay.account_name}")
	public static void setAccount_name(String account_name) {
		AlipayPropertyUtil.account_name = account_name;
	}
	/**
	 * 商户的私钥RSA
	 */
	public static String private_key;

	@Value("${alipay.private_key}")
	public static void setPrivate_key(String private_key) {
		AlipayPropertyUtil.private_key = private_key;
	}
	/**
	 * 支付宝的公钥  RSA
	 */
	public static String ali_public_key;
	
	@Value("${alipay.ali_public_key}")
	public static void setAli_public_key(String ali_public_key) {
		AlipayPropertyUtil.ali_public_key = ali_public_key;
	}
}

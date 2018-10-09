package com.ht.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PropertyUtil {
	
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
	
	
	
	public static String spring_profiles_active;
	
	@Value("${spring.profiles.active}") 
	public  void setSpring_profiles_active(String spring_profiles_active) {
		PropertyUtil.spring_profiles_active = spring_profiles_active;
	}
}

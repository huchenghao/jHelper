package com.ht.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * @ClassName: JpushPropertyUtil
 * @Description: 极光推送属性
 * @author: huchenghao
 * @date: 2018年10月8日 上午11:32:59
 */
@Component
public class JpushPropertyUtil {
	
	//针对ios区分开发模式/生产模式
	public static boolean jpush_ios_mode;
	
	@Value("${jpush.ios.mode}") 
	public  void setJpush_ios_mode(boolean jpush_ios_mode) {
		JpushPropertyUtil.jpush_ios_mode = jpush_ios_mode;
	}
	//极光推送分配的appkey
	public static String appKey;
	
	@Value("${jpush.appKey}")
	public  void setAppKey(String appKey) {
		JpushPropertyUtil.appKey = appKey;
	}
	//极光推送分配的masterSecret
	public static String masterSecret;
	@Value("${jpush.masterSecret}")
	public  void setMasterSecret(String masterSecret) {
		JpushPropertyUtil.masterSecret = masterSecret;
	}
	
	
	
	
}

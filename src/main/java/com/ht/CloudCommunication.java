package com.ht;
import java.util.HashMap;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
/**
 * 
 * @ClassName: CloudCommunication
 * @Description: 短信发送工具类
 * @author: huchenghao
 * @date: 2017年9月26日 下午12:38:55
 */
public class CloudCommunication {
	/*
	 * 服务器端地址 
	 *  开发:sandboxapp.cloopen.com 
	 *  生产:app.cloopen.com
	 */
	public static String serverIP = "app.cloopen.com";
	
	public static String serverPort = "8883";//端口
	
	public static CCPRestSmsSDK smsapi = null;
	
	public HashMap<String, Object> result = null;
	
	
	/*
	 * 初始化服务器地址和端口
	 * 初始化主账号和主账号令牌
	 * 初始化应用ID
	 */
	public CloudCommunication() {
		
	}

	/**
	 * 
	 * @Title: sendSmsByTemplet
	 * @Description: 短信发送
	 * @param phone
	 * @param datas
	 * @param account_sid
	 * @param auth_token
	 * @param app_id
	 * @param templet_id
	 * @return
	 * @return: boolean
	 */
	public static boolean sendSmsByTemplet(String phone, String[] datas,String account_sid,String auth_token,String app_id,String templet_id) {
		try{
			smsapi = new CCPRestSmsSDK();
			smsapi.init(serverIP, serverPort);
			smsapi.setAccount(account_sid, auth_token);
			smsapi.setAppId(app_id);
			smsapi.sendTemplateSMS(phone, templet_id, datas);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
}

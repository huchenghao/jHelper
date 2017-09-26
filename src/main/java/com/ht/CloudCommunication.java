package com.ht;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 
 * @ClassName: CloudCommunication
 * @Description: 云通讯发送短信平台
 * @author: huchenghao
 * @date: 2017年9月26日 下午6:32:35
 */
public class CloudCommunication {
	/**
	 * 服务器端地址 
	 * 开发:sandboxapp.cloopen.com
	 * 生产:app.cloopen.com
	 */
	public static String serverIP = "app.cloopen.com";
	//端口
	public static String serverPort = "8883";
	
	
	public static CCPRestSmsSDK smsapi = null;
	
	public HashMap<String, Object> result = null;

	/**
	 * 
	 * @Title: sendSmsByTemplet
	 * @Description: 短信发送方法
	 * @param phone 接收短信的手机号
	 * @param datas 发送的动态的文本内容String[] datas
	 * @param account_sid 云通讯平台的account_sid
	 * @param auth_token 云通讯平台的auth_token
	 * @param app_id 云通讯平台的app_id
	 * @param templet_id 云通讯平台的短信模板的templet_id
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

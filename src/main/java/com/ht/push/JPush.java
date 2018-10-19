package com.ht.push;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.ht.properties.JpushPropertyUtil;

/**
 * 
 * @ClassName: JPush
 * @Description: 极光推送
 * @author: huchenghao
 * @date: 2018年10月8日 上午11:14:50
 */
public class JPush {
	/**
	 * 
	 * @Title: sendAllAlertNotice
	 * @Description: 【给所有设备推送消息】1、后台发布通知的时候
	 * @param content
	 * @param type
	 * @param typeId
	 * @return
	 * @author huchenghao
	 */
	public static void sendAllAlertNotice(String content,String type,String typeId) throws APIConnectionException, APIRequestException{
		System.out.println("JpushPropertyUtil.masterSecret:"+JpushPropertyUtil.masterSecret);
		System.out.println("JpushPropertyUtil.appKey:"+JpushPropertyUtil.appKey);
		JPushClient jpushClient = new JPushClient(JpushPropertyUtil.masterSecret, JpushPropertyUtil.appKey,null,ClientConfig.getInstance());
		PushPayload pp = buildPushObject_all_all_alert(content, type, typeId);
		PushResult pr = jpushClient.sendPush(pp);
		if(pr == null){
			System.out.println("推送失败");
		}else{
			System.out.println("推送成功==="+pr.getResponseCode());
		}
	}
	
	/**
	 * 
	 * @Title: sendAllPlatFormByTags
	 * @Description: 【根据目标的tag推送】不区分ios、android
	 * @param content
	 * @param type
	 * @param typeId
	 * @param tags
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 * @author huchenghao
	 */
	public static void sendAllPlatFormByTags(String content,String type,String typeId,String tags) throws APIConnectionException, APIRequestException{
		JPushClient jpushClient = new JPushClient(JpushPropertyUtil.masterSecret, JpushPropertyUtil.appKey,null,ClientConfig.getInstance());
		PushPayload pp = buildPushObject_ios_android_with_tags(content, type, typeId,tags);
		PushResult pr = jpushClient.sendPush(pp);
		if(pr == null){
			System.out.println("推送失败");
		}else{
			System.out.println(pr.getResponseCode());
		}
	}
	/**
	 * 
	 * @Title: sendAllPlatform
	 * @Description: 【根据别名进行推送】不区分ios、android
	 * @param alias
	 * @param title
	 * @author huchenghao
	 * @throws APIRequestException 
	 * @throws APIConnectionException 
	 */
	public static void sendAllPlatform(String content,String type,String typeId,String...alias) throws APIConnectionException, APIRequestException{
		JPushClient jpushClient = new JPushClient(JpushPropertyUtil.masterSecret, JpushPropertyUtil.appKey,null,ClientConfig.getInstance());
		PushPayload pp = buildPushObject_ios_android(content, type, typeId,alias);
		PushResult pr = jpushClient.sendPush(pp);
		if(pr == null){
			System.out.println("推送失败");
		}else{
			System.out.println(pr.getResponseCode());
		}
	}
	
	
	
	

	public static PushPayload buildPushObject_all_all_alert(String content,String type,String typeId) {
		return PushPayload.newBuilder()
		.setPlatform(Platform.all())
		.setAudience(Audience.all())
		.setNotification(Notification.newBuilder()
                		.addPlatformNotification(
                				AndroidNotification.newBuilder()
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.build())
                		.addPlatformNotification(
                				IosNotification.newBuilder()
                				.setContentAvailable(true)
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.build())
                				.build()
                		)
		.setOptions(Options.newBuilder().setApnsProduction(JpushPropertyUtil.jpush_ios_mode).build())
                .build();
    }
	

	
	public static PushPayload buildPushObject_ios_android(String content,String type,String typeId,String...alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                		.addPlatformNotification(
                				AndroidNotification.newBuilder()
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.build())
                		.addPlatformNotification(
                				IosNotification.newBuilder()
                				.setContentAvailable(true)
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.build())
                				.build()
                		)
                .setOptions(Options.newBuilder().setApnsProduction(JpushPropertyUtil.jpush_ios_mode).build())
                .build();
    }
	
	
	public static PushPayload buildPushObject_ios_android_with_tags(String content,String type,String typeId,String tags) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                 .setAudience(Audience.tag(tags))
                .setNotification(Notification.newBuilder()
                		.addPlatformNotification(
                				AndroidNotification.newBuilder()
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.build())
                		.addPlatformNotification(
                				IosNotification.newBuilder()
                				.setAlert(content)
                				.addExtra("type", type)
                				.addExtra("typeId", typeId)
                				.setContentAvailable(true)
                				.build())
                				.build()
                		)
                .setOptions(Options.newBuilder().setApnsProduction(JpushPropertyUtil.jpush_ios_mode).build())
                .build();
    }
	

	
	
	
	
	
	
	

}

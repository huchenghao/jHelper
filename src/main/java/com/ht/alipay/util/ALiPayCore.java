package com.ht.alipay.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
 * 
 * @ClassName: ALiPayCore
 * @Description: alipay pay core
 * @author: huchenghao
 * @date: 2018年8月28日 下午6:47:50
 * 当前版本:3.3.1  生成时间:2018-07-05 22:12:46
 * alipay-sdk-java-3.3.1.jar
 */
public class ALiPayCore {
	private static Logger logger = Logger.getLogger(ALiPayCore.class);
	/**
	 * 
	 * @Title: createAliPayStr
	 * @Description: JAVA服务端SDK生成APP支付订单信息
	 * @param aliPayMap
	 * @return
	 * @author huchenghao
	 * @throws AlipayApiException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String createAliPayStr(Map<String, String> aliPayMap)
			throws Exception{
		AlipayClient alipayClient = new DefaultAlipayClient(
				aliPayMap.get("url"),// 正式环境支付宝网关https://openapi.alipay.com/gateway.do如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
				aliPayMap.get("app_id"), 
				aliPayMap.get("app_private_key"),
				"json", "utf-8", aliPayMap.get("alipay_public_key"), "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(aliPayMap.get("body"));
		model.setSubject(aliPayMap.get("subject"));
		model.setOutTradeNo(aliPayMap.get("out_trade_no"));// 请保证OutTradeNo值每次保证唯一
		model.setTimeoutExpress("30m");
		model.setTotalAmount(aliPayMap.get("total_fee"));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(aliPayMap.get("notify_url"));
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 
	 * @Title: checkAliPayParam
	 * @Description: JAVA服务端验证异步通知信息参数
	 * @param request
	 * @param aliPayMap
	 * @return
	 * @author huchenghao
	 * @throws AlipayApiException 
	 */
	public static boolean checkAliPayParam(HttpServletRequest request,Map<String,String> aliPayMap) throws AlipayApiException{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		boolean flag = AlipaySignature.rsaCheckV1(params, aliPayMap.get("alipay_public_key"), "utf-8","RSA2");
		return flag;
	}
	
	
}

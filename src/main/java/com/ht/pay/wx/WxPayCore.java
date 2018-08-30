package com.ht.pay.wx;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ht.pay.wx.util.HttpXmlUtils;
import com.ht.pay.wx.util.ParseXMLUtils;
import com.ht.pay.wx.util.RandCharsUtils;
import com.ht.pay.wx.util.Unifiedorder;
import com.ht.pay.wx.util.WXSignUtils;
import com.ht.res.ResultGenerator;



public class WxPayCore {
	/**
	 * 
	 * @Title: sign
	 * @Description: APP支付统一下单
	 * @param wxPayMap
	 * @return
	 * @author huchenghao
	 */
	public static String sign(Map<String, String> wxPayMap){
		String nonce_str = RandCharsUtils.getRandomString(32);
		String time_start = RandCharsUtils.timeStart();
		String time_expire = RandCharsUtils.timeExpire();
		
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", wxPayMap.get("appid"));
        parameters.put("mch_id", wxPayMap.get("mch_id"));
        parameters.put("nonce_str", nonce_str);
        parameters.put("body", wxPayMap.get("body"));
        parameters.put("detail", wxPayMap.get("detail"));
        parameters.put("attach", wxPayMap.get("attach"));
        parameters.put("out_trade_no", wxPayMap.get("out_trade_no"));
        parameters.put("total_fee", wxPayMap.get("total_fee"));
        parameters.put("time_start", time_start);
        parameters.put("time_expire", time_expire);
        parameters.put("notify_url", wxPayMap.get("notify_url"));
        parameters.put("trade_type", "APP");
        parameters.put("spbill_create_ip", wxPayMap.get("spbill_create_ip"));
        String sign = WXSignUtils.createSign("UTF-8", parameters,wxPayMap.get("key"));

        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(wxPayMap.get("appid"));
        unifiedorder.setMch_id(wxPayMap.get("mch_id"));
        unifiedorder.setNonce_str(nonce_str);
        unifiedorder.setSign(sign);
        unifiedorder.setBody(wxPayMap.get("body"));
        unifiedorder.setDetail(wxPayMap.get("detail"));
        unifiedorder.setAttach(wxPayMap.get("attach"));
        unifiedorder.setOut_trade_no(wxPayMap.get("out_trade_no"));
        unifiedorder.setTotal_fee(Integer.parseInt(wxPayMap.get("out_trade_no")));
        unifiedorder.setSpbill_create_ip(wxPayMap.get("spbill_create_ip"));
        unifiedorder.setTime_start(time_start);
        unifiedorder.setTime_expire(time_expire);
        unifiedorder.setNotify_url(wxPayMap.get("notify_url"));
        unifiedorder.setTrade_type("APP");

        //构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        String jsonStr =ParseXMLUtils.jdomParseXmlToStr(weixinPost);
        //二次签名
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);   //fromObject(json);
        String appid2 =(String) jsonObject.get("appid");
        String mch_id2 =(String) jsonObject.get("mch_id");
        String nonce_str2 =(String) jsonObject.get("nonce_str");
        String prepay_id2 =(String) jsonObject.get("prepay_id");//预支付交易会话标识	
        //参数：开始生成签名
        SortedMap<Object,Object> parameters2 = new TreeMap<Object,Object>();
        parameters2.put("appid", appid2);
        parameters2.put("partnerid", mch_id2);
        parameters2.put("noncestr", nonce_str2);
        parameters2.put("package", "Sign=WXPay");
        parameters2.put("prepayid", prepay_id2);
        parameters2.put("timestamp", System.currentTimeMillis()/1000);
       
        String sign_result = WXSignUtils.createSign("UTF-8", parameters2,wxPayMap.get("key"));
	    Map<String,Object> map = Maps.newLinkedHashMap();
        map.put("sign", sign_result);
        map.put("timestamp", System.currentTimeMillis()/1000);
        return ResultGenerator.genSuccessResult(map).toString();
       
	}
}

package com.ht.alipay.util;

import java.text.DecimalFormat;
import java.util.Map;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.common.collect.Maps;
import com.ht.alipay.config.ALiPayConfig;
import com.ht.res.MessageInfo;

public class AlipayUtil {

	/**
	 * 
	 * @Title: getAliOrder
	 * @Description: TODO
	 * @author: 李国刚
	 * @param totalAmount	
	 * 	所要支付的金额，格式必须为0.00，单位为元。
	 * @param orderNo
	 * 	传入商户内部订单号，必须唯一；
	 * @param subject
	 * @return
	 * @return: String
	 */
	public static String getAliOrder(String totalAmount, String orderNo, String subject) {
		DecimalFormat   df = new  DecimalFormat("#0.00"); 	//用于格式化充值金额
		totalAmount = df.format(Double.parseDouble(totalAmount));
		String orderString = ALiPayCore.createAliPayStr(orderNo,totalAmount,subject);

		MessageInfo info = new MessageInfo();
		info.setCode("0");
		info.setMsg("");
		info.setResult(orderString);
		JSONObject json = JSONObject.fromObject(info);

        System.out.println(json.toString());
        return json.toString();  
		//	▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲		拼接支付宝所需参数，一般情况下无需修改		▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
	}
	/**
	 * 
	 * @Title: orderPayRefund
	 * @Description: 退款
	 * @author: 李国刚
	 * @param orderNo
	 * 	传入商户内部订单号，必须唯一；
	 * @param total_amount
	 * 	交易金额
	 * @param refund_amount
	 * 	退款金额
	 * @param refundNo
	 * 	退款单号
	 * @param refund_reason
	 * 	退款原因
	 * @return
	 * @return: String
	 */
	public static String orderPayRefund(String orderNo, String total_amount, String refund_amount, 
			String refundNo, String refund_reason) {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest(); // 统一收单交易退款接口
        // 只需要传入业务参数
        Map<String, Object> param = Maps.newHashMap();
        param.put("out_trade_no", orderNo); // 商户订单号
        param.put("total_amount", total_amount);// 交易金额
        param.put("refund_amount", refund_amount);// 退款金额
        param.put("refund_reason", refund_reason);// 退款原因
        param.put("out_request_no", refundNo); //退款单号
        
        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义 
        Map<String, Object> restmap = Maps.newHashMap();// 返回支付宝退款信息
        try {
        	AlipayClient alipayClient = new DefaultAlipayClient(ALiPayConfig.refuUrl, ALiPayConfig.appid,
        			ALiPayConfig.private_key, AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
        			ALiPayConfig.ali_public_key);
            AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
            if (alipayResponse.isSuccess()) {
                // 调用成功，则处理业务逻辑
                if ("10000".equals(alipayResponse.getCode())) {
                    // 订单创建成功
                    restmap.put("out_trade_no", alipayResponse.getOutTradeNo());
                    restmap.put("trade_no", alipayResponse.getTradeNo());
                    restmap.put("buyer_logon_id", alipayResponse.getBuyerLogonId());// 用户的登录id
                    restmap.put("gmt_refund_pay", alipayResponse.getGmtRefundPay()); // 退看支付时间
                    restmap.put("buyer_user_id", alipayResponse.getBuyerUserId());// 买家在支付宝的用户id
                    System.out.println("订单退款结果：退款成功");
                    return "success";
                } else {
                	 System.out.println("订单查询失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
                	 return "noOrder";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
		return "success";
	}
	
}

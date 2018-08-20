package com.ht.alipay.service;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.alipay.util.ALiPayCore;
import com.ht.res.MessageInfo;

@Service
public class ALiPayService {
	
	private static Logger log = Logger.getLogger(ALiPayService.class);
	/**
	 * 
	 * @Title: getAliOrder
	 * @Description: TODO
	 * @author: 李国刚
	 * @param total_amount
	 * @param orderNo
	 * @param subject
	 * @return
	 * @return: Object
	 */
	public Object getAliOrder(String total_amount,String orderNo,String subject){
		
		DecimalFormat   df = new  DecimalFormat("#0.00"); 	//用于格式化充值金额
		total_amount = df.format(Double.parseDouble(total_amount));
		/**
		 * @param out_trade_no	String	传入商户内部订单号，必须唯一；
		 * @param total_fee	String	所要支付的金额，格式必须为0.00，单位为元。
		 */
		String orderString = ALiPayCore.createAliPayStr(orderNo,total_amount,subject);


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
	 * 处理支付宝给的回调
	 * @param request
	 * @param out
	 */
	@Transactional
	public String AliPayAfter(HttpServletRequest request){
		//	▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼		验证签名、基础参数是否是支付宝发来的，一般情况下无需修改		▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
		Map<String, String> aliParam = ALiPayCore.checkAliPayParam(request);
		if(aliParam != null){
			//out.print("success");//执行完自己的业务后必须给反馈。
			String out_trade_no =aliParam.get("out_trade_no");
			String total_amount =aliParam.get("total_amount");
			
    		return "SUCCESS";
		}else{
			log.error("支付宝支付回调验证失败，可能不是支付宝发送的通知。");
			return "ERROR";
		}
		
		
	}


}

package com.ht.alipay.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.common.collect.Maps;
import com.ht.alipay.config.ALiPayConfig;
import com.ht.alipay.service.ALiPayService;

/*
 * @包名 com.net.pay.alipay.handler
 * @类名 ALiPayController
 * @原作者 snail
 * @创建时间 2015-8-22
 * @修改人 wwq
 * @修改时间 2017-3-09
 * @描述 支付宝 [App端得到调起支付宝的支付参数]、[App支付成功的回调]
 */
//@RequestMapping(value = "ALiPay", produces = "text/html;charset=utf-8")
@Controller
@RequestMapping("ALiPay")
public class ALiPayController{
	
	@Autowired
	private ALiPayService aLiPayService;


	/*
	 * 功能：支付宝服务器异步通知
	 * 说明：
	 * 该页面不能在本机电脑测试，请到服务器上做测试[或者外网映射]。请确保外部可以访问该页面。
	 * 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
	 */
	/* 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
	 * 商户订单号 out_trade_no
	 * 支付宝交易号 trade_no
	 * 交易状态 trade_status
	 */
	@RequestMapping(value = "AfterPayNotify", method = RequestMethod.POST)
	public void ALiPayNotify(HttpServletRequest request) {
		aLiPayService.AliPayAfter(request);
	}

}
package com.ht.wx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONObject;

import com.ht.properties.PropertyUtil;
import com.ht.properties.WXPayPropertyUtil;
import com.ht.res.MessageInfo;
import com.ht.text.StringHelper;

public class WXUtil {

	/**
	 * 
	 * @Title: weiXinPay
	 * @Description: 微信支付
	 * @author: 李国刚
	 * @param body	
	 * 	商品描述
	 * @param detail
	 * 	商品详情
	 * @param attach
	 * 	附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	 * @param out_trade_no
	 * 	商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	 * @param total_price
	 * 	订单总金额，单位为分，详见支付金额
	 * @param spbill_create_ip
	 * 	用户端实际ip
	 * @return
	 * @return: String
	 */
	public static String weiXinPay(String body, //商品描述
            String detail,  //商品详情
            String attach,  //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
            String out_trade_no, //商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
            String total_price, //订单总金额，单位为分，详见支付金额
            String spbill_create_ip //用户端实际ip
            ) { 
	 	MessageInfo info = new MessageInfo();
        //参数组
        String appid = WeixinConfigUtils.appid;//微信开放平台审核通过的应用APPID
        System.out.println("appid是："+appid);
        String mch_id = WeixinConfigUtils.mch_id;
        System.out.println("mch_id是："+mch_id);
        String nonce_str = RandCharsUtils.getRandomString(32);
        System.out.println("随机字符串是："+nonce_str);

        //body = body;//"测试微信支付0.01_2";
        //detail = detail;//"0.01_元测试开始";
        //attach = attach;//"备用参数，先留着，后面会有用的";
        //String out_trade_no = OrderUtil.getOrderNo();//"2015112500001000811017342394";
        DecimalFormat   df = new  DecimalFormat("#0.00"); 	//用于格式化充值金额
        String totalfee = df.format(Double.parseDouble(total_price));
        double wx_total_fee=(StringHelper.mul(Double.parseDouble(totalfee), Double.parseDouble("100")));
        int total_fee=(int)wx_total_fee;
       // spbill_create_ip = spbill_create_ip;//"127.0.0.1";

        /**
         * 阿里云国外服务器，微信支付的时候此时间是有问题的。可以不传此值
         */
        //String time_start = RandCharsUtils.timeStart();
        //System.out.println(time_start);
       // String time_expire = RandCharsUtils.timeExpire();
       // System.out.println(time_expire);
        String notify_url = WeixinConfigUtils.notify_url;
        System.out.println("notify_url是："+notify_url);
        String trade_type = "APP";

        //参数：开始生成签名
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("body", body);
        //parameters.put("nonce_str", nonce_str);
        parameters.put("detail", detail);
        parameters.put("attach", attach);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("total_fee", total_fee);
        parameters.put("time_start", "");
        parameters.put("time_expire", "");
        parameters.put("notify_url", notify_url);
        parameters.put("trade_type", trade_type);
        parameters.put("spbill_create_ip", spbill_create_ip);

        String sign = WXSignUtils.createSign("UTF-8", parameters);
        System.out.println("sign："+sign);


        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(appid);
        unifiedorder.setMch_id(mch_id);
        unifiedorder.setNonce_str(nonce_str);
        unifiedorder.setSign(sign);
        unifiedorder.setBody(body);
        unifiedorder.setDetail(detail);
        unifiedorder.setAttach(attach);
        unifiedorder.setOut_trade_no(out_trade_no);
        unifiedorder.setTotal_fee(total_fee);
        unifiedorder.setSpbill_create_ip(spbill_create_ip);
        unifiedorder.setTime_start("");
        unifiedorder.setTime_expire("");
        unifiedorder.setNotify_url(notify_url);
        unifiedorder.setTrade_type(trade_type);

        //构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        String method = "POST";

        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

        String json =ParseXMLUtils.jdomParseXmlToStr(weixinPost);
        
        //二次签名
        JSONObject job = JSONObject.fromObject(json);
        String appid2 =(String) job.get("appid");
        String mch_id2 =(String) job.get("mch_id");
        String nonce_str2 =(String) job.get("nonce_str");
        String prepay_id2 =(String) job.get("prepay_id");
        //参数：开始生成签名
        SortedMap<Object,Object> parameters2 = new TreeMap<Object,Object>();
        parameters2.put("appid", appid2);
        parameters2.put("partnerid", mch_id2);
        parameters2.put("noncestr", nonce_str2);
        parameters2.put("package", "Sign=WXPay");
        parameters2.put("prepayid", prepay_id2);
        parameters2.put("timestamp", System.currentTimeMillis()/1000);
       
        String sign2 = WXSignUtils.createSign("UTF-8", parameters2);
        System.out.println("sign2:"+sign2);
        
        JSONObject job2 = JSONObject.fromObject(json);
        job2.put("sign", sign2);
        job2.put("timestamp", System.currentTimeMillis()/1000);
        
		info.setCode("0");
		info.setMsg("");
		info.setResult(job2);
		JSONObject json1 = JSONObject.fromObject(info);
        return json1.toString();
    }
	/**
	 * 
	 * @Title: winXinRefu
	 * @Description: 微信退款
	 * @author: 李国刚
	 * @param price
	 * @param orderNo
	 * @param refundNo
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws DocumentException
	 * @return: String
	 */
	public static String winXinRefu(String price, String orderNo, String refundNo) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException, DocumentException {
		 
	     int total_fee=StringHelper.mul(Double.parseDouble(price), Double.parseDouble("100")).intValue();
		 SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", WeixinConfigUtils.appid);
        parameters.put("mch_id", WeixinConfigUtils.mch_id);
        parameters.put("nonce_str", RandCharsUtils.getRandomString(32));
       //在notify_url中解析微信返回的信息获取到 transaction_id，此项不是必填，详细请看上图文档
       //parameters.put("transaction_id", "微信支付订单中调用统一接口后微信返回的 transaction_id");
        parameters.put("out_trade_no", orderNo);
        parameters.put("out_refund_no", refundNo);//我们自己设定的退款申请号，约束为UK
        parameters.put("total_fee", ""+total_fee) ; //单位为分
        parameters.put("refund_fee", ""+StringHelper.mul(Double.parseDouble(price), Double.parseDouble(100+"")).intValue()); //单位为分
        String sign = WXSignUtils.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        String reuqestXml = HttpXmlUtils.getRequestXml(parameters);
       
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(WXPayPropertyUtil.apiclient_cert_file_url));//放退款证书的路径
        try {
            keyStore.load(instream, WeixinConfigUtils.mch_id.toCharArray());
        } finally {
            instream.close();
        }
		 SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeixinConfigUtils.mch_id.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");//退款接口
            System.out.println("executing request" + httpPost.getRequestLine());
            StringEntity  reqEntity  = new StringEntity(reuqestXml);
            // 设置类型 
            reqEntity.setContentType("application/x-www-form-urlencoded"); 
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                if (entity != null) {
               	 Map<String, String> map = new HashMap<String, String>();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(bufferedReader);
                    Element root = document.getRootElement();
                    List<Element> elementList = root.elements();
                    for (Element e : elementList) {
     	                map.put(e.getName(), e.getText());
     	             }
                    if(map.get("return_code").equals("SUCCESS")){
                   	 if(map.get("result_code").equals("SUCCESS")){
                   		 //退款成功，存储起来退款记录
                   		 //标记订单是已退款的
                   		 //
                   		 return "success";
                   	 }else if(map.get("result_code").equals("FAIL")){
                   		 //退款失败
                   		 //
                   		 return "fail";
                   	 }
                    }
                    /*for (Map.Entry<String, String> entry : map.entrySet()) {  
               	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
               	} */
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
		 return "success";
	}
	
}

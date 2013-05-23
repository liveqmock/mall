package com.hnfealean.sport.payment.alipay;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.hnfealean.sport.model.payment.alipay.AlipayRequest;


/**
 *功能：支付宝接口公用函数
 *详细：该页面是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改
 *版本：3.1
 *修改日期：2010-11-24
 '说明：
 '以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 '该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

*/

public class AlipayFunction {
	/** 
	 * 功能：生成签名结果
	 * @param sArray 要签名的数组
	 * @param key 安全校验码
	 * @return 签名结果字符串
	 */
	public static String BuildMysign(Map sArray, String key) {
		String prestr = CreateLinkString(sArray);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr + key;                     //把拼接后的字符串再与安全校验码直接连接起来
		String mysign = Md5Encrypt.md5(prestr);
		return mysign;
	}
	public static String generateSign(AlipayRequest alipayRequest){
		Map sPara = new HashMap();
		sPara.put("service",alipayRequest.getService());
		sPara.put("payment_type",alipayRequest.getPayment_type());
		sPara.put("partner", alipayRequest.getPartner());
		sPara.put("seller_email", alipayRequest.getSeller_email());
		sPara.put("return_url", alipayRequest.getReturn_url());
		sPara.put("notify_url", alipayRequest.getNotify_url());
		sPara.put("_input_charset", alipayRequest.get_input_charset());
		sPara.put("show_url", alipayRequest.getShow_url());
		sPara.put("out_trade_no", alipayRequest.getOut_trade_no());
		sPara.put("subject", alipayRequest.getSubject());
		sPara.put("body", alipayRequest.getBody());
		sPara.put("price", alipayRequest.getPrice());
		sPara.put("logistics_fee", alipayRequest.getLogistics_fee());
		sPara.put("logistics_type", alipayRequest.getLogistics_type());
		sPara.put("logistics_payment", alipayRequest.getLogistics_payment());
		sPara.put("quantity", alipayRequest.getQuantity());
		sPara.put("receive_name", alipayRequest.getReceive_name());
		sPara.put("receive_address", alipayRequest.getReceive_address());
		sPara.put("receive_zip", alipayRequest.getReceive_zip());
		sPara.put("receive_phone", alipayRequest.getReceive_phone());
		sPara.put("receive_mobile", alipayRequest.getReceive_mobile());
		sPara.put("logistics_fee_1", alipayRequest.getLogistics_fee_1());
		sPara.put("logistics_type_1", alipayRequest.getLogistics_type_1());
		sPara.put("logistics_payment_1", alipayRequest.getLogistics_payment_1());
		sPara.put("logistics_fee_2", alipayRequest.getLogistics_fee_2());
		sPara.put("logistics_type_2", alipayRequest.getLogistics_type_2());
		sPara.put("logistics_payment_2", alipayRequest.getLogistics_payment_2());
		sPara.put("buyer_email", alipayRequest.getBuyer_email());
		sPara.put("discount", alipayRequest.getDiscount());
		Map sParaNew = AlipayFunction.ParaFilter(sPara); //除去数组中的空值和签名参数
		String mysign = AlipayFunction.BuildMysign(sParaNew, alipayRequest.getKey());//生成签名结果
		return mysign;
	}
	
	/** 
	 * 功能：除去数组中的空值和签名参数
	 * @param sArray 签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map ParaFilter(Map sArray){
		List keys = new ArrayList(sArray.keySet());
		Map sArrayNew = new HashMap();
		
		for(int i = 0; i < keys.size(); i++){
			String key = (String) keys.get(i);
			String value = (String) sArray.get(key);
			
			if( value == null ||value.equals("") || 
					key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
				continue;
			}
			
			sArrayNew.put(key, value);
		}
		
		return sArrayNew;
	}
	
	/** 
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String CreateLinkString(Map params){
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) params.get(key);

			if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}
	
	/** 
	 * 功能：写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void LogResult(String sWord){
		// 该文件存在于和应用服务器 启动文件同一目录下，文件名是alipay log加服务器时间
		try {
			FileWriter writer = new FileWriter("D:\\alipay_log" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
	 * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
	 * @param partner 合作身份者ID
	 * @return 时间戳字符串
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	
	public static String query_timestamp(String partner)
			throws MalformedURLException, DocumentException, IOException {
		String strUrl = "https://mapi.alipay.com/gateway.do?service=query_timestamp&partner="+partner;
		StringBuffer buf1 = new StringBuffer();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new URL(strUrl).openStream());
		
		List<Node> nodeList = doc.selectNodes("//alipay/*");
		
		for (Node node : nodeList) {
			// 截取部分不需要解析的信息
			if (node.getName().equals("is_success")
					&& node.getText().equals("T")) {
				// 判断是否有成功标示
				List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
				for (Node node1 : nodeList1) {
					buf1.append(node1.getText());
				}
			}
		}
		
		return buf1.toString();
	}
}

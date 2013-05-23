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
 *���ܣ�֧�����ӿڹ��ú���
 *��ϸ����ҳ��������֪ͨ���������ļ������õĹ��ú������Ĵ����ļ�������Ҫ�޸�
 *�汾��3.1
 *�޸����ڣ�2010-11-24
 '˵����
 '���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 '�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���

*/

public class AlipayFunction {
	/** 
	 * ���ܣ�����ǩ�����
	 * @param sArray Ҫǩ��������
	 * @param key ��ȫУ����
	 * @return ǩ������ַ���
	 */
	public static String BuildMysign(Map sArray, String key) {
		String prestr = CreateLinkString(sArray);  //����������Ԫ�أ����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
		prestr = prestr + key;                     //��ƴ�Ӻ���ַ������밲ȫУ����ֱ����������
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
		Map sParaNew = AlipayFunction.ParaFilter(sPara); //��ȥ�����еĿ�ֵ��ǩ������
		String mysign = AlipayFunction.BuildMysign(sParaNew, alipayRequest.getKey());//����ǩ�����
		return mysign;
	}
	
	/** 
	 * ���ܣ���ȥ�����еĿ�ֵ��ǩ������
	 * @param sArray ǩ��������
	 * @return ȥ����ֵ��ǩ�����������ǩ��������
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
	 * ���ܣ�����������Ԫ�����򣬲����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
	 * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
	 * @return ƴ�Ӻ��ַ���
	 */
	public static String CreateLinkString(Map params){
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) params.get(key);

			if (i == keys.size() - 1) {//ƴ��ʱ�����������һ��&�ַ�
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}
	
	/** 
	 * ���ܣ�д��־��������ԣ�����վ����Ҳ���ԸĳɰѼ�¼�������ݿ⣩
	 * @param sWord Ҫд����־����ı�����
	 */
	public static void LogResult(String sWord){
		// ���ļ������ں�Ӧ�÷����� �����ļ�ͬһĿ¼�£��ļ�����alipay log�ӷ�����ʱ��
		try {
			FileWriter writer = new FileWriter("D:\\alipay_log" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ܣ����ڷ����㣬���ýӿ�query_timestamp����ȡʱ����Ĵ�����
	 * ע�⣺Զ�̽���XML������������Ƿ�֧��SSL�������й�
	 * @param partner ���������ID
	 * @return ʱ����ַ���
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
			// ��ȡ���ֲ���Ҫ��������Ϣ
			if (node.getName().equals("is_success")
					&& node.getText().equals("T")) {
				// �ж��Ƿ��гɹ���ʾ
				List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
				for (Node node1 : nodeList1) {
					buf1.append(node1.getText());
				}
			}
		}
		
		return buf1.toString();
	}
}

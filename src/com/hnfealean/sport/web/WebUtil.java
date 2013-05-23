package com.hnfealean.sport.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.web.ipaddressutil.IPSeeker;

/**
 * 
 * @author Administrator
 * 
 */
public class WebUtil {
     public static double round(double value, int scale, int roundingMode) {  
	         BigDecimal bd = new BigDecimal(value);  
	         bd = bd.setScale(scale, roundingMode);  
	         double d = bd.doubleValue();  
	         bd = null;  
	         return d;  
	     }  
     public static float round(float value, int scale, int roundingMode) {  
         BigDecimal bd = new BigDecimal(value);  
         bd = bd.setScale(scale, roundingMode);  
         float d = bd.floatValue();  
         bd = null;  
         return d;  
     }  
	public static void setPage(HttpServletRequest request) {
		Integer page=null;
		try {
			page = Integer.valueOf(request.getParameter("page"));
		} catch (Exception e) {
			
			request.setAttribute("page", 1);
			return;
		}
		if(page==null||page==0){
			request.setAttribute("page", 1);
			return;
		}
		request.setAttribute("page", page);
	}
	public static Administrator getAdministrator(HttpServletRequest request) {

		Administrator admin = (Administrator) request.getSession()
				.getAttribute("administrator");

		return admin;
	}

	public static boolean getAdministratorLogin(HttpServletRequest request) {

		Administrator admin = (Administrator) request.getSession()
				.getAttribute("administrator");
		if (admin == null) {
			return false;
		}
		return true;
	}

	public static String encodeStringByBase64(String url) {
		return new String(Base64.encodeBase64(url.getBytes()));
	}

	public static String decodeStringByBase64(String url) {

		return new String(Base64.decodeBase64(url.getBytes()));
	}
	public static DeliverInfo getDeliverInfo(HttpServletRequest request) {
		DeliverInfo deliverInfo = (DeliverInfo) request.getSession().getAttribute("deliverInfo");
		return deliverInfo;
	}
	public static int getDeliverInfoId(HttpServletRequest request) {
		DeliverInfo deliverInfo = (DeliverInfo) request.getSession().getAttribute("deliverInfo");
		if(deliverInfo==null)return 0;
		return deliverInfo.getId();
	}

	public static boolean getUserLogin(HttpServletRequest request) {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		System.out.println("username in session:"+userId);
		if(userId==null)	return false;
		return true;
	}
	public static String getUserEmail(HttpServletRequest request) {

		String email = (String) request.getSession().getAttribute("useremail");
		System.out.println("usermail in session:"+email);
		return email;
	}
	public static String getUserName(HttpServletRequest request) {

		String userName = (String) request.getSession().getAttribute("username");
		System.out.println("username in session:"+userName);
		return userName;
	}
	public static Integer getUserId(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("userId");
		if(obj==null){
			return 0;
		}
		int m;
		try {
			m = (Integer)obj;
		} catch (Exception e) {
			return 0;
		}
		return m;
	}
	public static String getAddressByIp(String ip) {
		IPSeeker ipseeker = IPSeeker.getInstance();
		return ipseeker.getAddress(ip);
	}
	public static String getShoppingCart(HttpServletRequest request,boolean yes) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession()
		.getAttribute("shoppingCart");
		if (shoppingCart == null
				|| shoppingCart.getShoppingCartItems().size() == 0) {
			return "";
		}
		return "总计："+shoppingCart.getOrderTotalPrice()+",包含产品"+shoppingCart.getShoppingCartItems().size()+"个";
	}
	public static boolean getShoppingCart(HttpServletRequest request) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession()
				.getAttribute("shoppingCart");
		if (shoppingCart == null
				|| shoppingCart.getShoppingCartItems().size() == 0) {
			return false;
		}
		// System.out.println(shoppingCart.getShoppingCartItems().size());
		return true;

	}
	public static ShoppingCart getShoppingCart(boolean returnCart,HttpServletRequest request) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession()
		.getAttribute("shoppingCart");
		if (shoppingCart == null
				|| shoppingCart.getShoppingCartItems().size() == 0) {
		return null;
		}
		return shoppingCart;
	}
	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie的名称
	 * @param value
	 *            cookie的值
	 * @param maxAge
	 *            cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param request
	 * @param name
	 *            cookie的名称
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	protected static Map<String, Cookie> readCookieMap(
			HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
		return cookieMap;
	}

	public static String generateURL(String in) {
		// 首先将所有非空白、非字母、非下划线、非连接线的字符串替换成空格
		Pattern p = Pattern.compile("[^\\sa-zA-Z-_\\d]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(in);
		in = m.replaceAll(" ");
		// 将所有或多个空格替换成下划线
		p = Pattern.compile("(\\s)+", Pattern.CASE_INSENSITIVE);
		m = p.matcher(in);
		in = m.replaceAll("_");
		return in;
	}

	/**
	 * 去除html代码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String HtmltoText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}
	public static PaymentAndDeliverMethod getPaymentMethod(HttpServletRequest request) {
		PaymentAndDeliverMethod paymentMethod = (PaymentAndDeliverMethod)request.getSession().getAttribute("paymentMethod");
		
		return paymentMethod;
	}

}

package com.hnfealean.test;

import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.ipaddressutil.IPSeeker;

public class TestIpaddress {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(WebUtil.getAddressByIp("127.0.0.1"));
		System.out.println(IPSeeker.getInstance().getArea("122.11.111.112"));
		System.out.println(IPSeeker.getInstance().getAddress("121.227.230.115"));
		System.out.println(IPSeeker.getInstance().getAddress("127.0.0.1"));

	}

}

package com.hnfealean.test;

import org.apache.velocity.VelocityContext;

import com.hnfealean.sport.web.WebProperty;

public class VelocityStaticClassTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VelocityContext context = new VelocityContext();
		context.put("m", new WebProperty());
		try {
			Object m = (Object)context.get("m");
			System.out.println(m.toString());
			((WebProperty)m).readUrl("log4j.appender.stdout");
			
			//System.out.println(m.readUrl("log4j.appender.stdout"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package com.hnfealean.sport.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.commons.beanutils.Converter;
public class UtilDateConverter implements Converter {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void init(FilterConfig config) throws ServletException{

	}
	
	public Object convert(Class type, Object value) {
		if (value == null) {
			return value;
		}
		if (value instanceof Date) {
			return value;
		}
		if (value instanceof String) {
			try {
				Date d = format.parse((String)value);
				return d;
			} catch (ParseException e) {
				//e.printStackTrace();
			}
		}
		return null;
	}

}


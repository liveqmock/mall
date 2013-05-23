package com.hnfealean.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryFilterStrTest {
	public static void main(String[] args){
		
			
		String pstr = "^(?:\\d+_\\d+-)*\\d+_\\d+$";
		Pattern pattern =  Pattern.compile(pstr);
		String input = "4_19-6_25";
		Matcher matcher = pattern.matcher(input);
		System.out.println(matcher.find());
		System.out.println(pattern.matcher("4_19").find());
		System.out.println(pattern.matcher("4_19-6_25-1_250").find());
		System.out.println(pattern.matcher("4_19-6_25-1").find());
//		while(matcher.find()){
//			for(int i = 0;i<matcher.groupCount();i++){
//		         System.out.println("group "+i+" : "+matcher.group(i));
//		         
//			}
//		}
	}
}

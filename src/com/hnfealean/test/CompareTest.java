package com.hnfealean.test;

import java.util.Comparator;

public class CompareTest  implements Comparator<String> {

	public int compare(String o1, String o2) {
	     String str1 = o1.toString();  
	     String str2 = o2.toString();  
	     if (str1.length() < str2.length()) {  
	         return 0;  
	     } else if (str1.length() > str2.length()) {  
	         return 1;  
	     } else {  
	         return str1.compareTo(str2);  
	     }	
//		String[] aArray = o1.split("_");
//		String[] bArray = o2.split("_");
//		if(aArray==bArray)	return 0;
//		if(aArray==null||aArray.length==0)	return 0;
//		if(bArray==null||bArray.length==0) 	return 0;
//		if(Integer.valueOf(aArray[0])<Integer.valueOf(bArray[0]))	return 0;
//		if(Integer.valueOf(aArray[0])==Integer.valueOf(bArray[0])){
//			if(Integer.valueOf(aArray[1])<Integer.valueOf(bArray[1]))	return 0;
//		}
//			
//	    return 1;
	}  
  
}  
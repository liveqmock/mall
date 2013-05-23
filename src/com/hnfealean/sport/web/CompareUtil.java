package com.hnfealean.sport.web;

import java.util.Comparator;

public class CompareUtil  implements Comparator<String> {

	public int compare(String str1, String str2) {
	    
	     if (str1.length() < str2.length()) {  
	         return 0;  
	     } else if (str1.length() > str2.length()) {  
	         return 1;  
	     } else {  
	         return str1.compareTo(str2);  
	     }	
}
}

package com.hnfealean.test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hnfealean.sport.web.ipaddressutil.IPEntry;

//import com.hnfealean.sport.web.ipaddressutil.IPSeeker;


public class Test{

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		
		System.out.println(IPEntry.class.getClassLoader().getResource("QQWry.dat").toString());
		try {
			RandomAccessFile ipFile = new RandomAccessFile(IPEntry.class.getClassLoader().getResource("QQWry.dat").getFile(),"r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		// TODO Auto-generated method stub
		//IPSeeker ipseek = new IPSeeker();
		//System.out.println(IPSeeker.getInstance().getAddress("121.227.230.115"));
		String input="1021_120-101_1-101_10-101_2-101_15-101_3-121_120";
		String[] inputArray = input.split("-");
		 Arrays.sort(inputArray, new CompareTest()); 
		 StringBuffer inputStr = new StringBuffer();
		 if(inputArray.length>0){
		 for (int i = 0; i < inputArray.length-1; i++) {  
	        // System.out.println(inputArray[i]); 
	         inputStr.append(inputArray[i]).append("-");
	     }  
		 inputStr.append(inputArray[inputArray.length-1]);
		 }
		 System.out.println(inputStr); */
		String m =new Date().toString();
		 
		 System.out.println(m);
		String today =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date today0 =new SimpleDateFormat("yyyy-MM-dd").parse(today);
		long yesMax = today0.getTime()-1;
		Date yes = new Date(yesMax);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(yes));
		System.out.println(today);
		System.out.println(new Date().getTime());
		System.out.println(new Date().getSeconds());
		Calendar cal = Calendar.getInstance();
		cal.setTime(today0);
		cal.add(Calendar.DATE, 1);
		Date tommorow = cal.getTime();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(tommorow));
		//昨天、今天、明天
		/**
		 * 今天	
		 * 		new Date():现在的时间点	
		 * 		String todayStr :	new SimpleDateFormat("yyyy-MM-dd").format(new Date());	//String:今天的日期，如2011-03-03
		 * 		Date today : new SimpleDateFormat("yyyy-MM-dd").parse(todayStr);
		 * 昨天
		 * 		今天的日期向前一天
		 * 		使用数值类型：今天的数值	today.getTime()
		 * 		昨天的最大值： today.getTime()-1
		 * 		
		 */
	}

}

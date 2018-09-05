package com.yzgs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author lenovo
 * 上午1:20:13
 */
public class DateUtil {
	
	/**
	 * 比较2个日期的大小, date1>date2 返回true ,否则 返回false
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static boolean checkTwoDate(Date date1,Date date2){
    	  return date1.getTime()>date2.getTime()?true:false;
    	
    	
    }
    
    
    /**
     * 
     */
    
    public static int getDayByTwoDate(Date date1,Date date2){
    	
    	return (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
    }
}

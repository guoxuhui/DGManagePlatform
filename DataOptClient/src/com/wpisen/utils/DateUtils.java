package com.wpisen.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期时间处理工具类
 * @author guoxuhui0822
 *
 */
public class DateUtils {

	private static DateUtils utl = null;  
	private Calendar time = Calendar.getInstance(); 
	private DateUtils(){}

	public static DateUtils getInstance(){
		if(utl == null){
			utl = new DateUtils();
		}		
		return utl;
	}
	
	/**
	 * 返回当前日期（天）
	 * @return
	 */
	public int getCurDay(){
		return time.get(Calendar.DATE);
	}
	
	/**
	 * 返回当前日期（月）
	 * @return
	 */
	public int getCurMonth(){
		return time.get(Calendar.MONTH);
	}
	
	/**
	 * 返回当前日期（年）
	 * @return
	 */
	public int getCurYear(){
		return time.get(Calendar.YEAR);
	}
	
	/**
	 * 返回当前日期（年月）
	 * @return
	 */
	public String getCurYearMonth(){
		String result = "";
		result = DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyyMM"); 
		return result;
	}
	
	/**
	 * 返回当前日期（年月日）
	 * @return
	 */
	public String getCurYearMonthDay(){
		String result = "";
		result = DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyyMMdd"); 
		return result;
	}
	
	/**
	 * 返回当前日期（时分秒）
	 * @return
	 */
	public String getCurHMS(){
		String result = "";
		result = DateFormatUtils.format(Calendar.getInstance().getTime(),"HHmmss"); 
		return result;
	}
	
	/**
	 * 返回当前日期（年月日时分秒）
	 * @return
	 */
	public String getCurYearMonthDayHMS(){
		String result = "";
		result = DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyyMMddHHmmss"); 
		return result;
	}
	
	/**
	 * 返回当前日期（年月日时分秒）
	 * @return
	 */
	public String getCurYearMonthDayHMS(String timezoneId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  //格式日期
		TimeZone timezone = TimeZone.getTimeZone(timezoneId);             //时区
		sdf.setTimeZone(timezone );
		String result = "";
		result = sdf.format(new Date());
		return result;
	}
	
	/**
	 * 返回当前日期（年月日时分秒毫秒）
	 * @return
	 */
	public String getCurYearMonthDayHMSS(){
		String result = "";
		result = DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyyMMddHHmmssSSS"); 
		return result;
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.getInstance().getCurYearMonthDayHMS("GMT"));
	}
}

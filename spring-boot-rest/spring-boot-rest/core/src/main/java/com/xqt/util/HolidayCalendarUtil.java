package com.xqt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 节假日计算工具
 * @author dongxuejiao
 *
 */
public class HolidayCalendarUtil {
	
	private static List<Calendar> holidayList = new ArrayList<Calendar>();  //节假日列表
	
	 /**  
	  *   
	  * <p>Title: addDateByWorkDay </P>  
	  * <p>Description: TODO  计算相加day天，并且排除节假日和周末后的日期</P>  
	  * @param calendar  当前的日期  
	  * @param day  相加天数  
	  * @return     
	  * return Calendar    返回类型   返回相加day天，并且排除节假日和周末后的日期  
	  * throws   
	  * date 2014-11-24 上午10:32:55  
	  */  
	 public Calendar addDateByWorkDay(Calendar calendar,int day){  
	       
	     try {  
	        for (int i = 0; i < day; i++) {  
	              
	             calendar.add(Calendar.DAY_OF_MONTH, -1);  
	               
	             if(checkHoliday(calendar)){  
	                 i--;  
	             }  
	        }  
	        return calendar;  
	          
	       
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return calendar;  
	 }  
	   
	 /**  
	  *   
	  * <p>Title: checkHoliday </P>  
	  * <p>Description: TODO 验证日期是否是节假日</P>  
	  * @param calendar  传入需要验证的日期  
	  * @return   
	  * return boolean    返回类型  返回true是节假日，返回false不是节假日  
	  * throws   
	  * date 2014-11-24 上午10:13:07  
	  */  
	 public boolean checkHoliday(Calendar calendar) throws Exception{  
	       
	     //判断日期是否是节假日  
	     for (Calendar ca : holidayList) {  
	        if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&  
	                ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&  
	                ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){  
	            return true;  
	        }  
	    }
	   //判断日期是否是周六周日  
    if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||   
            calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){  
        return true;  
    } 
	        
	     return false;  
	 }  
	   
	 /**  
	  *   
	  * <p>Title: initHolidayList </P>  
	  * <p>Description: TODO  把所有节假日放入list</P>  
	  * @param date  从数据库查 查出来的格式2014-05-09  
	  * return void    返回类型   
	  * throws   
	  * date 2014-11-24 上午10:11:35  
	  */  
	public void initHolidayList( String date){  
	      
	        String [] da = date.split("-");  
	          
	        Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));  
	        calendar.set(Calendar.MONTH, Integer.valueOf(da[1])-1);//月份比正常小1,0代表一月  
	        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));  
	        holidayList.add(calendar);  
	}  
	
	
	/**
     * 手动维护的节假日
     * @since 1.0 
     * @return
     * @throws ParseException
     */
    public void initHoliday(HolidayCalendarUtil ct) throws ParseException{   
    	//2017年
        //元旦
        ct.initHolidayList("2017-01-01");
        ct.initHolidayList("2017-01-02");
        //春节
        ct.initHolidayList("2017-01-27");
        ct.initHolidayList("2017-01-28");
        ct.initHolidayList("2017-01-29");
        ct.initHolidayList("2017-01-30");
        ct.initHolidayList("2017-01-31");
        ct.initHolidayList("2017-02-01");
        ct.initHolidayList("2017-02-02");
        //清明节
        ct.initHolidayList("2017-04-02");
        ct.initHolidayList("2017-04-03");
        ct.initHolidayList("2017-04-04");
        //劳动节
        ct.initHolidayList("2017-04-29");
        ct.initHolidayList("2017-04-30");
        ct.initHolidayList("2017-05-01");
        //端午节
        ct.initHolidayList("2017-05-28");
        ct.initHolidayList("2017-05-29");
        ct.initHolidayList("2017-05-30");
        //中秋节
        //国庆节
        ct.initHolidayList("2017-10-01");
        ct.initHolidayList("2017-10-02");
        ct.initHolidayList("2017-10-03");
        ct.initHolidayList("2017-10-04");
        ct.initHolidayList("2017-10-05");
        ct.initHolidayList("2017-10-06");
        ct.initHolidayList("2017-10-07");
        ct.initHolidayList("2017-10-08");
        
    	//2018年
        //元旦
        ct.initHolidayList("2018-01-01");
        //春节
        ct.initHolidayList("2018-02-15");
        ct.initHolidayList("2018-02-16");
        ct.initHolidayList("2018-02-17");
        ct.initHolidayList("2018-02-18");
        ct.initHolidayList("2018-02-19");
        ct.initHolidayList("2018-02-20");
        ct.initHolidayList("2018-02-21");
        //清明节
        ct.initHolidayList("2018-04-05");
        ct.initHolidayList("2018-04-06");
        ct.initHolidayList("2018-04-07");
        //劳动节
        ct.initHolidayList("2018-04-29");
        ct.initHolidayList("2018-04-30");
        ct.initHolidayList("2018-05-01");
        //端午节
        ct.initHolidayList("2018-05-17");
        ct.initHolidayList("2018-05-18");
        ct.initHolidayList("2018-05-19");
        //中秋节
        ct.initHolidayList("2018-09-24");
        //国庆节
        ct.initHolidayList("2018-10-01");
        ct.initHolidayList("2018-10-02");
        ct.initHolidayList("2018-10-03");
        ct.initHolidayList("2018-10-04");
        ct.initHolidayList("2018-10-05");
        ct.initHolidayList("2018-10-06");
        ct.initHolidayList("2018-10-07");
        
        
        //2019年
        //元旦
        ct.initHolidayList("2018-12-31");
        ct.initHolidayList("2019-01-01");
        //春节
        ct.initHolidayList("2019-02-03");
        ct.initHolidayList("2019-02-04");
        ct.initHolidayList("2019-02-05");
        ct.initHolidayList("2019-02-06");
        ct.initHolidayList("2019-02-07");
        ct.initHolidayList("2019-02-08");
        ct.initHolidayList("2019-02-09");
        //清明节
        ct.initHolidayList("2019-04-05");
        ct.initHolidayList("2019-04-06");
        ct.initHolidayList("2019-04-07");
        //劳动节
//        ct.initHolidayList("2019-04-29");
//        ct.initHolidayList("2019-04-30");
        ct.initHolidayList("2019-05-01");
        //端午节
        ct.initHolidayList("2019-06-07");
        ct.initHolidayList("2019-06-08");
        ct.initHolidayList("2019-06-09");
        //中秋节
        ct.initHolidayList("2019-09-13");
        //国庆节
        ct.initHolidayList("2019-10-01");
        ct.initHolidayList("2019-10-02");
        ct.initHolidayList("2019-10-03");
        ct.initHolidayList("2019-10-04");
        ct.initHolidayList("2019-10-05");
        ct.initHolidayList("2019-10-06");
        ct.initHolidayList("2019-10-07");
        
    }


    public static void main(String[] args) {
        /*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());//设置当前时间
        HolidayCalendarUtil ct = new HolidayCalendarUtil();
        Calendar c = ct.addDateByWorkDay(ca,3);
        System.out.println(df.format(c.getTime()));
        String time=df.format(c.getTime());*/

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());//设置当前时间
        HolidayCalendarUtil ct = new HolidayCalendarUtil();
        Calendar c = ct.addDateByWorkDay(ca,3);
        System.out.println(df.format(c.getTime())+" 15:00:00");
        String time=df.format(c.getTime())+" 15:00:00";


    }

}

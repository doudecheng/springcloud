package com.xqt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
/**
 * 
 * @ClassName:     ObjAnalysis.java
 * @Description:   本类说明：对象转Map
 * @author         Dongxuejiao
 * @Date           2017年6月10日 下午3:16:32
 */
public class ObjAnalysis {

	
	 public static Map<String, Object> ConvertObjToMap(Object obj){
		  Map<String,Object> reMap = new HashMap<String,Object>();
		  if (obj == null) {
			  return null;
		  }
		  Field[] fields = obj.getClass().getDeclaredFields();
		  try {
		   for(int i=0;i<fields.length;i++){
		    try {
		     Field f = obj.getClass().getDeclaredField(fields[i].getName());
		     f.setAccessible(true);
		           Object o = f.get(obj);
		           String val="null";
		           if(o!=null){
		        	   //reMap.put(fields[i].getName(), o);
		        	   if (o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Long || o instanceof Boolean ) {
		        		   val=String.valueOf(o);
	        		   } else if (o instanceof Date) {
	        			   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        			   val= sdf.format(o);
	        		   } else if(o instanceof BigDecimal){
	        			   val=o.toString();
	        		   } else if(o instanceof String){
	        			   val=o.toString();
	        		   }
		           }
		           reMap.put(fields[i].getName(), val);
		    } catch (NoSuchFieldException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IllegalArgumentException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IllegalAccessException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		   }
		  } catch (SecurityException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } 
		  return reMap;
		 }
}

package com.xqt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ErrorPasswordProcessing {
	
	public static Map<String,String> loginErrorCount(String errorCount,String errorSessionTime,boolean isTrue) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object obj=JedisUtil.get(errorCount);

		//密码输入错误的处理
		if(obj==null){
			if(isTrue){
				return getmap(0,true);
			}
			JedisUtil.setex(errorCount,1800,1);  //
			return getmap(1,true);
		}else{
			int count = (int) obj;
			if(JedisUtil.get(errorSessionTime)==null){
				return handleMap(count,errorCount,errorSessionTime,isTrue );
			}
			String time=(String) JedisUtil.get(errorSessionTime);
			Date limitTime=sdf.parse(time);
			long l=System.currentTimeMillis()-limitTime.getTime();
			long m=30*60*1000L;
			//long m=60*1000L;
			if(l>m){//超过30分钟
				JedisUtil.del(errorCount);
				JedisUtil.del(errorSessionTime);
				if(isTrue){
					return getmap(0,true);
				}
				JedisUtil.setex(errorCount,1800,1);
				return getmap(0,true);
			}else{//小于30分钟
				return handleMap(count,errorCount,errorSessionTime,isTrue );
			}
			
		}
		
	}
	
	public static Map<String,String> getmap(int count,boolean isTrue){
		Map<String,String> map=new HashMap<>();
		map.put("errorsCount", String.valueOf(count));
		map.put("isCanLogin", String.valueOf(isTrue));
		return map;
	}
	
	public static Map<String,String> handleMap(int count,String errorCount,String errorSessionTime,boolean isTrue ){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(count>=5){
			JedisUtil.set(errorCount, count+1);
			return getmap(count+1,false);
		}else{
			if(isTrue){//密码输入正确
				JedisUtil.del(errorCount);
				JedisUtil.del(errorSessionTime);
				return getmap(0,true);
			}
			if(count==4){
				JedisUtil.set(errorCount, count+1);
				JedisUtil.set(errorSessionTime, sdf.format(new Date()));
				return getmap(count+1,false);
			}else{
				JedisUtil.set(errorCount, count+1);
				return getmap(count+1,true);
			}
		}
	}

}

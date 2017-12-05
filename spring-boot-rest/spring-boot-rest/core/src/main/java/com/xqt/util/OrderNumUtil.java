package com.xqt.util;

import java.util.Random;

/**
 * 生成订单号
 * @author Andy
 *
 */
public class OrderNumUtil {
	
	public static String createOrderNum(){
		StringBuilder timeStr = new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000));
		for (int i = 0; i < 4; i++) {
            char ch = timeStr.charAt(new Random().nextInt(10));
            timeStr.append(ch);
        }
		return String.valueOf(timeStr);
	}
	
	
	public static String createDealNum(){
		StringBuilder timeStr = new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000));
		for (int i = 0; i < 6; i++) {
            char ch = timeStr.charAt(new Random().nextInt(10));
            timeStr.append(ch);
        }
		return String.valueOf(timeStr);
	}

}

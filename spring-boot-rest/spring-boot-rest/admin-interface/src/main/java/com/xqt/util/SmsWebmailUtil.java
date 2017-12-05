package com.xqt.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.newFixedThreadPool;


/**
 * 异步发送短信
 * @author Andy
 *
 */
public class SmsWebmailUtil {

	public static void sendMails(String mobilePhone, String smsContent,String smsType){
		ExecutorService exec = newFixedThreadPool(10);   //创建线程池
		final Semaphore semp = new Semaphore(10);
		Runnable run = new Runnable() {
			@Override
			public void run() {
					try {
						semp.acquire();
						//SmsBaseService.sendSms("", smsType, mobilePhone, smsContent);
						semp.release(); 
					} catch (InterruptedException e) {
					}
			} 
		};
		exec.execute(run);
	    exec.shutdown(); 
	}
	
	
}

package com.xqt.util;


import com.xqt.base.entity.Operator;

/**
 * @author 123
 */
public final class SessionLocalUtils {

	private static final ThreadLocal<Operator> threadLocal = new ThreadLocal<>();

	public static void setOperator(Operator operator) {

		threadLocal.set(operator);

	}

	public static Operator getOperator() {

		if (threadLocal != null){
			return threadLocal.get();
		}else{
			throw new RuntimeException("SESSION 为空");
		}
	}

}
package com.xqt.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Auth {

	/**
	 * 是否验证登录 true=验证 ,false = 不验证
	 * 
	 * @return
	 */
	boolean verifyLogin() default true;

	/**
	 * 验证数据访问权限
	 * 
	 * @return
	 */
	String permissions() default "";
}

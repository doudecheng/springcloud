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
public @interface Duplicate {

	/**
	 * 是否需要生成Token
	 * 
	 * @return
	 */
	public boolean add() default false;

	/**
	 * 是否需要验证Token
	 * 
	 * @return
	 */
	public boolean validate() default false;
}

package com.jdog.frameworks.form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {

	
	/**
	 * 发生重复提交后的重定向地址
	 */
	String redirectUrl() default "";
	
	/**
	 * 发生重复提交后的提示
	 * @return
	 */
	String msg() default "";
	
}

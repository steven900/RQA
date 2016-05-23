package com.jdog.frameworks.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动生成代码时指定 package位置
 * 必须在basePackage下，basePackage配置在config.properties中
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Location {

	/**
	 * 基础包名,如"user",则最后组装位置为 basePackage.user
	 * @return
	 */
	String module();
}

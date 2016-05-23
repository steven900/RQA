package com.jdog.frameworks.annotation.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记查询字段,将用于where语句中组合条件
 * @author Jdog.Asher
 * @Date 2014-3-11
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {

}

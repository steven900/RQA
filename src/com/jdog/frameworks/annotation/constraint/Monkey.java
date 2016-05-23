package com.jdog.frameworks.annotation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义字段约束， 随便怎么定义规则
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Monkey {

}

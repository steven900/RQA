package com.jdog.frameworks.annotation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段约束
 * 最大值
 * 针对所有数字类型
 * 遇到非数字类型默认通过
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxValue {

	double value();
}

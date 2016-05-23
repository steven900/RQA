package com.jdog.frameworks.annotation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段约束
 * 不能为空
 * 遇到原始类型默认通过
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}

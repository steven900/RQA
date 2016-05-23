package com.jdog.frameworks.annotation.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记修改字段，用于组合修改更新操作的语句
 * @author Jdog.Asher
 * @Date 2014-3-11
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateField {

}

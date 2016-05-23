package com.jdog.frameworks.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记某字段作为比较作用符
 * 只能注解到实体的具体字段上
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Compare {
	
	/**
	 * 作为字段比较的优先级，按照优先级比较，默认为1
	 * 如果有多个字段，按照优先级 从小到大 进行多级比较
	 * 如果优先级相等，那么按照字段的自然代码顺序进行比较
	 * @return
	 */
	int priority() default 1;
}

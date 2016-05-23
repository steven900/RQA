package com.jdog.frameworks.annotation.cache;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.Assert;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Aspect
public class EcachedInterceptor {

	private static final Log logger = LogFactory
			.getLog(EcachedInterceptor.class);

	@Resource
	private Cache ehCache;


	
	public Cache getEhCache() {
		return ehCache;
	}

	public void setEhCache(Cache ehCache) {
		this.ehCache = ehCache;
	}

	@Pointcut("@annotation(com.jdog.frameworks.annotation.cache.Ecache)")
	private void anyMethod() { // 声明一个切入点
	}

	// 环绕通知在我们做权限时，可以大量使用到
	@Around("anyMethod()")
	public <T> T doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		String targetName = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		Object[] arguments =  pjp.getArgs();
		Object result;
		//logger.debug("Find object from cache is " + cache.getName());

		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = ehCache.get(cacheKey);

		if (element == null) {
			logger.debug("Hold up method , Get method result and create cache........!");
			result = pjp.proceed();
			element = new Element(cacheKey, (Serializable) result);
			ehCache.put(element);
		}
		return (T) element.getValue();
	}

	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * implement InitializingBean，检查cache是否为空
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(ehCache,
				"Need a cache. Please use setCache(Cache) create it.");
	}

}

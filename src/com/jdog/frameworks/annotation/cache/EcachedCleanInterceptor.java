package com.jdog.frameworks.annotation.cache;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.Assert;

import net.sf.ehcache.Cache;

@Aspect
public class EcachedCleanInterceptor {
	private static final Log logger = LogFactory
			.getLog(EcachedCleanInterceptor.class);

	@Resource
	private Cache ehCache;

	

	public Cache getEhCache() {
		return ehCache;
	}

	public void setEhCache(Cache ehCache) {
		this.ehCache = ehCache;
	}

	public EcachedCleanInterceptor() {
		super();
	}

	@Pointcut("@annotation(com.jdog.frameworks.annotation.cache.EcacheClean)")
	private void anyMethod() { // 声明一个切入点

	}

	
   @AfterReturning("anyMethod()")
   public void doBasicProfiling(JoinPoint pjp) throws Throwable {
        System.out.println("后置通知");
        String className = pjp.getTarget().getClass().getName(); 
        List list = ehCache.getKeys(); 
        for(int i = 0;i<list.size();i++){ 
           String cacheKey = String.valueOf(list.get(i)); 
           if(cacheKey.startsWith(className)){ 
        	   ehCache.remove(cacheKey); 
              logger.debug("remove cache " + cacheKey); 
              System.out.println("remove cache " + cacheKey);
           } 
        } 
        
    }
	
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(ehCache,
				"Need a cache. Please use setCache(Cache) create it.");
	}

}

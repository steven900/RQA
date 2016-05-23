package com.jdog.frameworks.service;

import java.util.List;
import java.util.Map;

import com.jdog.frameworks.entity.BaseEntity;
import com.jdog.frameworks.entity.EntityCheckError;

public interface BaseService {

	/**
	 * 检查实体，结果保存在List<EntityCheckError>中
	 * @param entity
	 * @param errorList
	 * @return
	 */
	public <T extends BaseEntity> boolean checkEntity(T entity, List<EntityCheckError> errorList);
	
	/**
	 * 检查实体，结果保存在Map<String, String>中,map中key为字段名，value为错误
	 * @param entity
	 * @param errorList
	 * @return
	 */
	public <T extends BaseEntity> boolean checkEntity(T entity, Map<String, String> errorMap);
	
}

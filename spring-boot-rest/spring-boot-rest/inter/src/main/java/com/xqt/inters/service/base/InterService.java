package com.xqt.inters.service.base;

import com.xqt.base.page.PageInfo;
import com.xqt.param.base.BaseParam;

import java.util.List;

/**
 * @author andy
 * @param <T>
 */
public interface InterService<T> {

	
	<E> int insertEntity(E entity);
	
	<E> int updateEntity(E entity);
	
	<E,F> E selectEntity(F entity);
	
	<E,F> E selectEntityById(F entity);
	
	<E> Integer selectEntityCount(E entity);
	
	<E,F> List<E> selectEntityList(F entity);
	
	PageInfo<T> selectPageInfo(BaseParam entity);
	
	<E> int deleteEntity(E entity);
	
}

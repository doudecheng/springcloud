package com.xqt.service;

import java.util.List;

import com.xqt.base.dao.BaseDao;
import com.xqt.base.page.PageInfo;
import com.xqt.inters.service.base.InterService;
import com.xqt.param.base.BaseParam;

/**
 * 
 * @author Andy
 *
 * @param <T>
 */
public abstract class BaseService<T> implements InterService<T> {

	public abstract BaseDao<T> getDao();

	@Override
	public <E> int insertEntity(E entity) {
		return getDao().insertEntity(entity);
	}

	@Override
	public <E> int updateEntity(E entity) {
		return getDao().updateEntity(entity);
	}

	@Override
	public <E, F> E selectEntity(F entity) {
		return getDao().selectEntity(entity);
	}

	@Override
	public <E, F> E selectEntityById(F entity) {
		return getDao().selectEntityById(entity);
	}

	@Override
	public <E> Integer selectEntityCount(E entity) {
		return getDao().selectEntityCount(entity);
	}

	@Override
	public <E, F> List<E> selectEntityList(F entity) {
		return getDao().selectEntityList(entity);
	}

	@Override
	public PageInfo<T> selectPageInfo(BaseParam entity) {
		return getDao().selectPageInfo(entity);
	}

	@Override
	public <E> int deleteEntity(E entity) {
		return getDao().deleteEntity(entity);
	}
	
	
}

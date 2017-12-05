package com.xqt.base.dao;

import com.xqt.base.page.PageInfo;
import com.xqt.param.base.BaseParam;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


/**
 * 
 * @author Andy
 *
 * @param <T>
 */
public abstract class BaseDao<T> {

	@Autowired
	@Qualifier("readSqlSession")
	protected SqlSessionTemplate readSqlSession;

	@Autowired
	@Qualifier("writeSqlSession")
	protected SqlSessionTemplate writeSqlSession;
	
	public abstract String getReadSql();

	public abstract String getWriteSql();
	
	
	/**
	 * @Description insert单个实体
	 * @param entity
	 * @return int
	 * @author andy
	 */
	public <E> int insertEntity(E entity){
		return writeSqlSession.insert(getWriteSql()+"insertEntity",entity);
	}
	
	/**
	 * 自定义insert方法
	 * @param sqlMethod
	 * @param entity
	 * @return
	 */
	public <E> int insertEntityCustom(String sqlMethod,E entity){
		return writeSqlSession.insert(getWriteSql()+sqlMethod.trim(),entity);
	}
	
	/**
	 * @Description 更新实体 
	 * @param entity
	 * @return int
	 */
	public <E> int updateEntity(E entity){
		return writeSqlSession.insert(getWriteSql()+"updateEntity",entity);
	}

	/**
	 * 自定义update
	 * @param sqlMethod
	 * @param entity
	 * @param <E>
	 * @return
	 */
	public <E> int updateEntityCustom(String sqlMethod,E entity){
		return writeSqlSession.insert(getWriteSql()+sqlMethod.trim(),entity);
	}
	
	/**
	 * @Description 获取单个实体
	 * @param entity
	 * @return T
	 */
	public <E,F> E selectEntity(F entity){
		return readSqlSession.selectOne(getReadSql()+"selectEntity", entity);
	}
	
	public <E,F> E selectEntityCustom(String sqlMethod,F entity){
		return readSqlSession.selectOne(getReadSql()+sqlMethod.trim(), entity);
	}

	/**
	 * 根据id获取entity
	 * @param entity
	 * @param <E>
	 * @param <F>
	 * @return
	 */
	public <E,F> E selectEntityById(F entity){
		return readSqlSession.selectOne(getReadSql()+"selectEntityById", entity);
	}
	
	/**
	 * 获取entity数量
	 * @param entity
	 * @return
	 */
	public <E> Integer selectEntityCount(E entity){
		return readSqlSession.selectOne(getReadSql()+"selectEntityCount", entity);
	}

	/**
	 * 自定义返回数量的sql
	 * @param sqlMethod
	 * @param entity
	 * @param <E>
	 * @return
	 */
	public <E> Integer selectEntityCountCustom(String sqlMethod,E entity){
		return readSqlSession.selectOne(getReadSql()+sqlMethod.trim(), entity);
	}
	/**
	 * 获取list
	 * @param entity
	 * @return
	 */
	public <E,F> List<E> selectEntityList(F entity){
		return readSqlSession.selectList(getReadSql()+"selectEntityList", entity);
	}

	/**
	 * 自定义list
	 * @param entity
	 * @param sqlMethod
	 * @param <E>
	 * @param <F>
	 * @return
	 */
	public <E,F> List<E> selectEntityListCustom(F entity,String sqlMethod){
    	return readSqlSession.selectList(getReadSql()+sqlMethod.trim(), entity);
    }

	/**
	 * 预定义获取分页list
	 * @param entity
	 * @return
	 */
	public List<T> selectEntityPageList(BaseParam entity){
		return readSqlSession.selectList(getReadSql()+"selectPageList",entity);
	}

	/**
	 * 自定义获取分页list
	 * @param sqlMethod
	 * @param entity
	 * @param <E>
	 * @return
	 */
	public <E> List<E> selectEntityPageListCustom(String sqlMethod,BaseParam entity){
		return readSqlSession.selectList(getReadSql()+sqlMethod,entity);
	}

	/**
	 * 获取pageInfo
	 * @param entity
	 * @return
	 */
	public PageInfo<T> selectPageInfo(BaseParam entity){
		int rowCount = selectEntityCount(entity);
		List<T> list = selectEntityPageList(entity);
		PageInfo<T> pageInfo = new PageInfo<>(list, entity.getPageSize(), rowCount, entity.getPageId());
		return pageInfo;
	}
	
	public <E> PageInfo<E> selectPageInfoCustom(String countMethod,String sqlMethod, BaseParam entity){
		int rowCount = selectEntityCountCustom(countMethod,entity);
		List<E> list = selectEntityPageListCustom(sqlMethod,entity);
		PageInfo<E> pageInfo = new PageInfo<E>(list, entity.getPageSize(), rowCount, entity.getPageId());
		return pageInfo;
	}
	/**
	 * 根据方法名称获取分页列表
	 * @param entity
	 * @param countMethod  获取数据总记录
	 * @param sqlMethod    获取数据list
	 * @param pageIndex    当前页
	 * @param pageSize	   每页显示条数
	 * @return
	 */
	public <E,F> PageInfo<E> selectPageListCustom(F entity,String countMethod, String sqlMethod, int pageIndex, int pageSize){
		int recordCount = readSqlSession.selectOne(getReadSql()+countMethod.trim(),entity);
        int maxPage = recordCount / pageSize+(recordCount%pageSize==0?0:1);
        if (pageIndex > maxPage) {
            pageIndex = maxPage;
        }
        int startIndex = 0;
        if (pageIndex != 0) {
            startIndex = (pageIndex - 1) * pageSize;
        }
        List<E> list = readSqlSession.selectList(getReadSql()+sqlMethod.trim(), entity,new RowBounds(startIndex, pageSize));
        PageInfo<E> pageInfo = new PageInfo<E>(list, pageSize, recordCount, pageIndex);
        return pageInfo;
	}
	
	/**
	 * 根据id删除单个或多个实体
	 * @param entity
	 * @return
	 */
	public <E> int deleteEntity(E entity) {
		return this.writeSqlSession.delete(getWriteSql()+"deleteEntity",entity);
	}
	
}

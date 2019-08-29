package com.xiang.api.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * MyBatis Dao 基类接口，动态代理Dao接口对某个对象（数据库表）的操作可直接集成该接口。
 * 该基类接口提供了一些基础的功能，比如CURD操作，分页查询。 子接口无需重写这些方法，直接在MyBatis的mapper.xml实现即可。
 */
public interface IMyBatisGenericDao<T, PK> {

	int insert(T object) throws DataAccessException;
	
	void insertBySql(@Param("sql") String sql);
	
	int batchInsert(List<T> objList) throws DataAccessException;

	void delete(@Param("id") PK id) throws DataAccessException;
	
	void deleteByValue(@Param("col") String col, @Param("val") Object val);
	
	void deleteByMultiValue(@Param("cols") String[] cols, @Param("vals") Object[] vals);
	
	int batchDelete(List<PK> idList) throws DataAccessException;

	void update(T object) throws DataAccessException;
	
	void updateValueByValue(@Param("col") String col, @Param("val") Object val, @Param("keyCol") String keyCol, @Param("keyVal") Object keyVal) throws DataAccessException;
	
	void updateValueByMultiValue(@Param("col") String col, @Param("val") Object val, @Param("keyCols") String[] keyCols, @Param("keyVals") Object[] keyVals) throws DataAccessException;
	
	void updateMultiValueByValue(@Param("cols") String[] cols, @Param("vals") Object[] vals, @Param("keyCol") String keyCol, @Param("keyVal") Object keyVal) throws DataAccessException;

	void updateMultiValueByMultiValue(@Param("cols") String[] cols, @Param("vals") Object[] vals, @Param("keyCols") String[] keyCols, @Param("keyVals") Object[] keyVals) throws DataAccessException;

	T get(@Param("id") PK id) throws DataAccessException;

	List<T> queryAll() throws DataAccessException;

	List<T> queryAll(@Param("page") Object page) throws DataAccessException;
	
	List<T> queryAll(Object condition, @Param("page") Object page) throws DataAccessException;
	
	List<T> queryAllByPage(@Param("sql") String condition, @Param("page") Object pageInfo) throws DataAccessException;
	
	List<T> queryByPage(@Param("page") Object page, @Param("filters") List<PropertyFilter> filters) throws DataAccessException;
	
	int getCount(@Param("sql") String sql) throws DataAccessException;
	
	int getCountByFilters(@Param("filters") List<PropertyFilter> filters) throws DataAccessException;
	
	int getCountByValue(@Param("col") String col, @Param("val") Object val) throws DataAccessException;
	
	int getCountByMultiValue(@Param("cols") String[] name, @Param("vals") Object[] value) throws DataAccessException;
	
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	T findUniqueByValue(@Param("col") String col, @Param("val") Object val) throws DataAccessException;
	
	T findUniqueByMultiValue(@Param("cols") String[] cols, @Param("vals") Object[] vals) throws DataAccessException;

	T findUniqueByMultiValueWithOrder(@Param("cols") String[] cols, @Param("vals") Object[] vals, @Param("orderby") String orderby, @Param("order") String order) throws DataAccessException;
	
	List<T> findBySql(@Param("sql") String sql) throws DataAccessException;

	List<T> findByValue(@Param("col") String col, @Param("val") Object val) throws DataAccessException;

	List<T> findByValueByOrder(@Param("col") String col, @Param("val") Object val, @Param("orderBy") String orderBy, @Param("order") String order) throws DataAccessException;
	
	List<T> findByMultiValue(@Param("cols") String[] cols, @Param("vals") Object[] vals) throws DataAccessException;
	
	List<T> findByMultiValueByOrder(@Param("cols") String[] cols, @Param("vals") Object[] vals, @Param("orderBy") String orderBy, @Param("order") String order) throws DataAccessException;
	
	List<T> findByMultiValueByMultiOrder(@Param("cols") String[] cols, @Param("vals") Object[] vals, @Param("orderBys") String[] orderBys, @Param("orders") String[] orders) throws DataAccessException;

	List<T> findByLikeValue(@Param("col") String name, @Param("val") String value) throws DataAccessException;

	List<T> findByMap(Map<?, ?> map) throws DataAccessException;

	boolean isNotUniqueByOr(T object, String tableName, String names) throws DataAccessException;

	boolean isNotUniqueByAnd(T object, String tableName, String names) throws DataAccessException;
	
	boolean isUnique(T object) throws DataAccessException;
	
	boolean isUniqueByValue(@Param("obj") T object, @Param("cols") String... cols) throws DataAccessException;
	
	boolean isUniqueByMultiValue(@Param("cols") String[] cols, @Param("vals") Object[] vals);
}
package com.gwm.monodb.dao;

import java.awt.Point;
import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;



import com.gwm.monodb.dao.entity.Mapinfo;
import com.sun.org.apache.bcel.internal.generic.NEW;


public interface BaseMongoDAO<T> {

	/**
	 * 根据条件检索多个实体
	 * @param query
	 * @return
	 */
    public List<T> find(Query query) ;  
    
    /**
     * 检索所有实体
     * @return
     */
    public List<T> findAll();
  
    /**
     * 根据条件检索单个实体
     * @param query
     * @return
     */
    public T findOne(Query query) ;  
  
    
    /**
     * 根据条件更新实体
     * @param query
     * @param update
     */
    public void update(Query query, Update update);
  
    /**
     * 保存实体
     * @param entity
     * @return
     */
    public T save(T entity) ;  
    /**
     * 保存实体并更新实体
     * @param entity
     * @return
     */
    public void saveOrUpdate(T entity) ;
  
    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    public T findById(String id) ;  
    
   
    
    /**
     * 根据条件删除实体
     * @param entity
     */
    public void remove(Query query);
  
    /**
     * 根据主键查询指定集合中的实体
     * @param id
     * @param collectionName
     * @return
     */
    public T findById(String id, String collectionName) ;  
      
    
     /**
      * 查询符合条件的实体总数
      * @param query
      * @return
      */
    public long count(Query query);

    /**
	 * 根据条件检索多个实体
	 * @param query
	 * @return
	 */
	public GeoResults<T> findBynear(NearQuery query);
    
   
   
}

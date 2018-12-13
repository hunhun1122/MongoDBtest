package com.gwm.monodb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.gwm.monodb.dao.BaseMongoDAO;
import com.gwm.monodb.util.ReflectionUtils;

public abstract class BaseMongoDAOImpl<T> implements BaseMongoDAO<T> {

	private static final int DEFAULT_SKIP = 0;  
    private static final int DEFAULT_LIMIT = 200;  
      
    
    @Autowired(required = true)
    protected MongoTemplate mongoTemplate; 
    @Autowired(required = true)
    protected GridFsTemplate gridFsTemplate;
    
  
    /**
	 * 根据条件检索多个实体
	 * @param query
	 * @return
	 */
    @Override  
    public List<T> find(Query query) { 
        return mongoTemplate.find(query, this.getEntityClass());  
    }
    /**
     * 检索所有实体
     * @return
     */
    @Override  
    public List<T> findAll() {  
        return mongoTemplate.findAll(this.getEntityClass());  
    }
  
    /**
     * 根据条件检索单个实体
     * @param query
     * @return
     */
    @Override  
    public T findOne(Query query) {  
        return mongoTemplate.findOne(query, this.getEntityClass());  
    }  
  
    /**
     * 根据条件更新实体
     * @param query
     * @param update
     */
    @Override  
    public void update(Query query, Update update) {  
        mongoTemplate.findAndModify(query, update, this.getEntityClass());  
    }  
  
    /**
     * 保存实体并更新实体
     * @param entity
     * @return
     */
    @Override
    public void saveOrUpdate(T entity)
    {
    	mongoTemplate.save(entity);
    }
    /**
     * 保存实体
     * @param entity
     * @return
     */
    @Override  
    public T save(T entity) {  
        mongoTemplate.insert(entity);  
        return entity;  
    }  
  
    
    /**
     * 根据条件删除实体
     * @param entity
     */
    @Override
    public void remove(Query query)
    {
    	mongoTemplate.remove(query,this.getEntityClass());
    }
    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    @Override  
    public T findById(String id) {  
        return mongoTemplate.findById(id, this.getEntityClass());  
    }  
  
    /**
     * 根据主键查询指定集合中的实体
     * @param id
     * @param collectionName
     * @return
     */
    @Override  
    public T findById(String id, String collectionName) {  
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }  
      
    
    /**
     * 查询符合条件的实体总数
     * @param query
     * @return
     */ 
    @Override  
    public long count(Query query){  
        return mongoTemplate.count(query, this.getEntityClass());  
    }  
      
    /**
   	 * 根据条件检索多个实体
   	 * @param query
   	 * @return
   	 */
   	@SuppressWarnings("unchecked")
	public GeoResults<T> findBynear(NearQuery query){
   		return  mongoTemplate.geoNear(query, this.getEntityClass());
   	}
       
    
    
    
    
    
    
    
    
    
    
    
    
    
  
    private Class<T> getEntityClass(){  
        return ReflectionUtils.getSuperClassGenricType(getClass());  
    }  
  
}

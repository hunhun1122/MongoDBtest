package com.gwm.monodb.service;

import com.gwm.monodb.dao.entity.Mapinfo;
import java.util.List;
import com.gwm.monodb.service.BaseService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
public interface MapinfoService extends BaseService<Mapinfo>{
	/**
     * 查询附近的人
     * @param (100,100),10
     * @return
     */
	public List<Mapinfo> findBypointNearsphere(Point p,double d);
	/**
	    * 查询圆形区域
	    * @param (100,100),10/3963.2
	    * @return
	    */
	
	public List<Mapinfo> findBycycilegeowithin(Point p, Distance distanc);
	
	/**
	    * 查询矩形区域
	    * @param (100,100),(100,100)
	    * @return
	    */
	public List<Mapinfo> findByboxgeowithin(Point leftp,Point rightp);
	/**
	 * 查询附近的*公里的餐厅 、加油站
	 * @param p
	 * @param distanc
	 * @return
	 */
	public GeoResults<Mapinfo> findByNearQuery(Point p, Distance distanc);

}

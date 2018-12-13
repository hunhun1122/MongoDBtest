package com.gwm.monodb.service.impl;
import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gwm.monodb.dao.entity.Mapinfo;
import com.gwm.monodb.service.MapinfoService;

@Service("mapinfoServiceImpl")
public class MapinfoServiceImpl extends BaseServiceImpl<Mapinfo> implements MapinfoService{
		
	public List<Mapinfo> findByboxgeowithin(Point leftp, Point rightp) {
		// TODO Auto-generated method stub
		Query query =new Query(Criteria.where("loc").within(new Box(leftp, rightp)));
		return baseMongoDAO.find(query);
	}


	public List<Mapinfo> findBycycilegeowithin(Point center, Distance radius) {
		// TODO Auto-generated method stub
		
		
		Query query = new Query(Criteria.where("loc").withinSphere(new Circle(center, radius)));
		
		return baseMongoDAO.find(query);
	}


	public List<Mapinfo> findBypointNearsphere(Point point, double maxDistance) {
		// TODO Auto-generated method stub
		
	    	Query query = new Query(Criteria.where("loc").nearSphere(point).maxDistance(maxDistance).and("category").is("market"));
	    		    	
	    	return baseMongoDAO.find(query);
	      
		   
	}


	@Override
	public GeoResults<Mapinfo> findByNearQuery(Point p, Distance distanc) {

		NearQuery query = NearQuery.near(p).maxDistance(distanc);
		return  baseMongoDAO.findBynear(query);
	}
	
	
	
	

}

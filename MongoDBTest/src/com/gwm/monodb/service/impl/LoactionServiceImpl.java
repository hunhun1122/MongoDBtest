package com.gwm.monodb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gwm.monodb.dao.entity.Distanceinfo;
import com.gwm.monodb.dao.entity.Districtinfo;
import com.gwm.monodb.dao.entity.Locationinfo;
import com.gwm.monodb.dao.impl.DistanceDAOImpl;
import com.gwm.monodb.dao.impl.DistrictinDAOImpl;
import com.gwm.monodb.dao.impl.LoactionDAOImpl;
import com.gwm.monodb.service.LoactionService;

@Service("loactionServiceImpl")
public class LoactionServiceImpl extends BaseServiceImpl<Locationinfo> implements LoactionService{

	@Autowired
	DistrictinDAOImpl districtinDAOImpl;
	@Autowired
	LoactionDAOImpl loactionDAOImpl;
	@Autowired
	DistanceDAOImpl distanceDAOImpl;
	
	public List<Locationinfo> findByName(String name)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return baseMongoDAO.find(query);
	}

	/**
	 * 将json树的叶子节点拆出来
	 */
	@Override
	public void datata() {
		// TODO Auto-generated method stub
		List<Locationinfo> list = loactionDAOImpl.findAll();
		int i=0;
		
		for(Locationinfo li:list.get(0).getChildren()){		 
			
			if(li.getChildren()==null){
				continue; //去除台湾省
			 }
			
			
			//lili 省级  直辖市
			for(Locationinfo lili:li.getChildren()){
				//直辖市下的区先
				if(lili.getLevel().equals("district")){
					Districtinfo di=new Districtinfo();
					di.setAdcode(lili.getAdcode());
					di.setCenter(lili.getCenter());
					di.setLevel(lili.getLevel());
					di.setName(lili.getName());	
					di.setLongname(li.getName()+"-"+li.getName()+"-"+lili.getName());
					districtinDAOImpl.save(di);						
					System.out.println(++i);
					if(i==2860){
						System.out.println("");
					}
				}else if(lili.getLevel().equals("city")&&lili.getChildren()==null){
					//省级直属的市，市下没有县区
					Districtinfo di=new Districtinfo();
					di.setAdcode(lili.getAdcode());
					di.setCenter(lili.getCenter());
					di.setLevel(lili.getLevel());
					di.setName(lili.getName());	
					di.setLongname(li.getName()+"-"+li.getName()+"-"+lili.getName());
					districtinDAOImpl.save(di);	
					System.out.println(++i);
					if(i==2860){
						System.out.println("");
					}
				}else{					
				 //第三级的是市下面的县去
				for(Locationinfo lilili:lili.getChildren()){
					Districtinfo di=new Districtinfo();
					di.setAdcode(lilili.getAdcode());
					di.setCenter(lilili.getCenter());
					di.setLevel(lilili.getLevel());
					di.setName(lilili.getName());	
					di.setLongname(li.getName()+"-"+lili.getName()+"-"+lilili.getName());
					districtinDAOImpl.save(di);
					System.out.println(++i);
					if(i==2860){
						System.out.println("");
					}
				 }
				}
				
			}
			
		}
	}
  /***
    * 各个县区距离计算
   */
	@Override
	public void jisuan() {
		// TODO Auto-generated method stub
		List<Districtinfo>  ld=new ArrayList();
		ld=districtinDAOImpl.findAll();
		int i=1;
		Date d1=new Date();
		for(Districtinfo one:ld){
			
			for(Districtinfo two:ld){				
				Distanceinfo  di=new Distanceinfo();
				di.setOneadcode(one.getAdcode());
				di.setOnelevel(one.getLevel());				
				di.setOnename(one.getName());
				di.setOnelongname(one.getLongname());
				di.setTwoadcode(two.getAdcode());
				di.setTwolevel(two.getLevel());				
				di.setTwoname(two.getName());
				di.setTwolongname(two.getLongname());			    
				di.setDistance(getDistance(one.getCenter()[0],one.getCenter()[1],
						two.getCenter()[0],two.getCenter()[1]));
				
				distanceDAOImpl.save(di);
				System.out.println(i++);
			}
		}
		Date d2=new Date();
        int days = (int) ((d1.getTime() - d2.getTime()) / (1000));
        System.out.println("用时："+days);
		
		
	}
	
	private static double EARTH_RADIUS = 6378.137;
	 
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		//s = s * 1000;
		BigDecimal bg = new BigDecimal(s);
        double f1 = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 计算线路使用
	 */
	public void xianlu() {
	
		
		
		
	}
}

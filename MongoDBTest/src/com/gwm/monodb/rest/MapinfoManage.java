package com.gwm.monodb.rest;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.management.counter.Variability;

import com.gwm.monodb.dao.entity.Mapinfo;
import com.gwm.monodb.dao.entity.Person;
import com.gwm.monodb.service.MapinfoService;

import com.gwm.monodb.util.BaseDto;
import com.gwm.monodb.util.Dto;
import com.gwm.monodb.util.JsonHelper;

@Controller
@RequestMapping("/mapinfo")

public class MapinfoManage {
	@Autowired
	private MapinfoService mapinfoServiceImpl;
	
	/**
	 * 查询附近的人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/querypoint",method=RequestMethod.GET)
	public @ResponseBody Dto queryPlaceListJson(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("coming in queryUserListJson");
		Dto result = new BaseDto();
		Point p = new Point(-72.97,30.77);
		double distnace =0.90;
		try{
		List<Mapinfo> list = mapinfoServiceImpl.findBypointNearsphere(p, distnace);
		System.out.println("list is "+list);
		result.put("places", list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		//String json = JsonHelper.encodeList2Json(list, "yyyy-MM-dd");
		//printJson(response,json);
		//return json;
	}
	
	
	/**
	 * 查询原型区域
	 */
	@RequestMapping(value="/querycycile",method=RequestMethod.GET)
	public @ResponseBody Dto queryPlaceListJson1(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("coming in queryUserListJson");
		Dto result = new BaseDto();
		Point p = new Point(-72.97,30.77);
		double distnace =0.90;	
		Distance distanc=new Distance(distnace,Metrics.KILOMETERS);
		try{
		List<Mapinfo> list = mapinfoServiceImpl.findBycycilegeowithin(p, distanc);
		System.out.println("list is "+list);
		result.put("places", list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		//String json = JsonHelper.encodeList2Json(list, "yyyy-MM-dd");
		//printJson(response,json);
		//return json;
	}
	
	
	/**
	 * 查询附近10公里的餐厅
	 */
	@RequestMapping(value="/querynear",method=RequestMethod.GET)
	public @ResponseBody Dto queryPlaceListJson2(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("coming in queryUserListJson");
		Dto result = new BaseDto();
		Point p = new Point(-73.97, 40.77);
		double distnace =0.90;
		Distance distanc=new Distance(distnace,Metrics.KILOMETERS);
		try{
		GeoResults<Mapinfo> list = mapinfoServiceImpl.findByNearQuery(p, distanc);
		System.out.println("list is "+list);
		result.put("places", list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		//String json = JsonHelper.encodeList2Json(list, "yyyy-MM-dd");
		//printJson(response,json);
		//return json;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 向请求端响应Json格式的结果
	 * @param response
	 * @param jsonStr Json字符串
	 * @throws IOException
	 */
	private void printJson(HttpServletResponse response,String jsonStr)
	{
		try
		{
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(jsonStr);
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
	}
}

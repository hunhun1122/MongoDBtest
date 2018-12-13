package com.gwm.monodb.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gwm.monodb.dao.entity.Locationinfo;
import com.gwm.monodb.dao.entity.Person;
import com.gwm.monodb.service.LoactionService;
import com.gwm.monodb.util.JsonHelper;

@Controller
@RequestMapping("/loactiontest")
public class LoactionTest {
	@Autowired
	private LoactionService loactionServiceImpl;
	
	@RequestMapping(value="/queryList",method=RequestMethod.GET)
	public void queryUserListJson(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("coming in ");
		//List<Person> list = myTestServiceImpl.findAll();
		//List<Locationinfo> list = loactionServiceImpl.findAll();
		//String json = JsonHelper.encodeList2Json(list, "yyyy-MM-dd");
		/***
		 * 将json树拆分出叶子节点
		 */
		loactionServiceImpl.datata();
		printJson(response,"json");
		
	}
	@RequestMapping(value="/jisuan",method=RequestMethod.GET)
	public void jisuan(HttpServletRequest request,HttpServletResponse response)
	{
		
		/***
		 * 计算两个县区之间的距离
		 */
		loactionServiceImpl.jisuan();
		printJson(response,"json");
		
	}
	
	@RequestMapping(value="/chaifen",method=RequestMethod.GET)
	public void chaifen(HttpServletRequest request,HttpServletResponse response)
	{
		
		/***
		 * 计算线路
		 */
		loactionServiceImpl.xianlu();
		printJson(response,"json");
		
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


package com.gwm.monodb.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gwm.monodb.dao.entity.Person;
import com.gwm.monodb.service.MyTestService;
import com.gwm.monodb.util.JsonHelper;

@Controller
@RequestMapping("/mytest")
public class MyTestRestfulService {

	@Autowired
	private MyTestService myTestServiceImpl;
	
	@RequestMapping(value="/queryList",method=RequestMethod.GET)
	public String queryUserListJson(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("coming in queryUserListJson");
		//List<Person> list = myTestServiceImpl.findAll();
		List<Person> list = myTestServiceImpl.findByName("sailor");
		String json = JsonHelper.encodeList2Json(list, "yyyy-MM-dd");
		//printJson(response,json);
		return "userManage.jsp";
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

package com.gwm.monodb.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwm.monodb.util.BaseDto;
import com.gwm.monodb.util.Dto;
import com.gwm.monodb.dao.entity.UserInfo;
import com.gwm.monodb.service.UserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoManage {

	@Autowired
	private UserInfoService userInfoServiceImpl;
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response)
	{
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		try
		{
			if(userInfoServiceImpl.login(loginID, password))
			{
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				response.sendRedirect(basePath+"userManage/userManage.jsp");
				return null;
			}
			else
			{
				return "error.jsp";
			}
		}
		catch(Exception e)
		{
			return "error.jsp";
		}
		
	}
	/**
	 * 查询用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAll",method=RequestMethod.GET)
	public @ResponseBody Dto queryAll(HttpServletRequest request,HttpServletResponse response)
	{
		Dto result = new BaseDto();
		try
		{
			List<UserInfo> list = userInfoServiceImpl.findAll();
			result.put("users", list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 添加用户实体
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/Add",method=RequestMethod.POST)
	public String addUser(@RequestBody UserInfo user,HttpServletRequest request,HttpServletResponse response)
	{
		JSONObject jsonObject=new JSONObject();
		try
		{
			userInfoServiceImpl.save(user);
			jsonObject.put("success",  new Boolean(true));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("success",  new Boolean(false));
			jsonObject.put("msg",  e.getMessage());
		}
		
		printJson(response,jsonObject.toString() );
		return null;
		
	}
	
	/**
	 * 更新用户实体
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateUser(@RequestBody UserInfo user,HttpServletRequest request,HttpServletResponse response)
	{
		JSONObject jsonObject=new JSONObject();
		try
		{
			userInfoServiceImpl.saveOrUpdate(user);
			jsonObject.put("success",  new Boolean(true));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("success",  new Boolean(false));
			jsonObject.put("msg",  e.getMessage());
		}
		
		printJson(response,jsonObject.toString() );
		return null;
		
	}
	
	/**
	 * 根据主键获取用户信息
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userDetail",method=RequestMethod.POST)
	public @ResponseBody Dto getUserDetail(@RequestBody UserInfo user,HttpServletRequest request,HttpServletResponse response)
	{
		UserInfo userinfo = null;
		Dto dto = new BaseDto();
		try
		{
			String id = user.getId();
			userinfo = userInfoServiceImpl.findById(id);
			dto.put("user", userinfo);
			dto.put("success", new Boolean(true));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dto.put("success", new Boolean(false));
			dto.put("msg", e.getMessage());
		}
		return dto;
		
	}
	
	/**
	 * 根据主键删除用户
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Dto deleteUser(@RequestBody BaseDto users,HttpServletRequest request,HttpServletResponse response)
	{
		Dto dto = new BaseDto();
		dto.put("success", new Boolean(true));
		List list = users.getAsList("ids");
		try
		{
			for(int i=0;i<list.size();i++)
			{
				String id = (String)list.get(i);
				userInfoServiceImpl.removeById(id);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dto.put("success", new Boolean(false));
			dto.put("msg", e.getMessage());
		}
		return dto;
		
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

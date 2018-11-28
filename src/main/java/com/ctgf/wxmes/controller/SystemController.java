package com.ctgf.wxmes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.service.UserService;

@Controller
public class SystemController
{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, String userName, String password)
	{
		request.setAttribute("message", request.getParameter("message"));
		return "system/login";
	}
	
	@RequestMapping("index")
	public String init()
	{
		return "system/index";
	}
	
	@RequestMapping("userList")
	public String userList(@RequestParam(name = "pageNo", required = false) Integer pageNo) throws 	Exception
	{ 
		Integer totalpages = (Integer) session.getAttribute("totalpages");
		if(pageNo== null || pageNo <= 0 || totalpages == null)
		{
			pageNo = 1;
		}
		else if(pageNo >= totalpages)
		{
			pageNo = totalpages;
		}
		JSONObject res = userService.getUserListByPage(pageNo);
		return "user/userList";
	}
	
	@RequestMapping("user")
	public String user()
	{
		return "user/user";
	}
}

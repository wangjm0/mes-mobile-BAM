package com.ctgf.wxmes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.entity.User;
import com.ctgf.wxmes.service.UserService;

@Controller
@RequestMapping("user")
public class UserController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("add")
	public String addUser(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "username2", required = false) String username,
			@RequestParam(name = "password1", required = false) String password1,
			@RequestParam(name = "password2", required = false) String password2,
			@RequestParam(name = "admin", required = false) String admin)
	{
		String message = null;
	
		
		JSONObject json = new JSONObject();
		if(userId == "" && password1 =="" && password2 =="")
		{
			message = "用户ID、用户名、用户密码都不能为空";
		}
		else if(password1.equals(password2))
		{
			int a = 0;
			if(admin != "" && admin != null)
			{
				a = Integer.valueOf(admin);
			}
			json = userService.addUser(userId, username, password1, a);
			if(json.getBooleanValue("success"))
			{
				return "redirect:/index.do";
			}
			else
			{
				message = json.getString("message");
			}
		
		}
		else
		{
			message ="前后密码不匹配";
		}
		session.setAttribute("msg", message);
		return "redirect:/login.do";
	}
	
	@RequestMapping("getUserListByPage")
	@ResponseBody
	public JSONObject getUserListByPage(int pageNo)
	{
		JSONObject res = userService.getUserListByPage(pageNo);
		return res;
	}
	
	@RequestMapping("findUserByName")
	@ResponseBody
	public JSONObject findUserByName(@RequestParam(name = "findName", required = false) String findName)
	{
		JSONObject res = userService.findByName(findName);
		return res;
	}
	
	@RequestMapping("setAdmin")
	@ResponseBody
	public String setAdmin(@RequestParam(name = "userId", required = false) String userId)
	{
		if(userService.setAdmin(userId))
		{
			User user = userService.findByUserId(userId);
			session.setAttribute("user", user);
			return "redirect:/index.do";
		}
		return "redirect:/login.do";
	}
}

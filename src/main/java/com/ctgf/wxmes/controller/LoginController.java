package com.ctgf.wxmes.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("login") 
public class LoginController
{
	@Autowired
	private UserService userService;

	@RequestMapping("isLogin")
	public String isLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(name="username", required = false) String username, @RequestParam(name="password", required = false) String password)
	{
		if(username == null || password == null || username.equals("") || password.equals(""))
		{
			model.addAttribute("message", "账号或密码不能为空");
		}
		else
		{
			boolean res =   userService.login(username, password);
			if(res) {
				/*response.sendRedirect(request.getContextPath()+"/index.do");	*/	
				List<User> list = userService.findByUserName(username);				
				session.setAttribute("user", list.get(0));
				return "redirect:/index.do";
			}
			model.addAttribute("message", "账号或密码错误");
		}
		return "redirect:/login.do";
	}
	
	@RequestMapping("checkAll")
	@ResponseBody
	public JSONObject checkAll() 
	{
		JSONObject res = new JSONObject();
		List<User> list = userService.findAll();
		if(list.isEmpty())
		{
			res.put("success", false);
			res.put("bn", true);
			res.put("message", "没有用户存在，是否创建新用户？");
		}
		else
		{
			list = userService.findAdmin();
			if(list.isEmpty())
			{
				res.put("success", false);
				res.put("bn", false);
				res.put("message", "没有管理员存在，是否选择现有用户成为管理员？");
			}
			else 
			{
				res.put("success", true);
			}
		}
		return res;
	}
}

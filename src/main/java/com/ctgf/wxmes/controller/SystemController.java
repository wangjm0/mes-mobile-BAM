package com.ctgf.wxmes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SystemController
{
	@RequestMapping("login")
	public String login(HttpServletRequest request, String userName, String password)
	{ 
		request.setAttribute("message", request.getParameter("message"));  
		return "system/login";
	}
	
	@RequestMapping("index")
	public String ini()
	{ 
		return "system/index";
	}
}

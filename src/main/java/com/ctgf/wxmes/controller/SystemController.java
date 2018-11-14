package com.ctgf.wxmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("system") 
public class SystemController
{
	@RequestMapping("login")
	public String login(String userName, String password)
	{ 
		return "system/login";
	}
	
	@RequestMapping("index")
	public String ini()
	{ 
		return "system/index";
	}
}

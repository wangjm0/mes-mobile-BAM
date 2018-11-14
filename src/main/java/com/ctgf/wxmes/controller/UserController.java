package com.ctgf.wxmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user") 
public class UserController
{
	@RequestMapping("add")
	public String addUser()
	{
		return null;
		
	}
}

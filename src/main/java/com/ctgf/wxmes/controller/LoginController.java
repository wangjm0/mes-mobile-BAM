package com.ctgf.wxmes.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctgf.wxmes.service.UserService;

@Controller
@RequestMapping("login") 
public class LoginController
{
	@Autowired
	private UserService userService;

	@RequestMapping("isLogin")
	public String isLogin(Model model, @RequestParam String username, @RequestParam String password)
	{
		boolean res =   userService.login(username, password);
		if(res) {
			return "system/index";
		}
		model.addAttribute("message", "账号或密码错误");
		return "redirect : system/login";
	}
}

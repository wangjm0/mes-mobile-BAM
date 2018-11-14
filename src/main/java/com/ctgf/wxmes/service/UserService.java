package com.ctgf.wxmes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ctgf.wxmes.dao.UserDao;

@Service
public class UserService 
{
	@Autowired
	private UserDao userDao;
	
	public boolean findByUserName(String userName)
	{
		List<Object[]> list = userDao.findByUserName(userName);		
		if(list != null)
		{
			return true;
		}
		return false;
	}
	
	public boolean login(String userName, String passWord)
	{
		List<Object[]> list = userDao.login(userName, passWord);
		if(list != null)
		{
			return true;
		}
		return false;
	/*	String message = null;
		
		try
		{
			if(userName == null || passWord == null) 
			{
				
				message  =  "请输入用户名和密码！";
			}
			else {
				List<Object[]> userList = userDao.findByUserName(userName);
				if(userList == null)
				{
					
					message = "用戶不存在！";
				}
				else
				{
					List<Object[]> userList2 = userDao.login(userName, passWord);
					if(userList2 == null)
					{
						
						message="密码错误\n请重新输入！";
					}
					else
					{
						response.sendRedirect(request.getContextPath()+"/system/index.do");
						return true;
					}
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return false;*/
	}
	
	public String addUser(String userId, String userName, String passWord) 
	{
		String message = null;
		try
		{
			List<Object[]> userList = userDao.findByUserId(userId);
			if(userList != null)
			{
				message = "添加错误，用戶ID已存在！";
			}
			else {
				boolean isSuccess = userDao.addUser(userId, userName, passWord);
				if(isSuccess == true)
				{
					message = "添加成功！";
				}
				else {
					message = "添加失败，请重新添加！";
				}
			}			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return message;
	}
	
/*	public void getUrl(HttpServletRequest request, HttpServletResponse response, String message)
	{
		 try {
			response.sendRedirect(request.getContextPath()+message);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}*/
}

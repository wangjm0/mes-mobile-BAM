package com.ctgf.wxmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctgf.wxmes.dao.UserDao;
import com.ctgf.wxmes.entity.User;

@Service
public class UserService 
{
	@Autowired
	private UserDao userDao;
	
	public User findById(String userId)
	{
	User user = userDao.findByUserId(userId);
		if(user == null)
		{
			return null;
		}
		return user;
	}
	
	public 	List<User> findByUserName(String userName)
	{
		 return userDao.findByUserName(userName);		
	}
	
	public boolean login(String userName, String passWord)
	{
		List<Object> list = userDao.login(userName, passWord);
	
		if(list.isEmpty())
		{
			return false;
		}
		return true;
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
	
	public String addUser(String userId, String userName, String passWord, int admin) 
	{
		String message = null;
		try
		{
			User user = userDao.findByUserId(userId);
			if(user != null)
			{
				message = "添加错误，用戶ID已存在！";
			}
			else {
				boolean isSuccess = userDao.addUser(userId, userName, passWord, admin);
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
	
	
	public List<User> findAll()
	{
		return  userDao.findAll();
	}
	
	public List<User> findAdmin()
	{
		return userDao.findAdmin();
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

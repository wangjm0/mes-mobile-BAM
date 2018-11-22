package com.ctgf.wxmes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.dao.UserDao;
import com.ctgf.wxmes.entity.User;

@Service
public class UserService
{
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userDao;
	
	public User findByUserId(String userId)
	{
		User user = userDao.findByUserId(userId);
		if(user == null)
		{
			return null;
		}
		return user;
	}
	
	public List<User> findByUserName(String userName)
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
		/*
		 * String message = null;
		 * 
		 * try { if(userName == null || passWord == null) {
		 * 
		 * message = "请输入用户名和密码！"; } else { List<Object[]> userList =
		 * userDao.findByUserName(userName); if(userList == null) {
		 * 
		 * message = "用戶不存在！"; } else { List<Object[]> userList2 =
		 * userDao.login(userName, passWord); if(userList2 == null) {
		 * 
		 * message="密码错误\n请重新输入！"; } else {
		 * response.sendRedirect(request.getContextPath()+"/system/index.do"); return
		 * true; } } }
		 * 
		 * } catch(Exception e) { e.printStackTrace(); } model.addAttribute("message",
		 * message); return false;
		 */
	}
	
	public JSONObject addUser(String userId, String userName, String passWord, int admin)
	{
		JSONObject json = new JSONObject();
		try
		{
			User user = userDao.findByUserId(userId);
			if(user != null)
			{
				json.put("success", false);
				json.put("message", "添加错误，用戶ID已存在！");
				
			}
			else
			{
				user = new User();
				user.setAdmin(admin == 1);
				user.setPassWord(passWord);
				user.setUserId(userId);
				user.setUserName(userName);
				user = userDao.save(user);
				if(user != null)
				{
					json.put("success", true);
					json.put("message", "添加成功！");
				}
				else
				{
					json.put("success", false);
					json.put("message", "添加失败，请重新添加！");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}
	
	public List<User> findAll()
	{
		return userDao.findAll();
	}
	
	public List<User> findAdmin()
	{
		return userDao.findAdmin();
	}
	/*
	 * public void getUrl(HttpServletRequest request, HttpServletResponse response,
	 * String message) { try {
	 * response.sendRedirect(request.getContextPath()+message); } catch (IOException
	 * e) { // TODO 自动生成的 catch 块 e.printStackTrace(); } }
	 */
	
	/**
	 * 获得用户列表 分页显示 每页10条
	 */
	public JSONObject getUserListByPage(int pageNo)
	{
		JSONObject json = new JSONObject();
		/*
		 * List<User> list = userDao.findAll(); if(!list.isEmpty()) {
		 * json.put("success", true); json.put("list", list);
		 * session.setAttribute("list", list); } else { json.put("success", false); }
		 */
		try
		{
			
			int count = (int) userDao.count();
			// 总页数
			int totalpages = (int) Math.ceil(count / (10 * 1.0));
			if(0 < pageNo && pageNo <= totalpages)
			{
				// 10条用户数据
				List<User> list = userDao.getUserListByPage((pageNo - 1) * 10);
				json.put("success", true);
				json.put("totalpages", totalpages);
				json.put("pageNo", pageNo);
				json.put("list", list);
				
				session.setAttribute("list", list);
				session.setAttribute("pageNo", pageNo);
				session.setAttribute("totalpages", totalpages);
				
				// model.addAttribute("list", list); model.addAttribute("pageNo",pageNo);
				// model.addAttribute("totalpages", totalpages);
				
			}
			else
			{
				json.put("success", false);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JSONObject findByName(String name)
	{
		JSONObject json = new JSONObject();
		try
		{
			List<User> list = userDao.findByName(name);
			if(!list.isEmpty())
			{
				json.put("success", true);
				json.put("list", list);
				
				session.setAttribute("list", list);
			}
			else
			{
				json.put("success", false);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	public boolean setAdmin(String userId)
	{
		int i = userDao.setAdmin(userId);
		if(i == 1)
		{
			return true;
		}
		return false;
	}
}

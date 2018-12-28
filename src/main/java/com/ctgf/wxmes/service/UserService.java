package com.ctgf.wxmes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.dao.RoleDao;
import com.ctgf.wxmes.dao.UserDao;
import com.ctgf.wxmes.entity.Compound;
import com.ctgf.wxmes.entity.Page;
import com.ctgf.wxmes.entity.ProdLine;
import com.ctgf.wxmes.entity.ProdProcess;
import com.ctgf.wxmes.entity.Role;
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
	
	@Autowired
	private RoleService roleService;
	
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
		return userDao.findByName(userName);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Map<String, List<Compound>> getCompoundMap(List<User> userList)
	{
		Map<String, List<Compound>> compoundMap = new HashMap<>();
		Map<String, String> prodLineStr = new HashMap<>();
		Map<String, String> prodProcessStr = new HashMap<>();
		Map<String, String> pageStr = new HashMap<>();
		for (User user : userList)
		{
			List<Compound> compoundList = new ArrayList<>();
			List<Role> roleList = user.getRoleList();
			//去重
			roleList =roleList.stream().distinct().collect(Collectors.toList());
			if(roleList.isEmpty())
			{
				continue;
			}
			Compound cpd = new Compound();
			StringBuffer sb = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			for (Role role : roleList)
			{
				cpd = roleService.getCompound(role.getId());
				compoundList.add(cpd);
			}
			compoundMap.put(user.getId(), compoundList);
			
			for (ProdLine pl : cpd.getProdLineList())
			{
				sb.append(pl.getName()).append(",");
			}
			prodLineStr.put(user.getId(), sb.toString());
			
			for (ProdProcess pp : cpd.getProdProcessList())
			{
				sb1.append(pp.getName()).append(",");
			}
			
			prodProcessStr.put(user.getId(), sb1.toString());
			
			for (Page p : cpd.getPageList())
			{
				sb2.append(p.getName()).append(",");
			}
			
			pageStr.put(user.getId(), sb2.toString());
			
		}
		pageStr.remove(Collections.singleton(null)); 
		session.setAttribute("list", userList);
		
		session.setAttribute("compoundMap", compoundMap);
		request.setAttribute("prodLineStr", prodLineStr);
		request.setAttribute("prodProcessStr", prodProcessStr);
		request.setAttribute("pageStr", pageStr);
		
		
		return compoundMap;
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
	
	public boolean update(User user)
	{
		if(userDao.findById(user.getId()) != null)
		{
			userDao.updateUser(user.getId(), user.getUserId(), user.getUserName(), user.getPassWord(), user.isAdmin());
			for(Role role : user.getRoleList())
			{
				userDao.updateUserRole(role.getId(), user.getId());
			}
			return true;
		}
		return false;
//		String id = user.getId();
//		if(userDao.findById(id) != null)
//		{
//			userDao.deletUserRole(id);
//			userDao.deleteById(id);
//			
//			userDao.save(user);
//			return true;
//		}
//		return false;
		
	}
	
	/*
	 * public User save(String userId, String username, String password, boolean
	 * admin) { return userDao.save(new User(userId, username, password, admin)); }
	 */
	public JSONObject update(String userId, String userName, String passWord, boolean admin)
	{
		JSONObject json = new JSONObject();
		try
		{
			User user = userDao.findByUserId(userId);
			User user1 = (User) session.getAttribute("user");
			if(user != null && !user.equals(user1))
			{
				
				json.put("success", false);
				json.put("message", "修改错误，用戶ID已存在！");
			}
			else
			{
				int isUpdate = userDao.updateUser( user1.getId(),userId, userName, passWord, admin);
				if(isUpdate == 1)
				{
					json.put("success", true);
					json.put("message", "修改成功！");
					session.setAttribute("user", new User(userId, userName, passWord, admin));
				}
				else
				{
					json.put("success", false);
					json.put("message", "修改失败！");
				}
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject addUser(String userId, String userName, String passWord, boolean admin)
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
				user.setAdmin(admin);
				user.setPassWord(passWord);
				user.setUserId(userId);
				user.setUserName(userName);
				user = userDao.save(user);
				if(user != null)
				{
					
					System.out.println(user.toString());
					json.put("success", true);
					json.put("message", "添加成功！");
					session.setAttribute("user", user);
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
		return (List<User>) userDao.findAll();
	}
	
	public List<User> findAdmin()
	{
		return userDao.findAdmin();
	}
	
	
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
				getCompoundMap(list);
				request.setAttribute("pageNo", pageNo);
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
	
	public void delete(String userId)
	{
		userDao.delete(userDao.findByUserId(userId));
	}
	
}

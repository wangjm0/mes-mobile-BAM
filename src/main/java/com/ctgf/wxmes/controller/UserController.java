package com.ctgf.wxmes.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.entity.Compound;
import com.ctgf.wxmes.entity.Role;
import com.ctgf.wxmes.entity.User;
import com.ctgf.wxmes.service.RoleService;
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
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("createUser")
	public String createUser(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "username2", required = false) String username,
			@RequestParam(name = "password1", required = false) String password1,
			@RequestParam(name = "password2", required = false) String password2,
			@RequestParam(name = "admin", required = false) boolean admin, Model model)
	{
		String message = null;
		
		JSONObject json = new JSONObject();
		if(userId == "" && password1 == "" && password2 == "")
		{
			message = "用户ID、用户名、用户密码都不能为空";
		}
		else if(password1.equals(password2))
		{/*
			 * boolean a = false; if(admin != "" && admin != null) { a =true; }
			 */
			json = userService.addUser(userId, username, password1, admin);
			if(json.getBooleanValue("success"))
			{
				return "redirect:/userList.do";
			}
			else
			{
				message = json.getString("message");
			}
		}
		else
		{
			message = "前后密码不匹配";
		}
		model.addAttribute("msg", message);
		// request.setAttribute();
		
		return "redirect:/createUser.do";
	}
	
	@RequestMapping("add")
	public String addUser(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "username2", required = false) String username,
			@RequestParam(name = "password1", required = false) String password1,
			@RequestParam(name = "password2", required = false) String password2,
			@RequestParam(name = "admin", required = false) boolean admin)
	{
		String message = null;
		
		JSONObject json = new JSONObject();
		if(userId == "" && password1 == "" && password2 == "")
		{
			message = "用户ID、用户名、用户密码都不能为空";
		}
		else if(password1.equals(password2))
		{/*
			 * boolean a = false; if(admin != "" && admin != null) { a =true; }
			 */
			json = userService.addUser(userId, username, password1, admin);
			if(json.getBooleanValue("success") && admin)
			{
				return "redirect:/index.do";
			}
			else if(json.getBooleanValue("success") && !admin)
			{
				return "redirect:/login.do";
			}
			else
			{
				message = json.getString("message");
			}
			
		}
		else
		{
			message = "前后密码不匹配";
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
	
	@RequestMapping("findUserlistByName")
	public String findUserlistByName()
	{
		String name = request.getParameter("username");
		request.setAttribute("findUserliatByName", true);
		List<User> userList = userService.findByUserName(name);
		userService.getCompoundMap(userList);
		return "/user/userList";
	}
	
	@RequestMapping("setAdmin")
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
	
	@RequestMapping("findByUserId")
	public String findByUserId(@RequestParam(name = "userId", required = false) String userId)
	{
		User user = userService.findByUserId(userId);
		if(user != null)
		{
			request.setAttribute("user", user);
			List<Role> roleList = user.getRoleList();
			roleList = roleList.stream().distinct().collect(Collectors.toList());
			List<Compound> compoundList = roleService.getCompoundList(roleList);
			session.setAttribute("compoundList", compoundList);
			session.setAttribute("prodLineList", roleService.findAllProdLine());
			session.setAttribute("prodProcessList", roleService.findAllProdProcess());
			session.setAttribute("pageList", roleService.findAllPage());
		}
		return "forward:/user.do";
	}
	
	/**
	 * 添加或保存用户
	 */
	@RequestMapping("update")
	public String update(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "admin", required = true) boolean admin)
	{
		/*
		 * User user = userService.findByUserId(userId); User user1 = (User)
		 * session.getAttribute("user"); if(user)
		 */
		JSONObject json = userService.update(userId, username, password, admin);
		session.setAttribute("message", json.getString("message"));
		
		return "redirect:/user.do";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("deleteUser")
	public String deleteUser(@RequestParam(name = "userId", required = false) String userId)
	{
		userService.delete(userId);
		List<User> list = (List<User>) session.getAttribute("list");
		list.remove(userService.findByUserId(userId));
		session.setAttribute("list", list);
		return "redirect:/userList.do";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("updateUserRole")
	public String updateUserRole(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "roleId", required = false) String id,
			@RequestParam(name = "roleName", required = false) String name,
			@RequestParam(name = "prodLine", required = false) Integer[] prodLine,
			@RequestParam(name = "prodProcess", required = false) Integer[] prodProcess,
			@RequestParam(name = "page", required = false) Integer[] page)
	{
		
		try
		{
			int line = getRole(prodLine);
			int process = getRole(prodProcess);
			int pg = getRole(page);
			Role role = new Role();
			role.setName(name);
			role.setProdLine(line);
			role.setProdProcess(process);
			role.setPage(pg);
			
			Role role1 = roleService.findById(id);
			
			User user = userService.findByUserId(userId);
			List<Compound> compoundList = (List<Compound>) session.getAttribute("compoundList");
			
			if(roleService.isExist(line, process, pg) && role.getName().equals(roleService.findById(id).getName()))
			{
				role = roleService.find(line, process, pg);
				user.getRoleList().remove(role1);
				user.getRoleList().add(role);
				Compound compound = roleService.getCompound(id);
				compoundList.remove(compound);
				compound = roleService.getCompound(role.getId());
				compoundList.add(compound);
			}
			else if(!roleService.isExist(line, process, pg)
					&& role.getName().equals(roleService.findById(id).getName()))
			{
				Compound compound = roleService.getCompound(id);
				compoundList.remove(compound);
				roleService.save(role);
				// role保存到数据库，自动生成id
				compound = roleService.getCompound(role.getId());
				compoundList.add(compound);
				user.getRoleList().remove(role1);
				user.getRoleList().add(role);
			}
			else
			{
				role1.setName(name);
				roleService.update(role1);
			}
			
			userService.update(user);
			request.setAttribute("user", user);
			session.setAttribute("compoundList", compoundList);
		}
		catch (Exception e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return "forward:/user.do";
	}
	
	public boolean isEquals(Role role, Role role1)
	{
		if(role.getProdLine().equals(role1.getProdLine()) && role.getProdProcess().equals(role1.getProdProcess())
				&& role.getPage().equals(role1.getPage()))
		{
			return true;
		}
		return false;
	}
	
	public int getRole( Integer[] i)
	{
		if(i == null)
			return 0;
		return roleService.getRole(i);
	}
}

package com.ctgf.wxmes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.entity.Compound;
import com.ctgf.wxmes.entity.Role;
import com.ctgf.wxmes.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("roleListByPage")
	// @ResponseBody
	public String findAll(@RequestParam(name = "pageNo", required = false) Integer pageNo)
	{
		Integer totalpages = (Integer) session.getAttribute("totalpages");
		if(pageNo == null || pageNo <= 1)
		{
			pageNo = 1;
		}
		else if(pageNo > totalpages)
		{
			pageNo = totalpages;
		}
		JSONObject json = roleService.getRoleListByPage(pageNo);
		request.setAttribute("msg", json.getString("message"));
		// return "redirect:/roleList.do";
		return "role/roleList";
	}
	
	@RequestMapping("role")
	public String toRole(String id)
	{
		Compound cpd = roleService.getCompound(id);
		session.setAttribute("compound", cpd);
		
		request.setAttribute("prodLineList", roleService.findAllProdLine());
		request.setAttribute("prodProcessList", roleService.findAllProdProcess());
		request.setAttribute("pageList", roleService.findAllPage());
		return "role/role";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("updateRole")
	public String update(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "prodLine", required = false) Integer[] prodLine,
			@RequestParam(name = "prodProcess", required = false) Integer[] prodProcess,
			@RequestParam(name = "page", required = false) Integer[] page
			)
	{
		if(prodLine == null || prodProcess == null || page == null)
		{
			return "role/role";
		}
		try
		{
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			int line = roleService.getRole(prodLine) ;
			int process = roleService.getRole(prodProcess);
			int pg = roleService.getRole(page);
			Role role = roleService.findById(id);
			role.setProdLine(line);
			role.setProdProcess(process);
			role.setPage(pg);
			role.setName(name);
			roleService.update(role);
			Compound compound = roleService.getCompound(id);

			List<Compound> compoundList = (List<Compound>) session.getAttribute("compoundList");
			Compound cpd = (Compound) session.getAttribute("compound");
			compoundList.remove(cpd);
			compoundList.add(compound);
			session.setAttribute("compoundList", compoundList);
		}
		catch (Exception e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return "role/roleList";
	}
	
	@RequestMapping("deleteRole")
	public String delete(@RequestParam(name = "id", required = false) String id) throws Exception
	{
		
		@SuppressWarnings("unchecked")
		List<Compound> cpd = (List<Compound>) session.getAttribute("compoundList");
		cpd.remove(session.getAttribute("compound"));
		session.setAttribute("compoundList", cpd);
		roleService.delete(id);
		return "role/roleList";
	}
}

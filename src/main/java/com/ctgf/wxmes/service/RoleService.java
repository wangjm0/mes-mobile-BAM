package com.ctgf.wxmes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctgf.wxmes.dao.PageDao;
import com.ctgf.wxmes.dao.ProdLineDao;
import com.ctgf.wxmes.dao.ProdProcessDao;
import com.ctgf.wxmes.dao.RoleDao;
import com.ctgf.wxmes.entity.Compound;
import com.ctgf.wxmes.entity.Page;
import com.ctgf.wxmes.entity.ProdLine;
import com.ctgf.wxmes.entity.ProdProcess;
import com.ctgf.wxmes.entity.Role;

@Service
public class RoleService
{
	@Autowired
	private ProdLineDao prodLineDao;
	
	@Autowired
	private ProdProcessDao prodProcessDao;
	
	@Autowired
	private PageDao pageDao;
	@Autowired
	
	private RoleDao roleDao;
	@Autowired
	private HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	public Role findById(String id)
	{
		return roleDao.findByUuid(id);
	}
	
	public List<Role> findAll()
	{
		return roleDao.findAll();
	}
	
	public List<ProdLine> findAllProdLine()
	{
		return (List<ProdLine>) prodLineDao.findAll();
	}
	
	public List<ProdProcess> findAllProdProcess()
	{
		return (List<ProdProcess>) prodProcessDao.findAll();
	}
	
	public List<Page> findAllPage()
	{
		return (List<Page>) pageDao.findAll();
	}
	
	public boolean isExist(int prodLine, int prodProcess, int page)
	{
		List<Role> list = roleDao.find(prodLine, prodProcess, page);
		if(list.isEmpty())
		{
			return false;
		}
		return true;
	}
	public Role find(int prodLine, int prodProcess, int page)
	{
		List<Role> list = roleDao.find(prodLine, prodProcess, page);
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	public boolean update(Role role)
	{
		Integer id = Integer.valueOf(role.getId());
		if(roleDao.existsById(id))
		{
			roleDao.deleteById(id);
			roleDao.save(role);
			return true;
		}
		return false;
		// return roleDao.update(id, name, prodLine, prodProcess, page);
	}
	
	public Role save(Role role)
	{
		return roleDao.save(role);
	}
	
	public void delete(String id)
	{
		roleDao.delete(roleDao.findByUuid(id));
	}
	
	public List<Role> getRoleList(List<Compound> compoundList)
	{
		List<Role> list = new ArrayList<>();
		for (Compound c : compoundList)
		{
			list.add(findById(c.getId()));
		}
		return list;
	}
	
	public JSONObject getRoleListByPage(int pageNo)
	{
		JSONObject json = new JSONObject();
		try
		{
			
			int count = (int) roleDao.count();
			// 总页数
			int totalpages = (int) Math.ceil(count / (10 * 1.0));
			if(0 < pageNo && pageNo <= totalpages)
			{
				// 10条用户数据
				List<Role> list = roleDao.getRoleListByPage((pageNo - 1) * 10);
				json.put("success", true);
				
				// 获得复合类集合
				List<Compound> cpdList = new ArrayList<>();
				for (Role role : list)
				{
					cpdList.add(getCompound(role.getId().toString()));
				}
				session.setAttribute("compoundList", cpdList);
				request.setAttribute("pageNo", pageNo);
				session.setAttribute("totalpages", totalpages);
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
	
	/**
	 * 取得二进制数位,从而判断产线，工序和页面
	 * 
	 * @param role
	 * @return
	 */
	public List<Integer> getCode(int role)
	{
		List<Integer> code = new ArrayList<>();
		
		// 十进制转换为二进制
		String result = Integer.toBinaryString(role);
		char[] cs = result.toCharArray();
		for (int i = 0; i < cs.length; i++)
		{
			if(cs[i] == '1')
			{
				code.add(cs.length - i - 1);
			}
		}
		return code;
	}
	
	public Integer getRole(int code)
	{
		return (int) Math.pow(2, code);
	}
	
	public Integer getRole(Integer[] code)
	{
		Integer role = null;
		for (Integer i : code)
		{
			if(role == null)
			{
				role = getRole(i);
			}
			else
			{
				role += getRole(i);
			}
		}
		return role;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Compound getCompound(String id)
	{
		Compound cpd = new Compound();
		Role role = roleDao.findByUuid(id);
		
		Integer prodLineCode = role.getProdLine();
		List<Integer> list = getCode(prodLineCode);
		List<ProdLine> prodLineList = new ArrayList<>();
		for (Integer i : list)
		{
			prodLineList.add(prodLineDao.getProdLine(i));
		}
		prodLineList.removeAll(Collections.singleton(null));
		Integer prodProcessCode = role.getProdProcess();
		list = getCode(prodProcessCode);
		List<ProdProcess> prodProcessList = new ArrayList<>();
		for (Integer i : list)
		{
			prodProcessList.add(prodProcessDao.getProdProcess(i));
		}
		prodProcessList.removeAll(Collections.singleton(null));
		Integer pageCode = role.getPage();
		list = getCode(pageCode);
		List<Page> pageList = new ArrayList<>();
		for (Integer i : list)
		{
			pageList.add(pageDao.getPage(i));
		}
		// list去除空值
		pageList.removeAll(Collections.singleton(null));
		prodLineList.removeAll(Collections.singleton(null));
		prodProcessList.removeAll(Collections.singleton(null));
		cpd.setId(id);
		cpd.setName(role.getName());
		cpd.setProdLineList(prodLineList);
		cpd.setProdProcessList(prodProcessList);
		cpd.setPageList(pageList);
		return cpd;
	}
	
	public List<Compound> getCompoundList(List<Role> roleList)
	{
		List<Compound> compoundList = new ArrayList<>();
		for (Role role : roleList)
		{
			compoundList.add(getCompound(role.getId()));
		}
		return compoundList;
	}
}

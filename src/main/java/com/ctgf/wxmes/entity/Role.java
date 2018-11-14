package com.ctgf.wxmes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*******************************************************************************************
 * 类描述：角色
 * @author: wjm
 * @date： 2018年11月6日 下午1:27:55
 * @version 1.0
 *
 *
 * Version    Date       ModifiedBy                 Content
 * -------- ---------    ----------         ------------------------
 * 1.0      2018年11月6日       wjm                             
 *******************************************************************************************
 */
@Entity
@Table(name="BAMRole")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Role extends BaseEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3918121190996479655L;

	/**
	 * 角色名称
	 */
	@Column(length=32, nullable = false)
	private String name;
	
	/**
	 * 产线代码 
	 */
	@Column(nullable = false)
	private Integer prodLine;
	
	
	/**
	 * 工序代码
	 */
	@Column(nullable = false)
	private Integer prodProcess;
	
	
	/**
	 * 页面代码 
	 */
	@Column(nullable = false)
	private Integer page;
}

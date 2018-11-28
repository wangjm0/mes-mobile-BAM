package com.ctgf.wxmes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.type.NumericBooleanType;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*******************************************************************************************
 * 类描述：用户
 * @copyright:易和软件有限公司
 * @网址:www.eahsoft.com
 * @author: LIU
 * @date： 2018年11月6日 下午2:25:03
 * @version 1.0
 *
 *
 * Version    Date       ModifiedBy                 Content
 * -------- ---------    ----------         ------------------------
 * 1.0      2018年11月6日       wjm                            
 *******************************************************************************************
 */
@Entity
@Table(name="BAMUser")
@Data
@ToString(callSuper = true,exclude= {"roleList"})
@EqualsAndHashCode(callSuper=true)
public class User extends BaseEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1489996229622911433L;

	/**
	 * 用户id,来源于企业微信通信录
	 */
	@Column(length=32, nullable = false,unique=true)
	private String userId;
	
	/** 
	 * 用户名称
	 */
	@Column(length=32, nullable = false)
	private String userName;
	

	/**
	 * 密码
	 */
	@Column(length=32, nullable = false)
	private String passWord;
	
	
    /**
     * 关联角色
     */
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinTable(name="BAMUser_BAMRole", inverseJoinColumns={@JoinColumn(name="BAMROLE_UUID")}, joinColumns={@JoinColumn(name="BAMUSER_UUID")})
	@JSONField(serialize=false)   
	private List<Role> roleList = new ArrayList<>();
    
    /**
     * 是否管理员（默认否）
     */
    @Column
    private boolean admin;
  
	public User()
	{
		super();
		// TODO 自动生成的构造函数存根
	}

  
	public User(String userId, String userName, String passWord, boolean admin)
	{
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.admin = admin;
	}



}

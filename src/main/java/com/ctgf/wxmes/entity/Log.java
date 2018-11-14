package com.ctgf.wxmes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*******************************************************************************************
 * 类描述：日志
 * @author:  wjm
 * @date： 2018年11月6日 下午2:16:45
 * @version 1.0
 *
 *
 * Version    Date       ModifiedBy                 Content
 * -------- ---------    ----------         ------------------------
 * 1.0      2018年11月6日       wjm                            
 *******************************************************************************************
 */
@Entity
@Table(name="BAMLog")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Log extends BaseEntity
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -247532361167523583L;

	/**
	 * 用户id,来源于企业微信通信录
	 */
	@Column(length=32, nullable = false)
	private String userId;
	
	/**
	 * 访问时间
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date opeTime;
	
	/**
	 * ip地址
	 */
	@Column(length=32)
	private String ipAddress; 
	
	/**
	 * 操作类型
	 */
	@Column(length=32)
	private Integer opeType;
	
	/**
	 * 操作内容
	 */
	@Column(length=32)
	private String opecontent;

}

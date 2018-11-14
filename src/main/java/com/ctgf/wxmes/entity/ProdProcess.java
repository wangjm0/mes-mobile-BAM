package com.ctgf.wxmes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.ToString;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="BAMProdProcess")
@Data
@ToString(callSuper = true)
public class ProdProcess
{
	@Id
	@Column(length=11,nullable = false)
	private Integer code;
	
	@Column(length=32, nullable = false)
	private String name;
}

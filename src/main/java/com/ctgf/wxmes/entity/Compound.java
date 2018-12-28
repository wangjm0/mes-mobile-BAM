package com.ctgf.wxmes.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Compound
{
	private String id;
	
	private String name;
	private List<ProdLine>  prodLineList;
	
	private List<ProdProcess>  prodProcessList;
	
	private List<Page>  pageList;

	
	public Compound()
	{
		super();
	}


	public Compound(String id, String name, List<ProdLine> prodLineList, List<ProdProcess> prodProcessList,
			List<Page> pageList)
	{
		super();
		this.id = id;
		this.name = name;
		this.prodLineList = prodLineList;
		this.prodProcessList = prodProcessList;
		this.pageList = pageList;
	}

	
	
}

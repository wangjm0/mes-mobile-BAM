package com.ctgf.wxmes.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ctgf.wxmes.entity.Page;

public interface PageDao extends   PagingAndSortingRepository<Page, Integer>, JpaSpecificationExecutor<Page>
{
	@Query(value = "select * from BAMPage where code = ?1", nativeQuery = true)
	Page getPage(int code);
	
	
}

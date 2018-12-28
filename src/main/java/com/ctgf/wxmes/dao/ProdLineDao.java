package com.ctgf.wxmes.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ctgf.wxmes.entity.ProdLine;

public interface ProdLineDao extends   PagingAndSortingRepository<ProdLine, Integer>, JpaSpecificationExecutor<ProdLine>
{
	@Query(value = "select * from BAMProdLine where code = ?1", nativeQuery = true)
	ProdLine getProdLine(int i);
}

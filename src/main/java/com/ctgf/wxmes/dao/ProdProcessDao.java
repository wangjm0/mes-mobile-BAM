package com.ctgf.wxmes.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ctgf.wxmes.entity.ProdProcess;

public interface ProdProcessDao extends PagingAndSortingRepository<ProdProcess, Integer>, JpaSpecificationExecutor<ProdProcess>
{
	@Query(value = "select * from BAMProdProcess where code = ?1", nativeQuery = true)
	ProdProcess getProdProcess(int code);
}

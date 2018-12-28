package com.ctgf.wxmes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ctgf.wxmes.entity.*;

public interface RoleDao extends PagingAndSortingRepository<Role, Integer>, JpaSpecificationExecutor<Role>
{
	List<Role> findAll();
	
	@Query(value = "select * from BAMRole where uuid = :uuid", nativeQuery = true)
	Role findByUuid(@Param("uuid") String uuid);
	
	@Query(value = "select * from BAMRole limit :start, 10", nativeQuery = true)
	List<Role> getRoleListByPage(@Param("start") int start);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update BAMRole set name = :name, prodLine = :prodLine, prodProcess = :prodProcess, page = :page where uuid = :id", nativeQuery = true)
	int update(@Param("id") String id, @Param("name") String name, @Param("prodLine") int prodLine,
			@Param("prodProcess") int prodPrcoess, @Param("page") int page);
	
	// @Query(value = "select * from BAMRole where uuid = ?!", nativeQuery = true)
	// Role findById(String id);
	
	@Query(value = "select * from BAMRole where prodLine = :prodLine and prodProcess = :prodProcess and page = :page", nativeQuery = true)
	List<Role> find(@Param("prodLine") int prodLine, @Param("prodProcess") int prodProcess, @Param("page") int page);
}

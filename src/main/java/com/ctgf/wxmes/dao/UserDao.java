package com.ctgf.wxmes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ctgf.wxmes.entity.User;

public interface UserDao  extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User>
{
	@Query(value="insert into User(userId, userName, passWord) value (:userId, :userName, :passWord)", nativeQuery = true)
	boolean addUser(@Param("userId")String userId, @Param("userName") String userName, @Param("passWord") String passWord);
	
	@Query(value="update User set(userId = :userId, userName = :userName, passWord = :passWord", nativeQuery = true)
	boolean updateUser(@Param("userId")String userId, @Param("userName") String userName, @Param("passWord") String passWord);
	
	@Query(value="delete * from User where userId = :userId", nativeQuery = true)
	boolean deleteUser(@Param("userId")String userId);
	
	@Query(value="select * from User where userId = :userId", nativeQuery = true)
	List<Object[]> findByUserId(@Param("userId")String userId);
	
	@Query(value="select * from User where userName = :userName", nativeQuery = true)
	List<Object[]> findByUserName(@Param("userName") String userName);
	
	@Query(value="select * from User where userName = :userName and passWord = :passWord", nativeQuery = true)
	List<Object[]> login(@Param("userName") String userName, @Param("passWord") String passWord);
	
}

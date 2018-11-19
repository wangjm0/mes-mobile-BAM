package com.ctgf.wxmes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ctgf.wxmes.entity.User;

public interface UserDao  extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User>
{
	@Query(value="insert into BAMUser(userId, userName, passWord, admin) value (:userId, :userName, :passWord, :admin)", nativeQuery = true)
	boolean addUser(@Param("userId")String userId, @Param("userName") String userName, @Param("passWord") String passWord, @Param("admin") int admin);
	
	@Query(value="update BAMUser set(userId = :userId, userName = :userName, passWord = :passWord", nativeQuery = true)
	boolean updateUser(@Param("userId")String userId, @Param("userName") String userName, @Param("passWord") String passWord);
	
	@Query(value="delete * from BAMUser where userId = :userId", nativeQuery = true)
	boolean deleteUser(@Param("userId")String userId);
	
	@Query(value="select * from BAMUser where userId = :userId", nativeQuery = true)
	User findByUserId(@Param("userId") String UserId);
	
	@Query(value="select * from BAMUser where userName = :userName", nativeQuery = true)
	List<User> findByUserName(@Param("userName") String userName);
	
	@Query(value="select * from BAMUser where userName = :userName and passWord = :passWord", nativeQuery = true)
	List<Object> login(@Param("userName") String userName, @Param("passWord") String passWord);
	
	List<User> findAll();
	
	@Query(value="select * from BAMUser where admin = 1", nativeQuery = true)
	List<User>  findAdmin();
}

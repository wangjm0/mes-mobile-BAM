package com.ctgf.wxmes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ctgf.wxmes.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User>
{
	/* User findByUuid(String uuid); */
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update BAMUser set  userId = :userId, userName = :userName, passWord = :passWord, admin =:admin where uuid = :uuid", nativeQuery = true)
	int updateUser(@Param("uuid") String uuid, @Param("userId") String userId, @Param("userName") String userName,
			@Param("passWord") String passWord, @Param("admin") boolean admin);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update BAMUser_BAMRole  set BAMROLE_UUID = ?1 where BAMUSER_UUID = ?2", nativeQuery = true)
	int updateUserRole(@Param("BAMROLE_UUID") String roleId, @Param("uuid") String uuid);
	
	@Query(value = "delete * from BAMUser where userId = :userId", nativeQuery = true)
	int deleteUser(@Param("userId") String userId);
	
	@Query(value = "select * from BAMUser where userId = :userId", nativeQuery = true)
	User findByUserId(@Param("userId") String UserId);
	
	@Query(value = "select * from BAMUser where uuid = :id", nativeQuery = true)
	User findById(@Param("id") String id);
	
	@Query(value = "select * from BAMUser where userName = :userName", nativeQuery = true)
	List<User> findByUserName(@Param("userName") String userName);
	
	@Query(value = "select * from BAMUser where userName like  concat('%', ?1, '%')", nativeQuery = true)
	List<User> findByName(String name);
	
	@Query(value = "select * from BAMUser where userName = :userName and passWord = :passWord", nativeQuery = true)
	List<Object> login(@Param("userName") String userName, @Param("passWord") String passWord);
	
	@Query(value = "select * from BAMUser where admin = 1", nativeQuery = true)
	List<User> findAdmin();
	
	@Query(value = "select count(*) from BAMUser", nativeQuery = true)
	long count();
	
	@Query(value = "select * from BAMUser limit :start, 10", nativeQuery = true)
	List<User> getUserListByPage(@Param("start") int start);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update BAMUser set admin =1 where userId = :userId", nativeQuery = true)
	int setAdmin(@Param("userId") String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "insert into BAMUser(userId, userName, passWord, admin) values(:userId, :username, :password, :admin) ", nativeQuery = true)
	int addUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password,
			@Param("admin") int admin);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from BAMUser where uuid = :id", nativeQuery = true)
	void deleteById(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from BAMUser_BAMRole where BAMUSER_UUID = :id", nativeQuery = true)
	void deletUserRole(@Param("id") String id);
}

package com.app.dca.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.app.dca.entity.Users;

public interface UserRepository extends CrudRepository<Users, Integer>, JpaRepository<Users, Integer> {	
	@Transactional 
	@Modifying
	@Query(value="update users set password=:newPassword where password=:oldPassword and user_id=:id", nativeQuery=true )
   void replace( @Param("id")int id,@Param("oldPassword")String oldPassword,@Param("newPassword")String newPassword);
	
	@Query(value = "select password from users where user_id=:userId", nativeQuery = true)
	String checkpassword(@Param("userId") int userId);


	@Query(value = "select user_name from users where user_id=:userId", nativeQuery = true)
	String checkusername(@Param("userId") int userId);
	
	@Query(value ="select count(*) from users where user_name=:userName and password=:password group by user_name", nativeQuery = true)
	int existuser(@Param("userName")String userName,@Param("password") String password);
	
	@Query(value = "select user_id from users where user_name=:userName", nativeQuery = true)
	int getUserId(@Param("userName") String username);
	
}

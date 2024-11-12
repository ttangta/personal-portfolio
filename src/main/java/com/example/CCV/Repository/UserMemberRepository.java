package com.example.CCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.UserMember;

public interface UserMemberRepository extends JpaRepository<UserMember, String>{
	@Query(value="select * from usermember where name=:name and byear=:byear and bmonth=:bmonth and bday=:bday and tel2=:tel2 and tel3=:tel3",nativeQuery = true)
	UserMember findUsermember(@Param("name") String name, 
							  @Param("byear") String byear, @Param("bmonth") String bmonth, @Param("bday") String bday, 
							  @Param("tel2") String tel2, @Param("tel3") String tel3);

	@Query(value="select * from usermember where id=:id",nativeQuery = true)
	UserMember idCheck(@Param("id") String id);
	
	@Query(value="select * from usermember where id=:id and pwd=:pwd", nativeQuery = true)
	UserMember loginChekc(@Param("id") String id, @Param("pwd") String pwd);
}

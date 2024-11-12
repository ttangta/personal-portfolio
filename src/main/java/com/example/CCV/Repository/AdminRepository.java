package com.example.CCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	@Query(value = "select * from admin where id=:id and pwd=:pwd",nativeQuery = true)
	Admin findByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd); 
}

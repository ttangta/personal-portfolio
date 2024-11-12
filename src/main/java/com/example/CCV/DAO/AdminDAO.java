package com.example.CCV.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.Entity.Admin;
import com.example.CCV.Repository.AdminRepository;

@Repository
public class AdminDAO {
	@Autowired
	AdminRepository adminRepository;
	
	//1) 관리자 로그인
	public Admin adminLogin(String id, String pwd) {
		return adminRepository.findByIdAndPwd(id,pwd);
		
	}
}

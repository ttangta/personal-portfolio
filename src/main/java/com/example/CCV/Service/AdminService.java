package com.example.CCV.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.AdminDAO;
import com.example.CCV.Entity.Admin;

@Service
public class AdminService {
	@Autowired
	AdminDAO adminDao;
	
	// 1) 관리자 로그인
	public Admin adminLogin(String id, String pwd) {
		return adminDao.adminLogin(id, pwd);
	}
}

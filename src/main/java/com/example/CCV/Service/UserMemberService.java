package com.example.CCV.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.UserMemberDAO;
import com.example.CCV.DTO.UserMemberDTO;
import com.example.CCV.Entity.UserMember;

@Service
public class UserMemberService {
	@Autowired
	UserMemberDAO userMemberDAO;
	
	
	
	// 2) 회원가입 전 이름 생년월일 휴대전화로 가입여부 찾기
	public UserMember findUser(String name, String byear, String bmonth, String bday, String tel2, String tel3) {
		return userMemberDAO.findUser(name, byear, bmonth, bday, tel2, tel3);
	}
	
	// 3) 아이디 중복확인
	public UserMember idCheck(String id) {
		return userMemberDAO.idCheck(id);
	}
	
    // 4) 회원가입 
	public UserMember joinUserinfo(UserMemberDTO dto) {
		return userMemberDAO.joinUserinfo(dto);
	}
	
	// 5) 로그인 체크
	public UserMember loginCheck(String id, String pwd) {
		return userMemberDAO.loginCheck(id, pwd);
	}
}

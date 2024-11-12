package com.example.CCV.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.DTO.UserMemberDTO;
import com.example.CCV.Entity.UserMember;
import com.example.CCV.Repository.UserMemberRepository;

@Repository
public class UserMemberDAO {
	@Autowired
	UserMemberRepository usermemberRepository;
	
	// 1) 가입유무 확인 (아이디만 사용)
	public UserMember findUsermember(String id) {
		return usermemberRepository.findById(id).orElse(null);
	}
	// 2) 회원가입 전 이름 생년월일 휴대전화로 가입여부 찾기
	public UserMember findUser(String name, String byear, String bmonth, String bday, String tel2, String tel3) {
		return usermemberRepository.findUsermember(name,byear,bmonth,bday,tel2,tel3);
	}
	
	// 3) 아이디 중복확인
	public UserMember idCheck(String id) {
		return usermemberRepository.idCheck(id);
	}
	
	// 4) 회원가입 
	public UserMember joinUserinfo(UserMemberDTO dto) {
		UserMember userMember = dto.toEntity();
		UserMember saveUser = usermemberRepository.save(userMember);
		return saveUser;
	}
	// 5) 로그인 체크
	public UserMember loginCheck(String id, String pwd) {
		return usermemberRepository.loginChekc(id, pwd);
		
	}
}

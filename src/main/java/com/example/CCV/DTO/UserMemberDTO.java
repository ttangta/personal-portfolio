package com.example.CCV.DTO;

import java.util.Date;

import com.example.CCV.Entity.UserMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String birthday;
	private String byear;
	private String bmonth;
	private String bday;
	private String postcode;
	private String address;
	private String detailaddress;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private Date logtime;
	
	public UserMember toEntity() {
		return new UserMember(id, pwd, name, birthday, byear, bmonth, bday, postcode, address, detailaddress, email1, email2, tel1, tel2, tel3, logtime);
	}
}

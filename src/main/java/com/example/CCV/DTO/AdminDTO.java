package com.example.CCV.DTO;

import com.example.CCV.Entity.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	
	public Admin toEntity() {
		return new Admin(id, pwd, name, email, tel);
	}
}

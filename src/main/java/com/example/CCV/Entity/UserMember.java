package com.example.CCV.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="usermember")
public class UserMember {
	@Id
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
	@Temporal(TemporalType.DATE)
	private Date logtime;
}

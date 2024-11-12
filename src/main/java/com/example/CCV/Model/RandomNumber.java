package com.example.CCV.Model;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CCV.Service.EmailService;

public class RandomNumber {
	Random random = new Random();
	
	public String codeNumber() {
		String code = "";
		for(int i=0 ; i<=3 ; i++) {
			int codeNumber = random.nextInt(9);
			code += codeNumber;
		}
		System.out.println("전달해줄 코드번호 : " + code);
		return code;
	}
	
}

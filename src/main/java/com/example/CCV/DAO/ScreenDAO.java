package com.example.CCV.DAO;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.Entity.Screen;
import com.example.CCV.Repository.ScreenRepository;

@Repository
public class ScreenDAO {
	@Autowired
	ScreenRepository screenRepository;
	
	public List<Screen> all(){
		return screenRepository.findAll();
	}

	// 지점에 대한 스크림이름 가져오기
	public List<String> screenNames(String location){
		return screenRepository.screenNames(location);
	}
}

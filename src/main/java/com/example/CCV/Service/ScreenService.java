package com.example.CCV.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.ScreenDAO;
import com.example.CCV.Entity.Screen;

@Service
public class ScreenService {
	@Autowired
	ScreenDAO screenDAO;
	
	public List<Screen> all(){
		return screenDAO.all();
	}
	
	// 지점에 대한 스크림이름 가져오기
	public List<String> screenNames(String location){
		return screenDAO.screenNames(location);
	}
}

package com.example.CCV.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.CinemaDAO;
import com.example.CCV.DTO.CinemaDTO;
import com.example.CCV.Entity.Cinema;

@Service
public class CinemaService {
	@Autowired
	CinemaDAO cinemaDAO;
	
	public Cinema insertCinema(CinemaDTO dto) {
		return cinemaDAO.insertCinema(dto);
	}
	
	public List<Cinema> findByArea(String area){
		return cinemaDAO.findByArea(area);
	}
	
	// 저역 가져오기
	public Set<String> areas(){
		return cinemaDAO.areas();
	}
	// cinema 정보 전부 가져오기
	public List<Cinema> all(){
		return cinemaDAO.all();
	}
	
	// 지역에 대한 지점 가져오기
	public List<String> locations(String area){
		return cinemaDAO.locations(area);
	}
	
	// 모든 지점 들고오기
	public List<String> allLocation(){
		return cinemaDAO.allLocation();
	}

}

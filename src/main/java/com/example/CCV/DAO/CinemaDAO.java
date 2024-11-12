package com.example.CCV.DAO;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.DTO.CinemaDTO;
import com.example.CCV.Entity.Cinema;
import com.example.CCV.Repository.CinemaRepository;

@Repository
public class CinemaDAO {
	@Autowired
	CinemaRepository cinemaRepository;
	
	// 저장
	public Cinema insertCinema(CinemaDTO dto) {
		return cinemaRepository.save(dto.toEntity());
	}
	// area를 통해 해당 cinema 찾기
	public List<Cinema> findByArea(String area){
		return cinemaRepository.findByArea(area);
	}
	
	// 저역 가져오기
	public Set<String> areas(){
		return cinemaRepository.areas();
	}
	
	// cinema 정보 전부 가져오기
	public List<Cinema> all(){
		return cinemaRepository.findAll();
	}
	
	// 지역에 대한 지점 가져오기
	public List<String> locations(String area){
		return cinemaRepository.locations(area);
	}
	
	// 모든 지점 들고오기
	public List<String> allLocation(){
		return cinemaRepository.allLocation();
	}

}

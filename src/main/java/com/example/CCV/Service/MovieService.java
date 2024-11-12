package com.example.CCV.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.MovieDAO;
import com.example.CCV.DTO.MovieDTO;
import com.example.CCV.Entity.Movie;

import jakarta.transaction.Transactional;

@Service
public class MovieService {
	@Autowired
	MovieDAO movieDAO;
	
	// 저장
	@Transactional
	public Movie insertMovie(MovieDTO dto) {
		return movieDAO.insertMovie(dto);
	}
	
	// 영화 제목으로 개봉일 찾기	
	public Date findOpening(String title) {
		return movieDAO.findOpening(title);
	}

	// 개봉일이 빠른 영화 제목 가져오기
	public List<String> titles(){
		return movieDAO.titles();
	}
	
	// 전체 가져오기
	public List<Movie> all(){
		return movieDAO.all();
	}
	
	// runningtime 가져오기
	public List<Integer> runningtimes(){
		return movieDAO.runningtimes();
	}
	
}

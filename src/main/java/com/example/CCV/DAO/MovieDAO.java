package com.example.CCV.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.DTO.MovieDTO;
import com.example.CCV.Entity.Movie;
import com.example.CCV.Repository.MovieRepository;

@Repository
public class MovieDAO {
	@Autowired
	MovieRepository movieReopsitory;
	
	// 저장
	public Movie insertMovie(MovieDTO dto) {
		return movieReopsitory.save(dto.toEntity());
	}
	
	// 영화 제목으로 개봉일 찾기
	public Date findOpening(String title) {
		return movieReopsitory.openingFindByTitle(title);
	}
	
	// 개봉일이 빠른 영화 제목 가져오기
	public List<String> titles(){
		return movieReopsitory.titles();
	}
	
	// 전체 가져오기
	public List<Movie> all(){
		return movieReopsitory.findAll();
	}
	
	// runningtime 가져오기
	public List<Integer> runningtimes(){
		return movieReopsitory.runningtimes();
	}
}

package com.example.CCV.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query(value = "select opening from movie where title=:title",nativeQuery = true)
	Date openingFindByTitle(@Param("title") String title);
	
	// 개봉일이 빠른 것부터 title컬럼만 가져오기
	@Query(value = "select title from movie order by opening asc",nativeQuery = true)
	List<String> titles();
	
	// runningtime 가져오기
	@Query(value = "select runningtime from movie order by opening asc",nativeQuery = true)
	List<Integer> runningtimes();
}

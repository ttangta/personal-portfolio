package com.example.CCV.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
	@Query(value = "select * from cinema where area=:area",nativeQuery = true)
	List<Cinema> findByArea(@Param("area") String area);
	
	// 저역 가져오기
	@Query(value = "select area from cinema",nativeQuery = true)
	Set<String> areas();
	
	// 지역에 대한 지점 가져오기
	@Query(value = "select location from cinema where area=:area",nativeQuery = true)
	List<String> locations(@Param("area") String area);
	
	// 지점 모두 가져오기
	@Query(value = "select location from cinema",nativeQuery = true)
	List<String> allLocation();
}

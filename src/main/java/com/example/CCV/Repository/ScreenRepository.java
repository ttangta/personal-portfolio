package com.example.CCV.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {
	// 지점의 screenname 가져오기
	@Query(value = "select screenname from screen where location=:location",nativeQuery = true)
	List<String> screenNames(@Param("location") String location);
}

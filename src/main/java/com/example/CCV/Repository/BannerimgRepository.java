package com.example.CCV.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CCV.Entity.Bannerimg;

public interface BannerimgRepository extends JpaRepository<Bannerimg, Integer>{
	@Query(value = "select * from bannerimg where imgnum>=:startNum and imgnum<=:endNum order by imgnum desc",nativeQuery = true)
	List<Bannerimg> findByStartnumAndEndNum(@Param("startNum") int startNum, @Param("endNum") int endNum);
	
	@Query(value = "select count(*) as total from bannerimg",nativeQuery = true)
	int getTotal();
	
	@Query(value = "select imgnum from bannerimg where filename=:img",nativeQuery = true)
	int findByFilename(@Param("img") String img);
}

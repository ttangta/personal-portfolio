package com.example.CCV.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.CCV.DTO.BannerImgDTO;
import com.example.CCV.Entity.Bannerimg;
import com.example.CCV.Repository.BannerimgRepository;

@Repository
public class BannerimgDAO {
	@Autowired
	BannerimgRepository bannerimgRepository;
	
	// 1-1)저장하기(String -> dto 전환)
	public Bannerimg insertBannerImg(BannerImgDTO dto) {
		Bannerimg bannerimg = dto.toEntity();
		return bannerimgRepository.save(bannerimg);
	}
	
	// 2) 목록출력
	public List<Bannerimg> bannerImgList(int startNum, int endNum){
		return bannerimgRepository.findByStartnumAndEndNum(startNum, endNum);
	}
	
	// 3) 총 등록된 정보 수 가져오기
	public int getTotal() {
		return bannerimgRepository.getTotal();
	}
	
	// 4) Entity 전체 삭제
	public boolean removeAll() {
		boolean result = false;
		bannerimgRepository.deleteAll();
		int total = bannerimgRepository.getTotal();
		if(total == 0)result = true;
		return result;
		
	}
	
	// 5) 모든 목록 가져오기
	public List<Bannerimg> bannerImgAll(){
		return bannerimgRepository.findAll();
	}
	
	// 6) 이미지 이름으로 primery key 값 가져오기
	public int selectImgnum(String img) {
		return bannerimgRepository.findByFilename(img);
	}
	// 7) 선택된 이미지 삭제하기
	public boolean removeSelect(int imgnum) {
		bannerimgRepository.deleteById(imgnum);
		return !bannerimgRepository.existsById(imgnum);
	}
}

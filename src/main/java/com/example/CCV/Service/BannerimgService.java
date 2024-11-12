package com.example.CCV.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CCV.DAO.BannerimgDAO;
import com.example.CCV.DTO.BannerImgDTO;
import com.example.CCV.Entity.Bannerimg;

@Service
public class BannerimgService {
	@Autowired
	BannerimgDAO bannerimgDAO;
	
	// 1) 저장하기
	public Bannerimg insertBannerImg(BannerImgDTO dto) {
		return bannerimgDAO.insertBannerImg(dto);
	}
	
	// 2) 목록출력
	public List<Bannerimg> bannerImgList(int startNum, int endNum){
		return bannerimgDAO.bannerImgList(startNum, endNum);
	}
	
	// 3) 총 등록된 정보 수 가져오기
	public int getTotal() {
		return bannerimgDAO.getTotal();
	}
	
	// 4) Entity 전체 삭제
	public boolean removeAll() {
		return bannerimgDAO.removeAll();
	}
	
	// 5) 모든 목록 가져오기
	public List<Bannerimg> bannerImgAll(){
		return bannerimgDAO.bannerImgAll();
	}
	
	// 6) 이미지 이름으로 primery key 값 가져오기
	public int selectImgnum(String img) {
		return bannerimgDAO.selectImgnum(img);
	}
	// 7) 선택된 이미지 삭제하기
	public boolean removeSelect(int imgnum) {
		return bannerimgDAO.removeSelect(imgnum);
	}
}

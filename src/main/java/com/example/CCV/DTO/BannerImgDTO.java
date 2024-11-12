package com.example.CCV.DTO;

import com.example.CCV.Entity.Bannerimg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BannerImgDTO {
	private int imgnum;
	private String filename;
	
	public Bannerimg toEntity() {
		return new Bannerimg(imgnum, filename);
	}
}

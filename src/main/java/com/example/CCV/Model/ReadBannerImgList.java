package com.example.CCV.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadBannerImgList {
	public List<String> bannerList() {
		List<String> bannerImgName = new ArrayList<String>();
		File videoDirectory = new File("src/main/resources/static/video");
		File banner_imgDirectory = new File(
				"C:\\\\Users\\\\KimJungHoon\\\\Desktop\\\\spring\\\\workspace\\\\static\\\\banner_img");
		if (banner_imgDirectory.exists() && banner_imgDirectory.isDirectory()) {
			File[] files = banner_imgDirectory.listFiles((dir, name) -> {
				return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg");
			});
			if (files != null) {
				for (File file : files) {
					bannerImgName.add(file.getName());
				}
			}
		}
		return bannerImgName;
	}
}

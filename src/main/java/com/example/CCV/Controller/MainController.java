package com.example.CCV.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	// 메인 화면을 접속
	@GetMapping("/")
	public String main(Model model) {
		List<String> bannerImgName = new ArrayList<String>();
		File videoDirectory = new File("src/main/resources/static/video");
		File banner_imgDirectory = new File("C:\\\\Users\\\\KimJungHoon\\\\Desktop\\\\spring\\\\workspace\\\\static\\\\banner_img");
		if(banner_imgDirectory.exists() && banner_imgDirectory.isDirectory()) {
			File[] files = banner_imgDirectory.listFiles((dir,name) -> {return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg");});
			if(files != null) {
				for(File file : files) {
					bannerImgName.add(file.getName());
				}
			}
		}
		//System.out.println(bannerImgName);
		model.addAttribute("bannerImgName", bannerImgName);
		String webBannerPath = "/banner_img/";
		model.addAttribute("bannerPath", webBannerPath);
		String [] videoFiles = videoDirectory.list((dir,name) -> name.endsWith(".mp4"));
		
		model.addAttribute("videos",videoFiles);
		return "main";
	}
	// 관리자 페이지 이동
	@GetMapping("/admin")
	public String adminPage() {
		return "adminLoginForm";
	}
	
	
}

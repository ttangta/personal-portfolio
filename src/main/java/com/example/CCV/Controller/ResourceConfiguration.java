package com.example.CCV.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 파일 저장 경로 설정
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/storage/cinema")
		.addResourceLocations("C://Users/KimJungHoon/Desktop/spring/workspace/static/storage/");
		
		registry.addResourceHandler("/banner_img/**").addResourceLocations("file:///C:/Users/KimJungHoon/Desktop/spring/workspace/static/banner_img/");
	
		registry.addResourceHandler("/bass_file/**").addResourceLocations("file:///C:\\Users/KimJungHoon/Desktop/spring/workspace/static/bass_file/");
	}
	
	
}

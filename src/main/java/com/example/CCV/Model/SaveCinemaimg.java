package com.example.CCV.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SaveCinemaimg {
	private String bassPath;
	private String location;
	
	public SaveCinemaimg() {};
	public SaveCinemaimg(String bassPath,String location) {
		this.bassPath = bassPath;
		this.location = location;
	}
	
	public boolean Saveimg(List<MultipartFile> list) {
		boolean result = false;
		Path imgPath = Paths.get(bassPath,"locationImg",location);
		File folder = new File(imgPath.toString());
		if(!folder.exists()) {
			folder.mkdirs();
		}
		for(int i=0; i<list.size(); i++) {
			String imgName = list.get(i).getOriginalFilename();
			if(!imgName.equals("")) {
				File img = new File(folder.toString(),imgName);
				try {
					list.get(i).transferTo(img);
					result = true;
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

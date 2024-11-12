package com.example.CCV.DTO;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.CCV.Entity.Cinema;
import com.example.CCV.Entity.Cinemaimg;
import com.example.CCV.Entity.Screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
	private int cinema_id;
	private String area;
	private String location;
	private int total;
	private Date opening;
	private String manager;
	private List<CinemaimgDTO> cinemaimg;
	private List<ScreenDTO> screen;
	
	public Cinema toEntity() {
		Cinema cinema = new Cinema();
		cinema.setCinema_id(this.cinema_id);
		cinema.setArea(this.area);
		cinema.setLocation(this.location);
		cinema.setTotal(this.total);
		cinema.setOpening(this.opening);
		cinema.setManager(this.manager);
		List<Cinemaimg> cl = new ArrayList<>();
		if(this.cinemaimg != null) {
			for(CinemaimgDTO cDTO : cinemaimg) {
				Cinemaimg cinemaimg = new Cinemaimg();
				cinemaimg.setImgname(cDTO.getImgname());
				cinemaimg.setCinema(cinema);
				cl.add(cinemaimg);
			}
		}
		cinema.setCinemaimg(cl);
		
		List<Screen> sl = new ArrayList<>();
		if(this.screen != null) {
			for(ScreenDTO screenDTO : this.screen) {
				Screen screen = new Screen();
				screen.setLocation(screenDTO.getLocation());
				screen.setCinema(cinema);
				screen.setScreenname(screenDTO.getScreenname());
				screen.setScreen_type(screenDTO.getScreen_type());
				screen.setScreen_id(screenDTO.getScreen_id());
				screen.setSeat(screenDTO.getSeat());
				sl.add(screen);
			}
		}
		cinema.setScreen(sl);
		return cinema;
	}
}

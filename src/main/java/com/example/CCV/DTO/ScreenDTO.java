package com.example.CCV.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDTO {
	private int screen_id;
	private int cinema_id;
	private String screenname;
	private String location;
	private int seat;
	private String screen_type;
}

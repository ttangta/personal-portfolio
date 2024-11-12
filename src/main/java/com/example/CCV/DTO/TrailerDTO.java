package com.example.CCV.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrailerDTO {
	private int movie_id;
	private int trailer_id;
	private String trailername;
}

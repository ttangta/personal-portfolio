package com.example.CCV.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private int movie_id;
	private int post_id;
	private String postname;
}

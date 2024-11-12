package com.example.CCV.DTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.CCV.Entity.Movie;
import com.example.CCV.Entity.Post;
import com.example.CCV.Entity.Trailer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDTO {
	private int movie_id;
	private String title;
	private String rating;
	private String genre;
	private String studio;
	private String director;
	private Date opening;
	private int runningtime;
	private String language;
	private String subtitle;
	private List<PostDTO> post;
	private List<TrailerDTO> trailer;
	
	public Movie toEntity() {
		Movie movie = new Movie();
		movie.setMovie_id(this.movie_id);
		movie.setTitle(this.title);
		movie.setRating(this.rating);
		movie.setGenre(this.genre);
		movie.setStudio(this.studio);
		movie.setDirector(this.director);
		movie.setOpening(this.opening);
		movie.setRunningtime(this.runningtime);
		movie.setLanguage(this.language);
		movie.setSubtitle(this.subtitle);
		List<Post> pl = new ArrayList<>();
		if(this.post != null) {
			for(PostDTO pDTO : this.post) {
				Post post = new Post();
				post.setPostname(pDTO.getPostname());
				post.setMovie(movie);
				pl.add(post);
				
			}
		}
		movie.setPost(pl);
		
		List<Trailer> tl = new ArrayList<>();
		if(this.trailer != null) {
			for(TrailerDTO tDTO : this.trailer) {
				Trailer trailer = new Trailer();
				trailer.setTrailername(tDTO.getTrailername());
				trailer.setMovie(movie);
				tl.add(trailer);
			}
		}
		movie.setTrailer(tl);
		return movie;
	}
}

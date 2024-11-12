package com.example.CCV.Entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"post","trailer","showings"})	// 순환 참조 방지
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movie")
public class Movie {
	@Id
	@GeneratedValue(generator = "m_id" ,strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "m_id",sequenceName = "m_id",initialValue = 1,allocationSize = 1)
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
	
	@OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore	//순환 참조방지
	private List<Post> post;
	
	@OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore	//순환 참조방지
	private List<Trailer> trailer;
	
	@OneToMany(mappedBy = "movie")
	@JsonIgnore	//순환 참조방지
	private Set<Showing> showings;
}

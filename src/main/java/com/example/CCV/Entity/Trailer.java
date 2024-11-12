package com.example.CCV.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trailer")
public class Trailer {
	@ManyToOne
	@JoinColumn(name = "movie_id",referencedColumnName = "movie_id")
	@JsonBackReference
	private Movie movie;
	
	@Id
	@GeneratedValue(generator = "t_id",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="t_id",sequenceName ="t_id",initialValue = 1, allocationSize = 1)
	private int trailer_id;
	
	private String trailername;
}

package com.example.CCV.Entity;

import java.sql.Date;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="showing")
public class Showing {
	@Id
	@GeneratedValue(generator = "show_id", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "show_id",sequenceName = "show_id", initialValue = 1, allocationSize = 1)
	private int showing_id;
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name="screen_id")
	private Screen screen;
	@Temporal(TemporalType.DATE)
	private Date showdate;
	private LocalTime showtime;
	private int viewer;
}

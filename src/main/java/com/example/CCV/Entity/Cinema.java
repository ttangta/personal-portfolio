package com.example.CCV.Entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = {"cinemaimg","screen"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cinema")
public class Cinema {
	@Id
	@GeneratedValue(generator = "c_id", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "c_id",sequenceName = "c_id",initialValue = 1,allocationSize = 1)
	private int cinema_id;
	private String area;
	private String location;
	private int total;
	@Temporal(TemporalType.DATE)
	private Date opening;
	private String manager;
	
	@OneToMany(mappedBy = "cinema",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Cinemaimg> cinemaimg;
	
	@OneToMany(mappedBy="cinema",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Screen> screen;
	
}

package com.example.CCV.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cinemaimg {
	@Id
	@GeneratedValue(generator = "i_id",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="i_id",sequenceName ="i_id",initialValue = 1,allocationSize = 1 )
	private int imgnum;
	@ManyToOne
	@JoinColumn(name = "cinema_id",referencedColumnName = "cinema_id")
	@JsonBackReference
	private Cinema cinema;
	
	private String imgname;
}

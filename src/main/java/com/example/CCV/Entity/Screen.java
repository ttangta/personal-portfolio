package com.example.CCV.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"showings","cinema"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name="screen")
public class Screen {
	@Id
	@GeneratedValue(generator = "s_id",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="s_id",sequenceName ="s_id",initialValue = 1,allocationSize = 1)
	private int screen_id;
	@ManyToOne
	@JoinColumn(name = "cinema_id",referencedColumnName = "cinema_id")
	@JsonBackReference
	private Cinema cinema;
	private String screenname;
	private String location;
	private int seat;
	private String screen_type;
	@OneToMany(mappedBy = "screen")
	@JsonIgnore
	private Set<Showing> showings;
}

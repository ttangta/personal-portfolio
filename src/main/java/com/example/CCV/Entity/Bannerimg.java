package com.example.CCV.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
@Table(name="bannerimg")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bannerimg {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imgnumber")
	@SequenceGenerator(name = "imgnumber",sequenceName = "imgnum",initialValue = 1,allocationSize = 1)
	private int imgnum;
	private String filename;
}

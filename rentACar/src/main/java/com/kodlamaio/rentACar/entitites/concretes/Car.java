package com.kodlamaio.rentACar.entitites.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;
	@Column(name = "description")
	private String description;

	@Column(name = "dailyPrice")
	private double dailyPrice;

	@Column(name = "minFindeksScore")
	private int minFindeksScore;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToMany(mappedBy = "car")
	private List<Maintenance> maintenances;

	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;

	@Column(name = "kilometer")
	private double kilometer;

	@Column(name = "carPlate")
	private String carPlate;

	@Column(name = "state")
	private int state;
}

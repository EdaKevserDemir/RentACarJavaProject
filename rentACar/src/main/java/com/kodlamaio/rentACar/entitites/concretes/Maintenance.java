package com.kodlamaio.rentACar.entitites.concretes;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cars"})
@Table(name = "maintenances")
public class Maintenance {
	@Id()

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="id")
	private int id;
	
	@Column(name="dateSent")
	private LocalDate dateSent;
	
	@Column(name="dateReturned")
	private LocalDate dateReturned;
	
	@ManyToOne()
	@JoinColumn(name="car_id")
	private Car car;

}

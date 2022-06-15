package com.kodlamaio.rentACar.entitites.concretes;

import java.time.LocalDate;
import java.util.Date;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cars" })

@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@Column(name="pickupDate")
	private Date pickupDate;
	
	@Column(name="returnDate")
	private Date returnDate;
	
	@Column(name="totalDays")
	private int totalDays;
	
	@Column(name="totalPrice")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="pickUpCity_id")
	private City pickCity;
	
	@ManyToOne
	@JoinColumn(name="returnCity_id")
	private City returnCity;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	

}

package com.kodlamaio.rentACar.entitites.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderedAdditionalItems")
public class OrderedAdditionalItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@Column(name = "pickupDate")
	private Date pickupDate;

	@Column(name = "returnDate")
	private Date returnDate;

	@Column(name = "totalPrice")
	private double totalPrice;

	@Column(name = "totalDays")
	private int totalDays;

	@ManyToOne
	@JoinColumn(name = "additional_item_id")
	private AdditionalItem additionalItem;
	
	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;



}

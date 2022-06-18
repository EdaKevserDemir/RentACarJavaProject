package com.kodlamaio.rentACar.business.response.rentalDetails;

import com.kodlamaio.rentACar.entitites.concretes.Additional;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRentalDetailsResponse {

	private int id;
	private double sumTotalPrice;
	private Additional additional;
	private Rental rental;
}

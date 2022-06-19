package com.kodlamaio.rentACar.business.requests.rentalDetails;

import com.kodlamaio.rentACar.entitites.concretes.Additional;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalDetailRequest {
	private int id;
	private int additionalId; 
	private int rentalId;
	

}

package com.kodlamaio.rentACar.business.requests.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalDetailRequest {
	private int id;
	private int additionalId; 
	private int rentalId;
	

}

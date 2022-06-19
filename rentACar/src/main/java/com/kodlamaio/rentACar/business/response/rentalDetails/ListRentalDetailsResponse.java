package com.kodlamaio.rentACar.business.response.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRentalDetailsResponse {

	private int id;
	private double sumTotalPrice;
	private int additionalId;
	private int rentalId;
}

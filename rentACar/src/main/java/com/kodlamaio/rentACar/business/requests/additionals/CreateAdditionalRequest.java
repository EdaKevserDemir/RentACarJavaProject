package com.kodlamaio.rentACar.business.requests.additionals;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;

}

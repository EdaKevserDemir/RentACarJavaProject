package com.kodlamaio.rentACar.business.requests.additionals;

import javax.persistence.Column;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {
	private int id;
	@Min(20)
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;

}

package com.kodlamaio.rentACar.business.requests.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalRequest {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
}

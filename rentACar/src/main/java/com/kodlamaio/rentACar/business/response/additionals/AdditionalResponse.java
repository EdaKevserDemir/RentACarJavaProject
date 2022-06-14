package com.kodlamaio.rentACar.business.response.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalResponse {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
}

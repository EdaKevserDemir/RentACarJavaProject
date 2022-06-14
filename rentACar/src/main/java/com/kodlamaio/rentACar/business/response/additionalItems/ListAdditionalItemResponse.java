package com.kodlamaio.rentACar.business.response.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAdditionalItemResponse {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
}

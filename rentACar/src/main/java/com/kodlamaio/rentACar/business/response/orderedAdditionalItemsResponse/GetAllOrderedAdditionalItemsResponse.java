package com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderedAdditionalItemsResponse {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
	private Date pickupDate;
	private Date returnDate;
}

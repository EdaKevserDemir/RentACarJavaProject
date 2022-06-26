package com.kodlamaio.rentACar.business.requests.orderedAdditionalItems;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalItemsRequest {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
	private Date pickupDate;
	private Date returnDate;
	private int rentalId;
}

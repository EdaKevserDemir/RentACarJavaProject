package com.kodlamaio.rentACar.business.requests.rentals;

import java.time.LocalDate;
import java.util.Date;

import com.kodlamaio.rentACar.business.response.rentals.ListRentalResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
private int id;
private int carId;
private Date pickupDate;
private Date returnDate;
private int totalDays;
private double totalPrice;
private int pickUpCityId;
private int returnCityId;
private int customerId;
}

package com.kodlamaio.rentACar.business.requests.cars;


import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
	private int id;
	private String description;
	private double dailyPrice;
	private int brandId;
	private Brand brand;
	private Color color;
	private int colorId;

}

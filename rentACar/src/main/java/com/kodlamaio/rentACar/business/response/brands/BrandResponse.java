package com.kodlamaio.rentACar.business.response.brands;

import com.kodlamaio.rentACar.entitites.concretes.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
	private int id;
	private String name;
	
}

package com.kodlamaio.rentACar.business.requests.cars;


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
	private int colorId;
	private double kilometer;
	private String carPlate;
	private int minFindeksScore;
	

	 
	  
	
	  
	
	

}
